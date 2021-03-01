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

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.core.types.Dog
import com.example.androiddevchallenge.core.types.Sex
import com.example.androiddevchallenge.ui.composables.*
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private var lastSelected = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.dogs.observe(this, {
            setContent {
                ComposeNavigation(it)
            }
        })
    }

    @Composable
    fun ComposeNavigation(dogs : List<Dog>) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "first_screen"
        ) {
            composable("first_screen") {
                FirstScreen(navController = navController, dogs)
            }
            composable("second_screen") {
                SecondScreen()
            }
        }
    }


    @Composable
    private fun FirstScreen(navController: NavHostController,
                            dogs : List<Dog>) {
        MyTheme {
            DogList(dogs, object : RecordClick {
                override fun click(id: Long) {
                    lastSelected = id
                    navController.navigate("second_screen")
                }
            })
        }
    }

    @Composable
    private fun SecondScreen() {
        MyTheme {
            MyApp()
        }
    }

    private fun showLoading() {
        setContent {
            MyTheme {
                LoadingList()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showLoading()
        viewModel.loadDogs()
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun RowPreview() {
        MyTheme {
            DogRow(Dog(0,  1, "Name Here",
                "DESCRIPTION I AM A DESC",
                Sex.Female, "Stoke", ""), object : RecordClick {
                override fun click(id: Long) {
                    //DO NOTHING, IT'S A PREVIEW
                }
            })

        }
    }


    @Composable
    fun DogList(dogs: List<Dog>, clickEvent: RecordClick) {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            dogs.forEach { dog ->
                DogRow(dog, clickEvent)
            }
        }
    }

    @Composable
    fun DogRow(dog: Dog, clickEvent: RecordClick) {

        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    clickEvent.click(dog.id)
                }) {
            Row(modifier = Modifier.padding(0.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(ResourceConverter().getDrawableId(dog.fileName)),
                    contentDescription = "profile_pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(4.dp, color = Color.Blue, shape = CircleShape)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = dog.name, style = typography.h4)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Card(backgroundColor = Color.Blue, modifier = Modifier.padding(4.dp)) {
                                Text(dog.location, color = Color.White, modifier = Modifier.padding(4.dp))
                            }
                            Card(
                                backgroundColor = Color.Blue, modifier = Modifier.padding(4.dp)) {
                                Text("${dog.age} yrs", color = Color.White, modifier = Modifier.padding(4.dp))
                            }
                        }
                    }
                    Text(text = dog.description,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1, style = typography.h5)
                }
            }
        }

    }

}