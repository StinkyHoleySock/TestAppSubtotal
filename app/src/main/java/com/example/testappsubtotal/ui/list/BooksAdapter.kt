package com.example.testappsubtotal.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
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
                tvAuthor.text = if (authors.isNotEmpty()) authors[0] else "No authors found"
                tvDate.text = response.volumeInfo?.publishedDate
                tvDescription.text = response.volumeInfo?.description
                tvTitle.text = response.volumeInfo?.title
                val imageLink = response.volumeInfo?.imageLinks?.thumbnail
                ivBook.load(imageLink)
                Log.d("develop", "link: $imageLink")
                clContainer.setOnClickListener {
                    Log.d("develop", "item: ${binding.tvTitle.text}")
                    bookClickListener(response)
                }
            }
        }
    }

    override fun getItemCount() = list.size
}