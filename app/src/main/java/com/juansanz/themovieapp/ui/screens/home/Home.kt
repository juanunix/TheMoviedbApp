package com.juansanz.themovieapp.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.juansanz.themovieapp.R
import com.juansanz.themovieapp.ui.MainViewModel
import com.juansanz.themovieapp.ui.screens.Screen
import com.juansanz.themovieapp.ui.screens.common.ErrorText
import com.juansanz.themovieapp.ui.screens.common.Loading
import com.juansanz.themovieapp.ui.screens.model.Error
import com.juansanz.themovieapp.ui.screens.model.Movie
import com.juansanz.themovieapp.ui.theme.ThemoviedbTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    vm: MainViewModel = MainViewModel(),
    onMovieClick: (Movie) -> Unit,
) {
    /*PermissionRequestEffect(Manifest.permission.ACCESS_COARSE_LOCATION) {
        vm.onUiReady()
    }*/

    val state by vm.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Screen {
        Scaffold(
            topBar = { HomeTopAppBar(scrollBehavior = scrollBehavior) },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        ) { padding ->

            if (state.loading) {
                Loading(modifier = Modifier.padding(padding))
            }

            state.movies?.let {
                MoviesGrid(
                    movies = it,
                    onMovieClick = onMovieClick,
                    modifier = Modifier.padding(padding),
                )
            }

            state.error?.let { error ->
                ErrorText(
                    error = error as Error,
                    modifier = Modifier.padding(padding),
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HomeTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        scrollBehavior = scrollBehavior,
    )
}

@Preview
@Composable
private fun HomePreview() {
    ThemoviedbTheme {
        Home(
            onMovieClick = { },
            vm = MainViewModel(),
        )
    }
}
