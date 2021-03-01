/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.core.types.Dog
import com.example.androiddevchallenge.ui.composables.DogDetailedView
import com.example.androiddevchallenge.ui.composables.DogsList
import com.example.androiddevchallenge.ui.composables.LoadingIndicator
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var currentDog: Dog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.dogs.observe(
            this,
            {
                setContent {
                    ComposeNavigation(it)
                }
            }
        )
    }

    @Composable
    fun ComposeNavigation(dogs: List<Dog>) {
        val navController = rememberNavController()
        viewModel.dog.observe(
            this,
            {
                if (!it.hasBeenHandled) {
                    currentDog = it.getContentIfNotHandledOrReturnNull() !!
                    navController.navigate("third_screen") {
                        popUpTo = navController.graph.startDestination
                    }
                }
            }
        )
        NavHost(
            navController = navController,
            startDestination = "first_screen"
        ) {
            composable("first_screen") {
                DogsList(
                    dogs,
                    object : RecordClick {
                        override fun click(id: Long) {
                            viewModel.getDog(id)
                            navController.navigate("second_screen")
                        }
                    }
                )
            }
            composable("second_screen") {
                LoadingIndicator()
            }
            composable("third_screen") {
                MyTheme {
                    DogDetailedView(currentDog)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showLoading()
        viewModel.loadDogs()
    }

    private fun showLoading() {
        setContent {
            MyTheme {
                LoadingIndicator()
            }
        }
    }
}
