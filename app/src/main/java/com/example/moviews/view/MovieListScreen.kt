package com.example.moviews.view


import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.moviews.R
import com.example.moviews.StoreUser
import com.example.moviews.login.viewmodel.LoginViewModel
import com.example.moviews.model.MovieResponseItems
import com.example.moviews.util.Constrants
import com.example.moviews.viewmodel.MovieListViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel(),
    loginViewModel : LoginViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    //loginViewModel.logout()
    //navController.navigate("login_screen")
    //Toast.makeText(ctx, "It's logged out", Toast.LENGTH_SHORT).show()

    //New ekleme
    val dataStore = StoreUser(ctx)
    //eklenen
    val savedEmail = dataStore.getEmail.collectAsState(initial ="")


    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "MovieApp Workshop", color = MaterialTheme.colors.primary)},
              /*  navigationIcon = {
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(ImageVector.vectorResource(id = R.drawable.movie_loginicon), null, Modifier.width(8.dp).height(8.dp))
                    }
                },*/
                actions={
                    IconButton(onClick = {
                        loginViewModel.logout()
                        navController.navigate("login_screen")
                        Toast.makeText(ctx, "It's logged out", Toast.LENGTH_SHORT).show() })
                    {
                        Icon(ImageVector.vectorResource(id = R.drawable.log_out_icon), null)
                    }

                },
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.primaryVariant,
                elevation = 10.dp
            )
        }
    ) {
        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxSize()
        ) {

            Column {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp, 4.dp, 4.dp, 0.dp)
                    ){
                    Text(
                        text = "Movies", modifier = Modifier
                            .padding(10.dp, 10.dp, 0.dp, 0.dp),
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant
                    )
                    //Spacer(modifier = Modifier.width(135.dp))
                    Box(contentAlignment = Alignment.TopEnd){
                        Text(
                            text = savedEmail.value!!,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .padding(0.dp, 10.dp, 10.dp, 0.dp),
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }


                }

                Spacer(modifier = Modifier.height(4.dp))
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp), "Search"
                ) {
                    viewModel.searchMovieList(it)
                }
                Spacer(modifier = Modifier.height(8.dp))
                MovieList(navController = navController, viewModel = viewModel)
            }

        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint:String = "",
    onSearch : (String) -> Unit = {}
){
    var text by remember{
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier){
    BasicTextField(value =  text, onValueChange ={
        text = it
        onSearch(it)
    },  maxLines = 1,
        singleLine = true,
        textStyle = TextStyle.Default,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, CircleShape)
            .background(Color.White, CircleShape)
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .onFocusChanged {
                isHintDisplayed = it.isFocused != true && text.isEmpty()
            }
        )
    if(isHintDisplayed){
        Text(text = hint, color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
        }
    }
}


//
@Composable
fun MovieRow(
    navController: NavController,
    movie : MovieResponseItems
){


    val encodedUrl = URLEncoder.encode("${movie.poster_path}", StandardCharsets.UTF_8.toString())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .clickable {
                navController.navigate(
                    //"movie_detail_screen/${movie.original_title}/${movie.popularity}"

                    "movie_detail_screen/${movie.original_title}/${movie.popularity}/${movie.release_date}/${movie.vote_average}/${movie.overview}/${encodedUrl}"
                )
            }
    ) {

        CardView(painter = rememberImagePainter(data = movie.backdrop_path), contentDescription = "", title = movie.original_title)
    }
}


@Composable
fun MovieListView(
    movies : List<MovieResponseItems>,
    navController: NavController)
{
    LazyColumn(contentPadding = PaddingValues(5.dp)){
        items(movies){ movie ->
            MovieRow(navController = navController, movie = movie)
        }
    }
}


@Composable
fun MovieList(
    navController: NavController,
    viewModel: MovieListViewModel= hiltViewModel()
){

    val movieList by remember {
        viewModel.movieList
    }
    val errorMessage by remember {
        viewModel.errorMessage
    }
    val isLoading by remember {
        viewModel.isLoading
    }

    MovieListView(movies = movieList, navController = navController)
    
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()){
        if(isLoading){
            //yuklendigini gostermek icin
            CircularProgressIndicator(color = MaterialTheme.colors.secondary)
        }
        if(errorMessage.isNotEmpty()){
            //internet bir anlıgına calısmadıgında bile verileri indirebilir
            RetryView(error = errorMessage) {
                viewModel.loadMovies()
            }

        }
    }
}

@Composable
fun RetryView(
    error : String,
    onRetry : () -> Unit
){
    Column() {
        Text(text =error , color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { onRetry() },
               modifier = Modifier.align(Alignment.CenterHorizontally)){
            Text(text = "Retry")
        }
    }
}