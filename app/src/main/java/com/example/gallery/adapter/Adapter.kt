package com.example.gallery.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.R
import com.example.gallery.databinding.ItemListBinding

class Adapter(private var listener: Listener) :
    RecyclerView.Adapter<Adapter.AdapterHolder>() {

    private val imageList = arrayListOf<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return AdapterHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.bind(imageList[position], listener)
    }

    override fun getItemCount() = imageList.size


    class AdapterHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemListBinding.bind(item)

        fun bind(mainImage: Uri, listener: Listener) = with(binding) {
            imageView.setImageURI(mainImage)
            imageView.visibility = INVISIBLE
            itemView.setOnClickListener {
                if (!itemView.isVisible) {
                    listener.onClick(mainImage)
                    imageView.visibility = VISIBLE
                } else  {
                    listener.deleteClick(mainImage)
                    imageView.visibility = INVISIBLE
                }
            }
        }
    }

    interface Listener {
        fun onClick(mainImage: Uri)
        fun deleteClick(mainImage: Uri)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addImage(image: Uri) {
        this.imageList.addAll(listOf(image))
        notifyDataSetChanged()
    }
}