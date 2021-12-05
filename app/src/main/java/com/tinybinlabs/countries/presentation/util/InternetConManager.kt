package com.tinybinlabs.countries.presentation.util

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner


class InternetConManager

    constructor(app : Application){

    private val conLiveData = InternetConLiveData(app)


    val isNetAvailable = mutableStateOf(false)

    fun registerConnectionObserver(lifecycleOwner: LifecycleOwner) {
        conLiveData.observe(lifecycleOwner, {
            Log.d("LiveData", "isNetAvailable: $it")
            isNetAvailable.value = it
        })
    }

    fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner) {
        conLiveData.removeObservers(lifecycleOwner)
    }


}