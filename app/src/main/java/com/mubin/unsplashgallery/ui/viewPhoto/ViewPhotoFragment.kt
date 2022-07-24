package com.mubin.unsplashgallery.ui.viewPhoto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mubin.unsplashgallery.api.models.bindingModel.PhotoLinkAuthor
import com.mubin.unsplashgallery.databinding.FragmentViewPhotoBinding
import com.mubin.unsplashgallery.ui.HomeActivity
import com.mubin.unsplashgallery.ui.home.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPhotoFragment : Fragment() {

    private lateinit var binding: FragmentViewPhotoBinding

    private val viewPhotoViewModel: GalleryViewModel by viewModels()

    private var photoLink = ""
    private var authorName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPhotoBinding.inflate(layoutInflater, container, false).apply{
            viewModel = viewPhotoViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle? = arguments
        arguments?.let {
            photoLink = bundle?.getString("photoLink") ?: ""
            authorName = bundle?.getString("authorName") ?: ""
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObserver()

    }

    private fun initObserver() {
        viewPhotoViewModel.shouldAskPermission.observe(viewLifecycleOwner, Observer { shouldAsk->

            if (shouldAsk){
                (activity as HomeActivity).askPermission()
            }

        })
    }

    private fun initViews() {
        (activity as HomeActivity).setToolbarTitle("© $authorName")
        binding.photoDetails = PhotoLinkAuthor(photoLink)
    }

}