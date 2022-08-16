package com.rpfcoding.mykaraokebrowser.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rpfcoding.mykaraokebrowser.data.local.SongService
import com.rpfcoding.mykaraokebrowser.data.local.dao.SongEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val songService: SongService
) : ViewModel() {

    val songs = songService.getSome()

    private val _searchedSongs = MutableStateFlow<PagingData<SongEntity>>(PagingData.empty())
    val searchedSongs = _searchedSongs.asStateFlow()

    private val _switchPagingDataEvent = MutableSharedFlow<Boolean>()
    val switchPagingDataEvent = _switchPagingDataEvent.asSharedFlow()

    var searchText by mutableStateOf("")
        private set

    fun onSearchTextChange(text: String) {
        searchText = text

        if(searchText.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                songService.searchByNameOrSinger(searchText).cachedIn(viewModelScope).collect {
                    _searchedSongs.value = it

                    delay(1000L)
                    _switchPagingDataEvent.emit(true)
                }
            }
        } else {
            viewModelScope.launch {
                _switchPagingDataEvent.emit(false)
            }
        }
    }
}