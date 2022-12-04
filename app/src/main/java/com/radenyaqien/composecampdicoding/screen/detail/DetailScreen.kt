package com.radenyaqien.composecampdicoding.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.radenyaqien.composecampdicoding.data.RickMortyRepository
import com.radenyaqien.composecampdicoding.data.model.RickMorty

@Composable
fun DetailScreen(
    id: Int,
    onBackClicked: () -> Unit,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        modelClass = DetailViewModel::class.java,
        factory = ViewModelFactory(
            id,
            RickMortyRepository()
        )
    )
) {
    val model by viewModel.state
    Scaffold(topBar = {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(model?.name ?: "Detail")
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            })
    }) {

        if (model != null) {
            DetailContent(rickMorty = checkNotNull(model))
        } else {
            Text(text = "Data not Found")
        }
    }
}


@Composable
fun DetailContent(rickMorty: RickMorty) {
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = rickMorty.image,
            contentDescription = rickMorty.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 24.dp)
                .clip(CircleShape)
                .align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextContainer(judul = "Name", isi = rickMorty.name)
        TextContainer(judul = "Gender", isi = rickMorty.gender)
        TextContainer(judul = "Species", isi = rickMorty.species)
        TextContainer(judul = "Status", isi = rickMorty.status)
    }
}

@Composable
fun TextContainer(judul: String, isi: String) {
    Row(Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = judul,
            modifier = Modifier.weight(0.5f)
        )
        Text(
            text = " : "
        )
        Text(
            text = isi,
            modifier = Modifier.weight(1f)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}