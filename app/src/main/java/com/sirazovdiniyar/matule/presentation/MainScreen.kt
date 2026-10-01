package com.sirazovdiniyar.matule.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sirazovdiniyar.matule.R
import com.sirazovdiniyar.matule.ui.theme.RobotoFamily


@Composable
fun MainScreen(
    rootNavController: NavHostController
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(Route.HomeScreen)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.home),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = "Главная",
                            style = TextStyle(
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                letterSpacing = 0.em,
                                fontFamily = RobotoFamily,
                                color = Color(0xFFB8C1CC)
                            )
                        )
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(Route.CatalogScreen)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.result),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = "Каталог",
                            style = TextStyle(
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                letterSpacing = 0.em,
                                fontFamily = RobotoFamily,
                                color = Color(0xFFB8C1CC)
                            )
                        )
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Route.ProjectScreen) },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.group),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = "Проекты",
                            style = TextStyle(
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                letterSpacing = 0.em,
                                fontFamily = RobotoFamily,
                                color = Color(0xFFB8C1CC)
                            )
                        )
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Route.ProfileScreen) },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.user),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = "Профиль",
                            style = TextStyle(
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                letterSpacing = 0.em,
                                fontFamily = RobotoFamily,
                                color = Color(0xFFB8C1CC)
                            )
                        )
                    }
                )

            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            navController = navController,
            startDestination = Route.HomeScreen
        ) {
            composable<Route.HomeScreen> {
                HomeScreen()
            }
            composable<Route.CatalogScreen> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Каталог")
                }
            }
            composable<Route.ProjectScreen> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Проекты")
                }
            }
            composable<Route.ProfileScreen> {
                ProfileScreen(
                    rootNavController = rootNavController
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPrewiew() {
    MaterialTheme {
        HomeScreen()
    }
}