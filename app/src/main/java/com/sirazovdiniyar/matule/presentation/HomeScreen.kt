package com.sirazovdiniyar.matule.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.sirazovdiniyar.matule.R
import com.sirazovdiniyar.matule.ui.theme.RobotoFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current

    Scaffold() { innerPadding ->
        val sheetState = rememberModalBottomSheetState()
        var isSheetOpen by rememberSaveable {
            mutableStateOf(false)
        }


        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            Button(onClick = {
                isSheetOpen = true
            })
            {
                Text(
                    text = "Open bottom sheet",
                    color = Color.White
                )
            }
        }

        if (isSheetOpen) {

            ModalBottomSheet(
                sheetState = sheetState,
                dragHandle = {},
                onDismissRequest = {
                    isSheetOpen = false
                })
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth())
                    {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "Рубашка воскресенье для \n" +
                                    "машинного вязания",
                            style = TextStyle(
                                fontWeight = FontWeight.W600,
                                fontSize = 20.sp,
                                lineHeight = 28.sp,
                                letterSpacing = 0.0038.em,
                                fontFamily = RobotoFamily,

                                color = Color(0xFF000000)
                            )
                        )
                        Spacer(modifier = Modifier.width(44.dp))
                        Icon(
                            painter = painterResource(R.drawable.close),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                isSheetOpen = false
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Описание",
                        style = TextStyle(
                            fontWeight = FontWeight.W500,
                            fontSize = 26.sp,
                            lineHeight = 20.sp,
                            letterSpacing = ((-0.0032).em),
                            fontFamily = RobotoFamily,
                            color = Color(0xFF939396)
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Мой выбор для этих шапок – кардные составы, которые раскрываются деликатным пушком. Кашемиры, мериносы, смесовки с ними отлично подойдут на шапку.\n" +
                                "Кардные составы берите в большое количество сложений, вязать будем резинку 1х1, плотненько.\n" +
                                "Пряжу 1400-1500м в 100г в 4 сложения, пряжу 700м в 2 сложения. Ориентир для конечной толщины – 300-350м в 100г.\n" +
                                "Артикулы, из которых мы вязали эту модель: Zermatt Zegna Baruffa, Cashfive, Baby Cashmere Loro Piana, Soft Donegal и другие.\n" +
                                "Примерный расход на шапку с подгибом 70-90г.",
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            lineHeight = 20.sp,
                            letterSpacing = 0.em,
                            color = Color(0xFF000000)
                        )
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    Text(
                        text = "Примерный расход:",
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            letterSpacing = 0.em,
                            fontFamily = RobotoFamily,
                            color = Color(0xFF939396)
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "80-90 г",
                        style = TextStyle(
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            letterSpacing = ((-0.0032).em),
                            fontFamily = RobotoFamily,
                            color = Color(0xFF000000)
                        )
                    )
                    Spacer(modifier = Modifier.height(19.dp))
                    Button(
                        modifier = Modifier
                            .padding(top = 19.dp)
                            .fillMaxWidth(),

                        colors = ButtonDefaults.buttonColors(Color(0xFF1A6FEE)),
                        contentPadding = PaddingValues(16.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            Toast
                                .makeText(context, "Товар добавлен", Toast.LENGTH_SHORT)
                                .show()
                        })
                    {
                        Text(
                            text = "Добавить за 690 ₽",
                            style = TextStyle(
                                fontWeight = FontWeight.W600,
                                fontSize = 17.sp,
                                lineHeight = 24.sp,
                                letterSpacing = 0.em,
                                fontFamily = RobotoFamily,
                                color = Color(0xFFFFFFFF)
                            )
                        )

                    }


                }


            }
        }

    }

}

@Preview
@Composable
private fun HomeScreenPreview() {
    MaterialTheme() {
        HomeScreen()
    }

}