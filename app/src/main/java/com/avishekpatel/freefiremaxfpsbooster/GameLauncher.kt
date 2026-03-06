package com.avishekpatel.freefiremaxfpsbooster

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

class GameLauncher(private val context: Context) {
    fun launchFreeFireMAX() {
        val packageName = "com.dts.freefiremax"
        if (isPackageInstalled(packageName)) {
            val intent = context.packageManager.getLaunchIntentForPackage(packageName)
            context.startActivity(intent)
        } else {
            // Handle the case where Free Fire MAX is not installed
            val playStoreIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
            context.startActivity(playStoreIntent)
        }
    }

    private fun isPackageInstalled(packageName: String): Boolean {
        return try {
            context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}