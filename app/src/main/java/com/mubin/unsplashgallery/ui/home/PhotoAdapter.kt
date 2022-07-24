package com.mubin.unsplashgallery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mubin.unsplashgallery.api.models.bindingModel.PhotoLinkAuthor
import com.mubin.unsplashgallery.api.models.responseModel.UnsplashPhotoItem
import com.mubin.unsplashgallery.databinding.ItemGalleyBinding


class PhotoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList: MutableList<UnsplashPhotoItem> = mutableListOf()

    var onItemClick: ((model:UnsplashPhotoItem, position: Int) -> Unit)? = null

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        val binding = ItemGalleyBinding.inflate(layoutInflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder){
            holder.bind(dataList[position])
        }
    }

    inner class PhotoViewHolder(private var binding: ItemGalleyBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(photoData: UnsplashPhotoItem) {
            try {

                binding.photoDetails = PhotoLinkAuthor(photoData.urls?.small!!, photoData.user?.username!!)

                binding.root.setOnClickListener {

                    onItemClick?.invoke(photoData, adapterPosition)

                }

            } catch (ignored: Exception) {

            }
        }

    }


    fun initLoad(list: List<UnsplashPhotoItem>){
        dataList.clear()
        dataList.addAll(list)
        notifyItemInserted(0)
    }

    fun pagingLoad(list: List<UnsplashPhotoItem>) {
        val currentIndex = dataList.size
        dataList.addAll(list)
        notifyItemInserted(currentIndex)
    }


}