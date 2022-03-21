package com.example.presentation.widjet.extention

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


//리사이클러뷰를 쉽게 사용하기 위한 확장함수
fun RecyclerView.showVertical(context: Context){
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}