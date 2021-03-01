package com.example.androiddevchallenge.core.data

import com.example.androiddevchallenge.core.types.Dog
import com.example.androiddevchallenge.core.types.Sex

class DataSource {

    private val dogs : List<Dog>

    init {
        dogs = listOf(
            Dog(0, 2, "Fred", loremIpsum(), Sex.Male, "London", "dog1"),
            Dog(1, 9, "Jack", loremIpsum(), Sex.Male, "Glasgow", "dog2"),
            Dog(2, 7, "Jill", loremIpsum(), Sex.Female, "Glasgow", "dog3"),
            Dog(3, 4, "Emma", loremIpsum(), Sex.Female, "Cardiff", "dog4"),
            Dog(4, 5, "Hank", loremIpsum(), Sex.Male, "London", "dog5"),
            Dog(5, 1, "Trish", loremIpsum(), Sex.Female, "London", "dog6"),
            Dog(6, 11, "Snoopy", loremIpsum(), Sex.Female, "London", "dog7"))
    }

    private fun loremIpsum() : String =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque cursus sit amet " +
                "purus eu egestas. Vivamus hendrerit quam eu pharetra rutrum. Vestibulum vitae ullamcorper " +
                "nulla. Ut euismod malesuada erat ac bibendum. Mauris nec placerat elit. Praesent id" +
                " dolor nulla. Aenean posuere quis tellus lobortis accumsan. In pellentesque iaculis" +
                " accumsan. Nullam non metus nec nisl aliquam feugiat. Vestibulum viverra in felis" +
                " eu tempor. Cras vestibulum semper ipsum, " +
                "eu molestie nisi condimentum et. Pellentesque vel ligula ipsum. Suspendisse" +
                " ut congue libero."


    fun getAll() : List<Dog> = dogs

    fun get(id : Long) = dogs.first { it.id == id }

}