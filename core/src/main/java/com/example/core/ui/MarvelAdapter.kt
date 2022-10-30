package com.example.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemListMarvelEventBinding
import com.example.core.domain.model.MarvelEvent

class MarvelAdapter : RecyclerView.Adapter<MarvelAdapter.ListViewHolder>() {

    private var listData = ArrayList<MarvelEvent>()
    var onItemClick: ((MarvelEvent) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<MarvelEvent>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_marvel_event, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMarvelEventBinding.bind(itemView)
        fun bind(data: MarvelEvent) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.thumbnail?.path + "." + data.thumbnail?.extension)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}