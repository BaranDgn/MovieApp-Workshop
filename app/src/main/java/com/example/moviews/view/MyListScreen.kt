package com.example.moviews.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.moviews.R
import com.example.moviews.model.MovieResponse
import com.example.moviews.model.MovieResponseItems
import com.example.moviews.util.Constrants.LOGO_URL
import com.example.moviews.util.Resource
import com.example.moviews.viewmodel.MyListViewModel

@Composable
fun MyListScreen(

) {


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.secondary,
                title = { Text("My List") }
            )
        }
    ) {
       // MyListRow(movieItem : MovieResponseItem)
    }


}

@Composable
fun MyListRow(
  movieItem : MovieResponseItems
){
    Box() {
        Row(modifier = Modifier.fillMaxWidth()) {

          /*  Image(painter = rememberImagePainter(data = R.drawable.movie_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(8.dp)
                    .size(60.dp, 80.dp)
                    .border(2.dp, Color.Gray))
            Spacer(modifier = Modifier.height(10.dp))
            Column() {

                Text(text = original_title,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(4.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    textAlign = TextAlign.Center)

                Text(text = .toString(),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(2.dp),
                    color = MaterialTheme.colors.primaryVariant,
                    textAlign = TextAlign.Center)*/


            Box(
                modifier = Modifier.padding(8.dp),
                contentAlignment = Alignment.CenterEnd

            ){
                Button(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Notifications, contentDescription = "remove")
                }
            }
        }
    }

}