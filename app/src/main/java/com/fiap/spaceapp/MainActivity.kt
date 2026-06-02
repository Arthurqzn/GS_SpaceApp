package com.fiap.spaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.fiap.spaceapp.ui.navigation.NavGraph
import com.fiap.spaceapp.ui.theme.SpaceAppTheme
import com.fiap.spaceapp.viewmodel.SpaceViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpaceAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val nav = rememberNavController()
                    val vm: SpaceViewModel = viewModel()
                    NavGraph(nav = nav, vm = vm)
                }
            }
        }
    }
}
