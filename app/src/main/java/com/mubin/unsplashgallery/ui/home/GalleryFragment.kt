package com.mubin.unsplashgallery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mubin.unsplashgallery.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding

    private val galleryViewModel: GalleryViewModel by viewModels()

    private var galleryAdapter = PhotoAdapter()

    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(layoutInflater).apply {
            viewModel = galleryViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    private fun initViews() {
        galleryAdapter.setHasStableIds(true)
        with(binding.galleryRv) {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = galleryAdapter
            animation = null
            itemAnimator = null
        }
        binding.galleryRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.galleryRv.canScrollVertically(1)) {
                    currentPage += 1
                    galleryViewModel.getPhotoList(currentPage)
                }
            }
        })
    }

    private fun initData() {

        galleryViewModel.photoList.observe(viewLifecycleOwner, Observer { photoList ->

            if (currentPage == 1){
                galleryAdapter.initLoad(photoList)
            } else {
                galleryAdapter.pagingLoad(photoList)
            }

        })

        galleryViewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        })

    }


}