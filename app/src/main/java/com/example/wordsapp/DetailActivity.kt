/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.ActivityDetailBinding


//Main Activity가 아닌 새로운 액티비티! - 얘를 위해 intent가 필요함
class DetailActivity : AppCompatActivity() {
        //단어를 선택해서 검색창으로 이동하게끔 해보자!
        //base url은 어떤 검색을 하든 쓰일 것이라서 상수로 작성함.
        companion object{
            val LETTER = "letter"
            val SEARCH_PREFIX = "https://www.google.com/search?q="
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve a binding object that allows you to refer to views by id name
        // Names are converted from snake case to camel case.
        // For example, a View with the id word_one is referenced as binding.wordOne
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the LETTER from the Intent extras
        // intent.extras.getString returns String? (String or null)
        // so toString() guarantees that the value will be a String
        //val letterId = "A"
        val letterId = intent?.extras?.getString("letter").toString()
        //intent property는 DetailActivity의 속성이 아님. any activity의 속성임.
        //extras 속성은 bundle 유형이다. intent로 전달된 모든 extras의 접근 방법을 제공
        //둘 다 물음표인 이유는 intent와 extras속성이 nullable이라서
        //코틀린에서 null은 값이 없는 것. ? <- 약간 null 처리해주는 역할
        //intent가 null이면 그 다음(extras) 실행 안함, extras가 null이면 그 다음(getSTring) 실행 안함
        //마지막으로 getString 값이 string인지 확인하려고 toString을 넣은것..

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(letterId, this)

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix) + " " + letterId
    }
}