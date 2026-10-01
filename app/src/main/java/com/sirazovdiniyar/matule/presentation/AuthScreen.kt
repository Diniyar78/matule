package com.sirazovdiniyar.matule.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sirazovdiniyar.matule.R
import com.sirazovdiniyar.matule.TOKEN
import com.sirazovdiniyar.matule.USER_ID
import com.sirazovdiniyar.matule.data.AuthRequest
import com.sirazovdiniyar.matule.data.matuleApi
import com.sirazovdiniyar.matule.dataStore
import com.sirazovdiniyar.matule.ui.theme.MatuleTheme
import com.sirazovdiniyar.matule.ui.theme.RobotoFamily
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var emailError by remember {
        mutableStateOf<String?>(null)
    }
    var passwordError by remember {
        mutableStateOf<String?>(null)
    }
    val scope = rememberCoroutineScope()
    var showPassword by remember {
        mutableStateOf(false)
    }

    Scaffold(modifier = Modifier.background(Color(0xFFFFFFFF))) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(59.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "✋",
                    fontSize = 32.sp,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    "Добро пожаловать!",
                    style = TextStyle(
                        fontWeight = FontWeight.W700,
                        fontSize = 24.sp,
                        lineHeight = 28.sp,
                        letterSpacing = 0.0033.em
                    ),
                    fontFamily = RobotoFamily
                )
            }
            Spacer(modifier = Modifier.height(23.dp))
            Text(
                text = "Войдите, чтобы пользоваться функциями приложения",
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.em,
                    fontFamily = RobotoFamily
                ),

                )
            Spacer(modifier = Modifier.height(64.dp))
            Column {
                Text(
                    text = "Вход по E-mail",
                    style = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.em,
                        fontFamily = RobotoFamily
                    ),

                    )
                Spacer(modifier = Modifier.width(4.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2254F5).copy(alpha = 0.5f),
                        unfocusedBorderColor = Color(0xFFEBEBEB),
                        errorBorderColor = Color(0xFFFD3535),
                        unfocusedContainerColor = Color(0xFFF5F5F9),
                        focusedContainerColor = Color(0xFFF5F5F9),
                    ),
                    placeholder = {
                        Text(
                            text = "example@mail.com",
                            style = TextStyle(
                                fontWeight = FontWeight.W400,
                                fontSize = 15.sp,
                                lineHeight = 20.sp,
                                letterSpacing = 0.em,
                                fontFamily = RobotoFamily
                            ),

                            )
                    },
                    isError = emailError != null,
                    supportingText = {
                        if (emailError != null) {
                            Text(emailError!!)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Пароль",
                    style = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.em,
                        fontFamily = RobotoFamily
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    singleLine = true,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                showPassword = !showPassword

                            })
                        {
                            if (showPassword == false) {
                                Icon(
                                    painter = painterResource(R.drawable.vector12),
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    painter = painterResource(R.drawable.eye_off_an_inner_journey___iconsvg_co),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2254F5),
                        unfocusedBorderColor = Color(0xFFEBEBEB),
                        errorBorderColor = Color(0xFFFD3535),
                        unfocusedContainerColor = Color(0xFFF5F5F9),
                        focusedContainerColor = Color(0xFFF5F5F9),
                    ),
                    isError = passwordError != null,
                    supportingText = {
                        if (passwordError != null) {
                            Text(passwordError!!)
                        }
                    },
                    visualTransformation = if (showPassword) {
                        VisualTransformation.None
                    } else PasswordVisualTransformation('*')
                )
                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = {
                        val emailRegex = Regex("^[a-z0-9]+@[a-z0-9]+.[a-z]{2,}$")
                        emailError = if (!emailRegex.matches(email)) {
                            "Неверный email"
                        } else null
                        passwordError = if (password.length < 6) {
                            "Длина пароля должна быть не меньше 6 символов"
                        } else null
                        if (emailError == null && passwordError == null) {
                            scope.launch {
                                try {
                                    val response = matuleApi.authWithPassword(
                                        AuthRequest(email, password)
                                    )
                                    context.dataStore.edit { preferences ->
                                        preferences[TOKEN] = response.token
                                        preferences[USER_ID] = response.record.id
                                    }
                                    navController.navigate(Route.MainNavigation)
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .size(10.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A6FEE),
                        disabledContainerColor = Color(0xFFC9D4FB),
                        disabledContentColor = Color(0xFFFFFFFF)
                    ),
                    enabled = email.isNotEmpty() && password.isNotEmpty()
                ) {
                    Text(
                        text = "Далее",
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 17.sp,
                            lineHeight = 24.sp,
                            letterSpacing = 0.em,
                            fontFamily = RobotoFamily
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ) {

                        },
                    text = "Зарегистрироваться",
                    style = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 15.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.em,
                        color = Color(0xFF2074F2),
                        fontFamily = RobotoFamily
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(59.dp))
                Text(
                    text = "Или войдите с помощью",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 15.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.em,
                        color = Color(0xFF939396),
                        fontFamily = RobotoFamily
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(14.dp),
                    onClick = {

                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                    ),
                    border = BorderStroke(1.dp, Color(0xFFEBEBEB)),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.vkh4_logo),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Войти с VK",
                            style = TextStyle(
                                fontWeight = FontWeight.W500,
                                fontSize = 17.sp,
                                lineHeight = 24.sp,
                                letterSpacing = 0.em,
                                color = Color(0xFF000000),
                                fontFamily = RobotoFamily
                            ),
                            textAlign = TextAlign.Center
                        )

                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                    },
                    contentPadding = PaddingValues(14.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                    ),
                    border = BorderStroke(1.dp, Color(0xFFEBEBEB)),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.vector),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Войти с Yandex",
                            style = TextStyle(
                                fontWeight = FontWeight.W500,
                                fontSize = 17.sp,
                                lineHeight = 24.sp,
                                letterSpacing = 0.em,
                                color = Color(0xFF000000),
                                fontFamily = RobotoFamily
                            ),
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AuthScreenPreview() {
    MatuleTheme {
        AuthScreen(
            navController = rememberNavController()
        )
    }
}