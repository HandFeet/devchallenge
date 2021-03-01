package com.example.androiddevchallenge.core.types

data class Dog(
    val id: Long,
    val age: Int,
    val name: String,
    val description: String,
    val sex: Sex,
    val location: String,
    val fileName : String)