package com.example.androiddevchallenge.core.actions

import com.example.androiddevchallenge.core.data.DataSource
import com.example.androiddevchallenge.core.types.Dog

class GetAllDogsAction {

    operator fun invoke() : List<Dog> {
        return DataSource().getAll()
    }

}