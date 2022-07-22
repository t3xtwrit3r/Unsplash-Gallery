package com.mubin.unsplashgallery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mubin.unsplashgallery.api.models.responseModel.UnsplashPhotoItem
import com.mubin.unsplashgallery.databinding.ItemGalleyBinding


class PhotoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList: MutableList<UnsplashPhotoItem> = mutableListOf()

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

        }

    }


    fun initLoad(dataList: List<UnsplashPhotoItem>){
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }


}