package com.juansanz.themovieapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.juansanz.themovieapp.ui.DetailViewModel
import com.juansanz.themovieapp.ui.screens.common.ErrorText
import com.juansanz.themovieapp.ui.screens.model.Error
import com.juansanz.themovieapp.ui.screens.model.Movie
import com.juansanz.themovieapp.ui.theme.ThemoviedbTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Detail(
    onUpClick: () -> Unit,
    vm: DetailViewModel = DetailViewModel(),
) {
    val state by vm.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val imageVectorValid =
        if (state.movie?.favorite == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = state.movie?.title ?: "",
                onUpClick = onUpClick,
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = vm::onFavoriteClicked) {
                Icon(
                    imageVector = imageVectorValid,
                    contentDescription = "Mark as Favorite",
                )
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { padding ->
        state.movie?.let {
            MovieContent(
                movie = it,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(
    title: String,
    onUpClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    LargeTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onUpClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Main Screen",
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun MovieContent(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    val imageUrl = "https://image.tmdb.org/t/p/w780/${movie.backdropPath}"
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(text = movie.overview, modifier = Modifier.padding(16.dp))
        Text(
            text = buildAnnotatedString {
                Property("Original language", movie.originalLanguage)
                Property("Original title", movie.originalTitle)
                Property("Release date", movie.releaseDate)
                Property("Popularity", movie.popularity.toString())
                Property("Vote average", movie.voteAverage.toString(), true)
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp),
        )
    }
}

@Composable
private fun AnnotatedString.Builder.Property(
    name: String,
    value: String,
    end: Boolean = false,
) {
    withStyle(ParagraphStyle(lineHeight = 18.sp)) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$name: ")
        }
        append(value)
        if (!end) {
            append("\n")
        }
    }
}

@Preview
@Composable
private fun DetailPreview() {
    ThemoviedbTheme {
        Detail(
            onUpClick = {},
            vm = DetailViewModel(),
        )
    }
}
