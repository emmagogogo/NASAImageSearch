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

enum class NASAImageApiStatus { LOADING, ERROR, DONE, EMPTY }

class NASAImageSearchViewModel: ViewModel() {
    //_ mutable
    private val _status = MutableStateFlow(NASAImageApiStatus.DONE)
    // immutable
    val status = _status.asStateFlow()

    private val _images = mutableStateListOf<NASAImage>()
    val images : List<NASAImage> = _images

    fun searchNASAImage(q: String) {

        viewModelScope.launch {
            _status.value = NASAImageApiStatus.LOADING
            // delay(2000)

            try {
                val result = NASAApi.retrofitService.getImages(q)
                _images.clear()
                _images.addAll(fromNASAResult(result))
                if (_images.size > 0){
                    _status.value = NASAImageApiStatus.DONE
                } else {
                    _status.value = NASAImageApiStatus.EMPTY
                }

            } catch (ex: Exception) {
                Log.d("TAG", "searchNASAImage: ${ex.message}")
                _images.clear()
                _status.value = NASAImageApiStatus.ERROR
            }
        }
    }
}