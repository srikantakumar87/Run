package com.sri.core.domain.util

sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E: com.sri.core.domain.util.Error>(val error: E) : Result<Nothing, E>


}

inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E>{
    return when(this){
        is Result.Success -> Result.Success(map(data))
        is Result.Error -> Result.Error(error)
    }
}

fun <T, E: Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }

}
typealias EmptyResult<E> = Result<Unit, E>
/*
enum class PasswordValidationError: Error {
    TOO_SHORT,
    MISSING_ONE_DIGIT
}

fun validatePassword(password: String): Result<Unit, PasswordValidationError>{

    return Result.Error(PasswordValidationError.MISSING_ONE_DIGIT)

}

fun handleError(result: Result<Unit, PasswordValidationError>){
    when(result) {

        is Result.Success -> {

        }
        is Result.Error -> {
            when(result.error){
                PasswordValidationError.MISSING_ONE_DIGIT -> {}
                PasswordValidationError.TOO_SHORT -> {}
            }

        }


    }

}*/
