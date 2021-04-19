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
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.ActivityMainBinding

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true //기본값 true 설정, false면 그리드, true면 선형인듯

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // Sets the LinearLayoutManager of the recyclerview

        // 그리드랑 선형을 번갈아가서 사용하므로 이제 이거 두 줄 안쓰는 듯..?
        // recyclerView.layoutManager = LinearLayoutManager(this)
       // recyclerView.adapter = LetterAdapter()
        chooseLayout()
    }

    //사용자가 버튼을 toggle 하면 리스트가 그리드로 바뀌는..
    private fun chooseLayout(){
        if(isLinearLayoutManager){
            recyclerView.layoutManager = LinearLayoutManager(this)
        }else{
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.adapter = LetterAdapter() //그리드, 선형 모두 사용
    }
    private fun setIcon(menuItem: MenuItem?){
        if(menuItem == null)
            return
        //layoutManager가 현재 어떤 상태인지에 따라 icon(drawable)을 바꿈
        //if문을 변수 오른쪽에 써서 값 지정 할 수 있음.
        menuItem.icon =
                if(isLinearLayoutManager)
                    ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
                else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)

    }
    //옵션 메뉴를 확장하고 추가 설정 수행하는 곳
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menuInflater: 메뉴 XML 파일을 Menu 객체로 인스턴스화 하는 클래스.
        menuInflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
        return true
    }
    //메뉴를 탭할 때 마다 호출됨 id가 action_switch_layout 메뉴 항목과 일치하면 isLinearLayoutManager의 값을 무효화
    //그런 다음 chooseLayout() 랑 setIcon()을 실행해 UI를 업데이트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager //결과값 변경
                chooseLayout() //그리드 or 선형 변경
                setIcon(item)
                return true
            }
            //otherwise는 아무것도 안함.
            else -> super.onOptionsItemSelected(item)
        }
    }


}
