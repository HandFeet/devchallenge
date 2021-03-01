package com.example.androiddevchallenge

class ResourceConverter {

    fun getDrawableId(fileName : String): Int {
        when (fileName) {
            "dog1" -> return R.drawable.dog1
            "dog2" -> return R.drawable.dog2
            "dog3" -> return R.drawable.dog3
            "dog4" -> return R.drawable.dog4
            "dog5" -> return R.drawable.dog5
            "dog6" -> return R.drawable.dog6
            "dog7" -> return R.drawable.dog7
        }
        return 0
    }

}