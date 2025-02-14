package com.example.covertervk.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import com.example.covertervk.presentation.theme.ui.CoverterVkTheme
import com.example.covertervk.presentation.util.BottomNavigationItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ExchangeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoverterVkTheme {
                val items = listOf(
                    BottomNavigationItem(title = "123")
                )
                var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
//                Scaffold(
//                    bottomBar = {
//                        NavigationBar {
//                            items.forEachIndexed { index, item ->
//                                NavigationBarItem(
//                                    selected = selectedItemIndex == index,
//                                    onClick = {
//                                        selectedItemIndex = index
//                                        navController.navigate(item.title)
//                                    },
//                                    icon = {
//                                        Icon(
//                                            imageVector = if (index == selectedItemIndex) {
//                                                item.selectedIcon
//                                            } else {
//                                                item.unselectedIcon
//                                            },
//                                            contentDescription = ""
//                                        )
//                                    },
//
//                                    )
//                            }
//
//                        }
//                    }
//                ) {
//
//                }
                ExchangeScreen2(viewModel)
            }
        }
    }
}