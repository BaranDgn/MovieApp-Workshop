package com.example.moviews.view

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import coil.compose.rememberImagePainter
import com.example.moviews.model.MovieResponse
import com.example.moviews.model.MovieResponseItems
import com.example.moviews.model.myListModel
import com.example.moviews.util.Constrants.LOGO_URL
import com.example.moviews.util.Resource
import com.example.moviews.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    orginal_title : String,
    popularity :Double,
    release_date : String,
    vote_average : Double,
    overview: String,
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel(),
){



    val movieItem = produceState<Resource<MovieResponse>>(initialValue = Resource.Loading()){
        value = viewModel.getMovie()
    }.value

    val ctx = LocalContext.current

    val myListem : List<myListModel>
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

                Toast.makeText(ctx, "It's Added to your list", Toast.LENGTH_SHORT).show()

            }, Modifier.padding(330.dp,0.dp,0.dp,60.dp)) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {

    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            when(movieItem){

                is Resource.Success -> {
                    val selectedMovie = movieItem.data!!.results[7]

                   Image(painter = rememberImagePainter(data = LOGO_URL+selectedMovie.poster_path),
                        contentDescription = selectedMovie.original_title,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(300.dp, 400.dp)
                            .border(2.dp, Color.Gray)
                    )

                    Text(text = orginal_title,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue,
                        textAlign = TextAlign.Center)

                    Text(text = "Popularity: "+popularity.toString(),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Thin,
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Center)

                    Text(text = "Release Date: "+ release_date,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Thin,
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Center)

                    Text(text = "vote: "+vote_average.toString(),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Thin,
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Center)

                    Text(text = overview,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(6.dp),
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Center)
                }

                is Resource.Error -> {
                    Text(text = movieItem.message!!)
                }
                is Resource.Loading ->{
                    CircularProgressIndicator(color = MaterialTheme.colors.secondary)
                }
            }


        }


    }

    }


}

@Composable
fun CoilImage(
    selected : MovieResponseItems
){
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp),
            contentAlignment = Alignment.Center
    ){
        val painter = rememberImagePainter(
            data = selected.poster_path ,
            builder = {})
    
        Image(painter = painter, contentDescription = selected.original_title)
    }
}
