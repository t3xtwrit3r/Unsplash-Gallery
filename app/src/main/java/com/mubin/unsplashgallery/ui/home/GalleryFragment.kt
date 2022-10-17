package com.mubin.unsplashgallery.ui.home

import android.Manifest
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fondesa.kpermissions.*
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.extension.send
import com.mubin.unsplashgallery.R
import com.mubin.unsplashgallery.databinding.FragmentGalleryBinding
import com.mubin.unsplashgallery.helper.motion_detection.MotionDetector
import com.mubin.unsplashgallery.helper.motion_detection.MotionDetectorCallback
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding

    private lateinit var motionDetector: MotionDetector

    private var isMinimized: Boolean = false

    private val galleryViewModel: GalleryViewModel by viewModels()

    private var galleryAdapter = PhotoAdapter()

    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        //initData()
    }

    private fun initViews() {

        //galleryAdapter.setHasStableIds(true)
        val surfaceView = view?.findViewById<SurfaceView>(R.id.surfaceView)
        motionDetector = MotionDetector(requireContext(),surfaceView)
        motionDetector.setCheckInterval(1000)
        motionDetector.setLeniency(60)
        motionDetector.setMinLuma(1500)
        motionDetector.setMotionDetectorCallback(object : MotionDetectorCallback {
            override fun onMotionDetected() {

                Toast.makeText(context, "Motion Detected", Toast.LENGTH_SHORT).show()
            }

            override fun onTooDark() {
                Toast.makeText(context, "low light", Toast.LENGTH_SHORT).show()
            }
        })

//        with(binding.galleryRv) {
//            setHasFixedSize(true)
//            isNestedScrollingEnabled = false
//            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
//            adapter = galleryAdapter
//            animation = null
//            itemAnimator = null
//        }
//
//        binding.galleryRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (!binding.galleryRv.canScrollVertically(1)) {
//                    currentPage += 1
//                    galleryViewModel.getPhotoList(currentPage)
//                }
//            }
//        })
//
//        galleryAdapter.onItemClick = { photoData, position ->
//
//            val bundle = bundleOf("photoLink" to photoData.urls?.regular, "authorName" to photoData.user?.username)
//
//            findNavController().navigate(R.id.action_galleryFragment_to_viewPhotoFragment, bundle)
//
//        }

    }

//    private fun initData() {
//
//        galleryViewModel.photoList.observe(viewLifecycleOwner, Observer { photoList ->
//
//            if (currentPage == 1){
//                galleryAdapter.initLoad(photoList)
//            } else {
//                galleryAdapter.pagingLoad(photoList)
//            }
//
//        })
//
//        galleryViewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
//            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
//        })
//
//    }

    override fun onResume() {
        super.onResume()
        motionDetector.onResume()
        if (isMinimized) {
            Toast.makeText(context,"You Cheated", Toast.LENGTH_SHORT).show();
        } else {
            if (motionDetector.checkCameraHardware()) {
                Toast.makeText(context,"Camera found", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,"No camera available", Toast.LENGTH_SHORT).show();
            }
        }

    }

    override fun onPause() {
        super.onPause()
        motionDetector.onPause()
        isMinimized = true
    }


}