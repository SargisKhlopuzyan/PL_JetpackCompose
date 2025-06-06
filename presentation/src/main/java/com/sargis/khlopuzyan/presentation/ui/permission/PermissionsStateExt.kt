package com.sargis.khlopuzyan.presentation.ui.permission

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isPermanentlyDenied() =
    !this.status.isGranted && !this.status.shouldShowRationale