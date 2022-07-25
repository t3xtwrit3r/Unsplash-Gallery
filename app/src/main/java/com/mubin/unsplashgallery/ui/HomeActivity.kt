package com.mubin.unsplashgallery.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.mubin.unsplashgallery.R
import com.mubin.unsplashgallery.databinding.ActivityMainBinding
import com.mubin.unsplashgallery.helper.Session
import com.mubin.unsplashgallery.ui.home.HomeCommunicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HomeCommunicator {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isCheckPermission()
        } else {
            isCheckPermissionBelow23()
        }
        initNavGraph()
        initToolbar()

    }

    private fun initNavGraph() {
        navController = findNavController(R.id.navHostFragment)
    }

    private fun initToolbar() {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun setToolbarTitle(title: String){

        binding.toolbar.title = title

    }

    override fun askPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isCheckPermission()
        } else {
            isCheckPermissionBelow23()
        }

    }

    private fun isCheckPermissionBelow23() {
        val permissionRead = PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val permissionWrite = PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permissionRead == PermissionChecker.PERMISSION_GRANTED && permissionWrite == PermissionChecker.PERMISSION_GRANTED) {
            Session.isRead = true
            Session.isWrite = true
        } else {
            Toast.makeText(this, "Storage Permission is needed.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isCheckPermission(): Boolean {
        val permission1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val permission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return when {
            permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED -> {
                true
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) || shouldShowRequestPermissionRationale(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                requestMultiplePermissions.launch(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                )
                false
            }
            else -> {
                requestMultiplePermissions.launch(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                )
                false
            }
        }
    }

    private val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        permissions.entries.forEach { permission ->
            if (permission.key == Manifest.permission.READ_EXTERNAL_STORAGE) {
                Session.isRead = permission.value
            }
            if (permission.key == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                Session.isWrite = permission.value
            }
        }
    }

}