package com.mubin.unsplashgallery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mubin.unsplashgallery.api.models.responseModel.UnsplashPhotoItem
import com.mubin.unsplashgallery.databinding.ItemGalleyBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


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
            try {
                binding.photoIv.alpha = 0f
                Picasso.get().load(photoData.urls?.small).noFade().into(binding.photoIv, object: Callback {
                    override fun onSuccess() {
                        binding.photoIv.animate().setDuration(300).alpha(1f).start()
                    }

                    override fun onError(e: java.lang.Exception?) {

                    }

                })
            } catch (ignored: Exception) {

            }
        }

    }


    fun initLoad(list: List<UnsplashPhotoItem>){
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    fun pagingLoad(list: List<UnsplashPhotoItem>) {
        val currentIndex = dataList.size
        val newDataCount = list.size
        dataList.addAll(dataList)
        notifyItemRangeInserted(currentIndex, newDataCount)
    }


}