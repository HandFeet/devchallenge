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

class ResourceConverter {

    fun getDrawableId(fileName: String): Int {
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
