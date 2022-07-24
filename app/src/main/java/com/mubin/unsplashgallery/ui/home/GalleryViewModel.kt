package com.mubin.unsplashgallery.ui.home

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.mubin.unsplashgallery.api.models.responseModel.UnsplashPhotoItem
import com.mubin.unsplashgallery.helper.GetBitmap
import com.mubin.unsplashgallery.helper.Session
import com.mubin.unsplashgallery.repository.AppRepository
import com.mubin.unsplashgallery.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel
@Inject
constructor(private val repository: AppRepository, application: Application) : BaseViewModel(application) {

    private val _shouldAskPermission = MutableLiveData<Boolean>()
    val shouldAskPermission: LiveData<Boolean> = _shouldAskPermission

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _photoList = MutableLiveData<List<UnsplashPhotoItem>>()
    val photoList: LiveData<List<UnsplashPhotoItem>> = _photoList

    init {
        getPhotoList(1)
    }

    fun getPhotoList(page: Int) {
        _dataLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPhotos(page)
            withContext(Dispatchers.Main) {
                _dataLoading.value = false
                when (response) {
                    is NetworkResponse.Success -> {
                        _photoList.value = response.body
                    }
                    is NetworkResponse.ServerError -> {
                        _errorMessage.value = "Sorry! Server Error occurred, Please try again later!"
                    }
                    is NetworkResponse.NetworkError -> {
                        _errorMessage.value = "Sorry! Internet connection error occurred."
                    }
                    is NetworkResponse.UnknownError -> {
                        _errorMessage.value = "Unknown error occurred, Please try again."
                    }
                }
            }
        }
    }

    fun onShareClicked(authorName: String, photoLink: String) {

        _shouldAskPermission.value = false

        val getBitmap = GetBitmap()

        val title = "$authorName+${System.currentTimeMillis()}"

        if (Session.isWrite && Session.isRead) {
            getBitmap.shareImage(title, photoLink, context)
        } else {
            _shouldAskPermission.value = true
        }


    }

    fun onSaveClicked(authorName: String, photoLink: String) {

        downloadImageNew(authorName+"${System.currentTimeMillis()}", photoLink)

    }

    private fun downloadImageNew(filename: String, downloadUrlOfImage: String) {
        try {
            val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
            val downloadUri: Uri = Uri.parse(downloadUrlOfImage)
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator.toString() + filename + ".jpg"
                )
            dm!!.enqueue(request)
            Toast.makeText(context, "Image download started.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context.applicationContext, "Image download failed.", Toast.LENGTH_SHORT).show()
        }
    }

}