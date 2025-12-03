package com.sirazovdiniyar.matule.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rajat.pdfviewer.PdfViewerActivity
import com.rajat.pdfviewer.util.saveTo
import com.sirazovdiniyar.matule.TOKEN
import com.sirazovdiniyar.matule.USER_ID
import com.sirazovdiniyar.matule.data.User
import com.sirazovdiniyar.matule.data.matuleApi
import com.sirazovdiniyar.matule.dataStore
import com.sirazovdiniyar.matule.ui.theme.RobotoFamily
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    rootNavController: NavHostController
) {
    var switchState by remember {
        mutableStateOf(true)
    }
    var userState by remember {
        mutableStateOf<User?>(null)
    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val token = context.dataStore.data.map { preferences ->
            preferences[TOKEN]
        }.first()
        val userId = context.dataStore.data.map { preferences ->
            preferences[USER_ID]
        }.first()
        if (token != null && userId != null) {
            try {
                userState = matuleApi.getUser(
                    token = token,
                    userId = userId
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Scaffold(
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .padding(top = 32.dp)
        ) {
            userState?.let {
                Text(
                    text = it.firstname,
                    style = TextStyle(
                        fontWeight = FontWeight.W700,
                        fontSize = 24.sp,
                        lineHeight = 28.sp,
                        letterSpacing = 0.0033.em,
                        color = Color(0xFF000000),
                        fontFamily = RobotoFamily
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            userState?.let {
                Text(
                    text = it.email,
                    style = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        letterSpacing = (-0.0032).em,
                        color = Color(0xFF939396),
                        fontFamily = RobotoFamily
                    )
                )
            }
            Spacer(modifier = Modifier.height(23.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "📋",
                        fontSize = 32.sp
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Мои заказы",
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 17.sp,
                            lineHeight = 24.sp,
                            letterSpacing = 0.em,
                            fontFamily = RobotoFamily
                        )
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚙️",
                        fontSize = 32.sp
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Уведомления",
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 17.sp,
                            lineHeight = 24.sp,
                            letterSpacing = 0.em,
                            fontFamily = RobotoFamily
                        )
                    )
                    Spacer(modifier = Modifier.width(51.dp))
                    Switch(
                        checked = switchState,
                        onCheckedChange = {
                            switchState = it

                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(176.dp))
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        context.startActivity(
                            PdfViewerActivity.launchPdfFromUrl(
                                context = context,
                                pdfUrl = "https://www.back4app.com/terms-of-service.pdf",
                                pdfTitle = null,
                                saveTo = saveTo.ASK_EVERYTIME
                            )
                        )

                    },
                text = "Политика конфиденциальности",
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.em,
                    fontFamily = RobotoFamily
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .clickable {
                        context.startActivity(
                            PdfViewerActivity.launchPdfFromUrl(
                                context = context,
                                pdfTitle = null,
                                pdfUrl = "https://www.back4app.com/terms-of-service.pdf",
                                saveTo = saveTo.ASK_EVERYTIME
                            )
                        )
                    }
                    .align(alignment = Alignment.CenterHorizontally),
                text = "Пользовательское соглашение",
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.em,
                    fontFamily = RobotoFamily
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        scope.launch {
                            context.dataStore.edit { preferences ->
                                preferences.remove(TOKEN)
                            }
                        }
                        rootNavController.navigate(Route.AuthScreen)
                    },
                text = "Выход",
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.em,
                    fontFamily = RobotoFamily,
                    color = Color(0xFFFD3535)
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreePreview() {
    MaterialTheme {
        ProfileScreen(
            rootNavController = rememberNavController()
        )
    }

}