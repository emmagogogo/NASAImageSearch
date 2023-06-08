package com.workday.takehometest

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.workday.takehometest.NASAImage.Companion.fromNASAResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

enum class NASAImageApiStatus { LOADING, ERROR, DONE }

class NASAImageSearchViewModel: ViewModel() {

    private val _status = MutableStateFlow(NASAImageApiStatus.DONE)
    val status = _status.asStateFlow()

    private val _images = mutableStateListOf<NASAImage>()
    val images : List<NASAImage> = _images

    fun searchNASAImage(q: String) {
        if (q.isBlank()) {
            return
        }

        viewModelScope.launch {
            _status.value = NASAImageApiStatus.LOADING
            // delay(2000)

            try {
                val result = NASAApi.retrofitService.getImages(q)
                _images.clear()
                _images.addAll(fromNASAResult(result))
                _status.value = NASAImageApiStatus.DONE
            } catch (ex: Exception) {
                Log.d("TAG", "searchNASAImage: ${ex.message}")
                _images.clear()
                _status.value = NASAImageApiStatus.ERROR
            }
        }
    }
}