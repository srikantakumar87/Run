package com.sri.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.sri.core.presentation.designsystem.EyeClosedIcon
import com.sri.core.presentation.designsystem.EyeOpenedIcon
import com.sri.core.presentation.designsystem.LockIcon
import com.sri.core.presentation.designsystem.R

@Composable
fun SBasicSecureTextField(
    value: TextFieldValue,  // Existing state for text field
    onValueChange: (TextFieldValue) -> Unit,  // Add this to handle text changes
    textObfuscationMode: TextObfuscationMode,  // Pass secure/visible mode
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    cursorBrush: SolidColor = SolidColor(Color.Black),
    modifier: Modifier = Modifier,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    hint: String,
) {
    var isFocused by remember { mutableStateOf(false) }
    BasicTextField(
        value = value,  // Track the value of the text field
        onValueChange = onValueChange,
        visualTransformation = if (textObfuscationMode == TextObfuscationMode.Hidden) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        cursorBrush = cursorBrush,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isFocused) {
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
                } else {
                    MaterialTheme.colorScheme.surface
                }
            ).border(
                width = 1.dp,
                color = if (isFocused) {
                    MaterialTheme.colorScheme.primary
                } else {
                    Color.Transparent
                },
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp)
            .onFocusChanged { isFocused = it.isFocused },
        decorationBox = { innerTextField ->  // Custom decoration
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = LockIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (value.text.isEmpty() && !isFocused) {
                        Text(
                            text = hint,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    innerTextField()
                }
                IconButton(onClick = onTogglePasswordVisibility) {
                    Icon(
                        imageVector = if (isPasswordVisible) EyeOpenedIcon else EyeClosedIcon,
                        contentDescription = if (isPasswordVisible) {
                            stringResource(id = R.string.hide_password)
                        } else {
                            stringResource(id = R.string.show_password)
                        }
                    )
                }
            }
        }

            )
}

