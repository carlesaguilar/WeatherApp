package dev.carlesav.weatherapp.presentation.utils

import androidx.activity.compose.ManagedActivityResultLauncher

fun checkAndRequestPermissions(
    launcher: ManagedActivityResultLauncher<Array<String>, MutableMap<String, Boolean>>,
    permissionsArray: Array<String>,
) {
    launcher.launch(permissionsArray)
}