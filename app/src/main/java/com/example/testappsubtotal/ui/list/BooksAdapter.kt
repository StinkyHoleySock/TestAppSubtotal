package com.example.testappsubtotal.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappsubtotal.databinding.ItemBookBinding
import com.example.testappsubtotal.model.Books

class BooksAdapter(private val bookClickListener: (bookItem: Books) -> Unit) :
    PagingDataAdapter<Books, BooksAdapter.ViewHolder>(DiffUtilCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }
    }

    inner class ViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val bookItem = getItem(bindingAdapterPosition)
                bookItem?.let { bookClickListener(it) }
            }
        }

        fun bind(response: Books, position: Int) {
            with(binding) {
                tvAuthor.text = response.items[position].volumeInfo?.authors.toString()
                tvDate.text = response.items[position].volumeInfo?.publishedDate
                tvDescription.text = response.items[position].volumeInfo?.description
                tvTitle.text = response.items[position].volumeInfo?.title
                Glide.with(ivBook).load(response.items[position].volumeInfo?.imageLinks?.thumbnail).into(ivBook)
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Books>() {
        override fun areItemsTheSame(oldItem: Books, newItem: Books): Boolean {
            return oldItem.items == newItem.items
        }

        override fun areContentsTheSame(oldItem: Books, newItem: Books): Boolean {
            return oldItem == newItem
        }
    }
}