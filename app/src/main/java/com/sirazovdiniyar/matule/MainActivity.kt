package com.sirazovdiniyar.matule

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sirazovdiniyar.matule.presentation.AuthScreen
import com.sirazovdiniyar.matule.presentation.MainScreen
import com.sirazovdiniyar.matule.presentation.Route
import com.sirazovdiniyar.matule.ui.theme.MatuleTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("settings")

val TOKEN = stringPreferencesKey("token")
val USER_ID = stringPreferencesKey("userId")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleTheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                var startDestination by remember {
                    mutableStateOf<Route>(Route.AuthScreen)
                }

                LaunchedEffect(Unit) {
                    val token = context.dataStore.data.map { preferences ->
                        preferences[TOKEN]
                    }.first()
                    if (token != null) {
                        startDestination = Route.MainNavigation
                    } else {
                        startDestination = Route.AuthScreen
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable<Route.AuthScreen> {
                        AuthScreen(navController = navController)
                    }
                    navigation<Route.MainNavigation>(
                        startDestination = Route.MainScreen
                    ) {
                        composable<Route.MainScreen> {
                            MainScreen(
                                rootNavController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}