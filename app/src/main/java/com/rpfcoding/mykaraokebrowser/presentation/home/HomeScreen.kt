package com.rpfcoding.mykaraokebrowser.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.rpfcoding.mykaraokebrowser.data.local.dao.SongEntity
import kotlinx.coroutines.flow.collectLatest

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val lazyListState = rememberLazyListState()

    var songState by remember {
        mutableStateOf(viewModel.songs)
    }

    LaunchedEffect(key1 = viewModel.switchPagingDataEvent) {
        viewModel.switchPagingDataEvent.collectLatest { event ->
            songState = if (event) {
                viewModel.searchedSongs
            } else {
                viewModel.songs
            }
        }
    }

    val songs = songState.collectAsLazyPagingItems()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.searchText,
                onValueChange = viewModel::onSearchTextChange,
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                },
                placeholder = {
                    Text(text = "Search by title or singer")
                },
                label = {
                    Text(text = "Search by title or singer")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(
                    items = songs,
                    key = { song ->
                        song.id
                    }
                ) { song ->
                    song?.let {
                        SongItem(it)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }

    }
}

@Composable
fun SongItem(
    song: SongEntity
) {
    Card(
        elevation = 12.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Title: ${song.title}")
            Text(text = "Code: ${song.code}")
            Text(text = "Performed By: ${song.singer}")
        }
    }
}
