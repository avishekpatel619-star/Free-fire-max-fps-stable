package com.avishekpatel.freefiremaxfpsbooster

import android.app.ActivityManager
import android.content.Context

class RAMCleanupManager(private val context: Context) {
    
    private val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    
    fun cleanRAM(): Long {
        val runtime = Runtime.getRuntime()
        val usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory()
        
        System.gc()
        
        val usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory()
        val freedMemory = usedMemoryBefore - usedMemoryAfter
        
        return freedMemory
    }
    
    fun getAvailableMemory(): Long {
        val runtime = Runtime.getRuntime()
        return runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory())
    }
    
    fun getTotalMemory(): Long {
        return Runtime.getRuntime().maxMemory()
    }
}