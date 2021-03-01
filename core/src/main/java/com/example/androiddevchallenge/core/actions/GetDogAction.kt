package com.example.androiddevchallenge.core.actions

import com.example.androiddevchallenge.core.data.DataSource
import com.example.androiddevchallenge.core.types.Dog

class GetDogAction {

    operator fun invoke(id : Long) : Dog {
        return DataSource().get(id)
    }

}