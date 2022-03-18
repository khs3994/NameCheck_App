package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.ScoreItemBinding
import com.example.presentation.viewmodel.MainViewModel

class ScoreRecyclerViewAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<ScoreRecyclerViewAdapter.ScoreRecyclerViewHolder>() {

    inner class ScoreRecyclerViewHolder(val binding : ScoreItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ScoreItemBinding>(
            layoutInflater,
            R.layout.score_item,
            parent,
            false
        )
        return ScoreRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScoreRecyclerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return
    }
}