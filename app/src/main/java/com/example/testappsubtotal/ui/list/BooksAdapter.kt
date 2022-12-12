package com.example.testappsubtotal.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappsubtotal.R
import com.example.testappsubtotal.databinding.ItemBookBinding
import com.example.testappsubtotal.model.Items

class BooksAdapter(
    private val bookClickListener: (bookItem: Items) -> Unit
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {
    private var list: MutableList<Items> = mutableListOf()

    fun setData(data: List<Items>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(response: Items) {
            with(binding) {
                val authors = response.volumeInfo?.authors ?: listOf()
                tvAuthor.text = if (authors.isNotEmpty()) authors[0] else "Authors not found"
                tvDate.text = response.volumeInfo?.publishedDate ?: "Date not found"
                tvDescription.text = response.volumeInfo?.description ?: "Description not found"
                tvTitle.text = response.volumeInfo?.title ?: "Title not found"
                val imageLink = response.volumeInfo?.imageLinks?.thumbnail

                Glide.with(ivBook.context)
                    .load(imageLink)
                    .error(R.drawable.ic_empty_data)
                    .into(ivBook)

                clContainer.setOnClickListener {
                    bookClickListener(response)
                }
            }
        }
    }

    override fun getItemCount() = list.size
}