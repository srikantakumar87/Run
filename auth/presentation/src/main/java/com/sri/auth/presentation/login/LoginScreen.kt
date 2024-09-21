package com.sri.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sri.auth.presentation.R
import com.sri.auth.presentation.register.RegisterAction
import com.sri.core.presentation.designsystem.CheckIcon
import com.sri.core.presentation.designsystem.EmailIcon
import com.sri.core.presentation.designsystem.Poppins
import com.sri.core.presentation.designsystem.RunTheme
import com.sri.core.presentation.designsystem.components.GradientBackground
import com.sri.core.presentation.designsystem.components.RunActionButton
import com.sri.core.presentation.designsystem.components.RunTextField
import com.sri.core.presentation.designsystem.components.SBasicSecureTextField
import com.sri.core.presentation.ui.ObserveAsEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    ObserveAsEvent(viewModel.events) { event ->
        when(event) {
            is LoginEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
                // Handle error event

            }
            LoginEvent.LoginSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    R.string.you_are_logged_in,
                    Toast.LENGTH_SHORT
                ).show()

                onLoginSuccess()

            }

        }
    }

    LoginScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                is LoginAction.OnRegisterClick -> onSignUpClick()
                else -> Unit
            }
            viewModel.onAction(action)



        }
    )


}
@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)

        ){
            Text(
                text = stringResource(id= R.string.hi_there),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineMedium


            )
            Text(
                text = stringResource(id= R.string.run_welcome_text),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            Spacer(modifier = Modifier.height(48.dp))
            RunTextField(
                value = state.email, // Pass the entire TextFieldValue
                onValueChange = { newValue ->
                    onAction(LoginAction.OnEmailChanged(newValue.text))
                },
                startIcon = EmailIcon,
                endIcon = null,
                hint = stringResource(id = R.string.example_email),
                title = stringResource(id = R.string.email),
                modifier = Modifier.fillMaxWidth(),
                additionalInfo = stringResource(id = R.string.must_be_a_valid_email),
                keyboardType = KeyboardType.Email,
                textStyle = TextStyle(textDirection = TextDirection.Ltr)
            )
            Spacer(modifier = Modifier.height(16.dp))
            SBasicSecureTextField(
                value = state.password,
                onValueChange = { newValue ->
                    onAction(LoginAction.OnPasswordChanged(newValue.text))

                },
                onTogglePasswordVisibility = {
                    onAction(LoginAction.OnTogglePasswordVisibilityClick)
                },
                isPasswordVisible = state.isPasswordVisible,
                textObfuscationMode = if (state.isPasswordVisible) {
                    TextObfuscationMode.Visible
                } else {
                    TextObfuscationMode.Hidden
                },
                textStyle = TextStyle(textDirection = TextDirection.Ltr, color = Color.White),
                keyboardOptions = KeyboardOptions.Default,
                cursorBrush= SolidColor(Color.White),
                modifier = Modifier.fillMaxWidth(),
                hint = stringResource(id = R.string.password)

            )
            Spacer(modifier = Modifier.height(32.dp))
            RunActionButton(
                text = stringResource(id = R.string.login),
                isLoading = state.isLoggingIn,
                enabled = state.canLogin,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(LoginAction.OnLoginClick)
                }
            )

            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = Poppins,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    append(stringResource(id = R.string.dont_have_an_account) + " ")
                    pushStringAnnotation(
                        tag = "clickable_text",
                        annotation = stringResource(id = R.string.sign_up)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = Poppins
                        )
                    ) {
                        append(stringResource(id = R.string.sign_up))
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp))

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxHeight() // Make the Box fill the entire available space
                    .padding(16.dp),
                contentAlignment = Alignment.BottomCenter


            ){
                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(
                            tag = "clickable_text",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {
                            onAction(LoginAction.OnRegisterClick)
                        }
                    }
                )
            }




        }
    }

}

@Preview
@Composable
private fun LoginScreenPreview() {
    RunTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}