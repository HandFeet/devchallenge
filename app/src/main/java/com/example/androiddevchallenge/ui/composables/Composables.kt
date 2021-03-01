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
package com.example.androiddevchallenge.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.RecordClick
import com.example.androiddevchallenge.ResourceConverter
import com.example.androiddevchallenge.core.types.Dog
import com.example.androiddevchallenge.core.types.Sex
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun LoadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun DogList(dogs: List<Dog>, clickEvent: RecordClick) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        dogs.forEach { dog ->
            DogRow(dog, clickEvent)
        }
    }
}

@Composable
fun DogsList(
    dogs: List<Dog>,
    recordClick: RecordClick
) {
    MyTheme {
        DogList(dogs, recordClick)
    }
}

@Composable
fun DogRow(dog: Dog, clickEvent: RecordClick) {
    Card(
        elevation = 16.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                clickEvent.click(dog.id)
            }
    ) {
        Row(
            modifier = Modifier.padding(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePic(dog)
            RowContent(dog)
        }
    }
}

@Composable
fun ProfilePic(dog: Dog) {
    Image(
        painter = painterResource(ResourceConverter().getDrawableId(dog.fileName)),
        contentDescription = "profile_pic",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(95.dp)
            .padding(8.dp)
            .clip(CircleShape)
            .border(4.dp, color = Color.Blue, shape = CircleShape)
    )
}

@Composable
fun RowContent(dog: Dog) {
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
                RowLocation(dog)
                RowAge(dog)
            }
        }
        RowDescription(dog)
    }
}

@Composable
fun RowLocation(dog: Dog) {
    Card(elevation = 16.dp, backgroundColor = Color.Blue, modifier = Modifier.padding(4.dp)) {
        Text(dog.location, color = Color.White, modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun RowAge(dog: Dog) {
    Card(
        elevation = 16.dp,
        backgroundColor = Color.Blue, modifier = Modifier.padding(4.dp)
    ) {
        Text("${dog.age} yrs", color = Color.White, modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun RowDescription(dog: Dog) {
    Text(
        text = dog.description,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2, style = typography.h5
    )
}

@Composable
fun DogDetailedView(dog: Dog) {
    Scaffold(
        content = {
            DetailsBody(dog)
        },
        floatingActionButton = {
            Fab()
        }
    )
}

@Composable
fun DetailsBody(dog: Dog) {
    Column {
        Box {
            ContentImage(dog)
            DogTopInfo(dog)
        }
        DogDetails(dog)
    }
}

@Composable
private fun ContentImage(dog: Dog) {
    Image(
        painter = painterResource(ResourceConverter().getDrawableId(dog.fileName)),
        contentDescription = "profile_pic",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

@Composable
fun DogTopInfo(dog: Dog) {
    Box(modifier = Modifier.padding(8.dp)) {
        Card(
            shape = RoundedCornerShape(8.dp), elevation = 32.dp,
            modifier = Modifier.padding(4.dp), contentColor = Color.White,
            backgroundColor = Color.Blue
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "${dog.age} yrs", modifier = Modifier.padding(8.dp))
                Text(text = dog.location, modifier = Modifier.padding(8.dp))
                Image(
                    painter = if (dog.sex == Sex.Male)
                        painterResource(R.drawable.gender_male)
                    else
                        painterResource(R.drawable.gender_female),
                    contentDescription = "profile_pic",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun DogDetails(dog: Dog) {
    Card(modifier = Modifier.padding(16.dp), elevation = 8.dp) {
        Column {
            Text(
                modifier = Modifier.padding(8.dp), text = dog.name, style = typography.h4
            )
            Text(
                modifier = Modifier.padding(8.dp), text = dog.description, style = typography.body2
            )
        }
    }
}

@Composable
fun Fab() {
    ExtendedFloatingActionButton(
        onClick = { /*Do nothing as we can't really adopt anything*/ },
        icon = {
            Icon(
                Icons.Filled.Favorite, "",
                tint = Color.White
            )
        },
        backgroundColor = Color.Blue,
        text = { Text(text = "Adopt me", color = Color.White) }
    )
}
