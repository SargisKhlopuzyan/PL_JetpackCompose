package com.sargis.khlopuzyan.presentation.permission

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsScreen() {
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver() { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                permissionsState.launchMultiplePermissionRequest()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        permissionsState.permissions.forEach { perm ->
            when (perm.permission) {
                Manifest.permission.CAMERA -> {
                    when {
                        perm.status.isGranted -> {
                            Text("Camera permission accepted")
                        }

                        perm.status.shouldShowRationale -> {
                            Text("Camera permission needed")
                        }

                        perm.isPermanentlyDenied() -> {
                            Text("Camera permission is permanently denied. You can enable it in app settings")
                        }
                    }
                }

                Manifest.permission.RECORD_AUDIO -> {
                    when {
                        perm.status.isGranted -> {
                            Text("Record audio permission accepted")
                        }

                        perm.status.shouldShowRationale -> {
                            Text("Record audio permission needed")
                        }

                        perm.isPermanentlyDenied() -> {
                            Text("Record audio permission is permanently denied. You can enable it in app settings")
                        }
                    }
                }
            }
        }
    }
}