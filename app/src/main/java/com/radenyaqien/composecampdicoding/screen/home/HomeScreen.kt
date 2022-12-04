package com.radenyaqien.composecampdicoding.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.radenyaqien.composecampdicoding.data.RickMortyRepository
import com.radenyaqien.composecampdicoding.data.model.RickMorty


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    onClick: (RickMorty) -> Unit,
    navigateToAboutUs: () -> Unit,
    viewModel: HomeViewModel = viewModel(
        modelClass = HomeViewModel::class.java,
        factory = ViewModelFactory(
            RickMortyRepository()
        )
    )
) {

    val list by viewModel.state.collectAsStateWithLifecycle()
    val query by viewModel.query
    val searchWidgetState by viewModel.searchBarWidgetState
    Scaffold(
        topBar = {
            MainAppBar(
                searchBarWidgetState = searchWidgetState,
                searchTextState = query,
                onTextChange = viewModel::search,
                onCloseClicked = {
                    viewModel.updateSearchWidgetState(newValue = SearchBarWidgetState.CLOSED)
                },
                onSearchClicked = viewModel::search,
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchBarWidgetState.OPENED)
                },
                navigateToAboutUs = navigateToAboutUs
            )
        }
    ) {
        HomeContent(list, onClick)
    }
}

@Composable
private fun HomeContent(
    list: List<RickMorty>,
    onClick: (RickMorty) -> Unit
) {
    if (list.isEmpty()) {
        Text(text = "Character Not Found")
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(list) {  rickMorty ->
                ItemRickMorty(rickMorty = rickMorty, onClick)
            }
        }
    }

}

@Composable
fun ItemRickMorty(rickMorty: RickMorty, onClick: (RickMorty) -> Unit) {
    Row(modifier = Modifier.clickable {
        onClick(rickMorty)
    }) {
        AsyncImage(
            model = rickMorty.image,
            contentDescription = rickMorty.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = rickMorty.id.toString())
            Text(text = rickMorty.name)
        }
    }

}

@Composable
fun MainAppBar(
    searchBarWidgetState: SearchBarWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    navigateToAboutUs: () -> Unit,
) {
    when (searchBarWidgetState) {
        SearchBarWidgetState.CLOSED -> {
            DefaultAppBar(
                onSearchClicked = onSearchTriggered,
                navigateToAboutUs = navigateToAboutUs
            )
        }
        SearchBarWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit,
    navigateToAboutUs: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Rick Morty"
            )
        },
        actions = {
            IconButton(
                onClick = navigateToAboutUs,
            ) {
                Icon(

                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "about_page"
                )
            }
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            ))
    }
}
