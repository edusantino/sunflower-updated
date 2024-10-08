/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.samples.apps.sunflower.usecase.SearchResultUseCase
import com.santino.api.data.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class GalleryViewModelImpl(
    savedStateHandle: SavedStateHandle,
    private val searchResultUseCase: SearchResultUseCase
) : GalleryViewModel() {

    private var queryString: String? = savedStateHandle["plantName"]

    private val _plantPictures = MutableStateFlow<PagingData<UnsplashPhoto>?>(null)
    val plantPictures: Flow<PagingData<UnsplashPhoto>> get() = _plantPictures.filterNotNull()

    init {
        refreshData()
    }

    override fun refreshData() {
        viewModelScope.launch {
            try {
                _plantPictures.value = searchResultUseCase.execute(queryString ?: "").cachedIn(viewModelScope).first()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}