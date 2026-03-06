package com.avishekpatel.freefiremaxfpsbooster

import android.app.ActivityManager
import android.content.Context

class BackgroundAppManager(private val context: Context) {
    
    private val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    
    fun getRunningApps(): List<String> {
        val runningApps = mutableListOf<String>()
        val tasks = activityManager.getRunningAppProcesses()
        
        tasks?.forEach { processInfo ->
            runningApps.add(processInfo.processName)
        }
        
        return runningApps
    }
    
    fun killBackgroundApps(): Int {
        val tasks = activityManager.getRunningAppProcesses() ?: return 0
        var killedCount = 0
        
        for (processInfo in tasks) {
            // Exclude system apps and Free Fire MAX
            if (!isSystemApp(processInfo.processName) && 
                processInfo.processName != "com.dts.freefireth" &&
                processInfo.processName != "com.dts.freefiremax") {
                
                android.os.Process.killProcess(processInfo.pid)
                killedCount++
            }
        }
        
        return killedCount
    }
    
    private fun isSystemApp(packageName: String): Boolean {
        val systemPackages = listOf(
            "com.android",
            "android.",
            "system",
            "com.google"
        )
        
        return systemPackages.any { packageName.startsWith(it) }
    }
}