package com.example.moviews.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviews.R
import com.example.moviews.StoreUser
import com.example.moviews.login.viewmodel.LoginViewModel
import com.example.moviews.util.Resource
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    hintUser : String = "",
    hintPassword : String = ""
) {
    //Eklenen new
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreUser(context)

    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    val authResource = viewModel?.loginFlow?.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

  //  val state by viewModel.loading.collectAsState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary)

    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(bitmap = ImageBitmap.imageResource(id = R.drawable.movie_loginicon),
                    contentDescription = "registerIcon")
            }
            Spacer(modifier = Modifier.height(50.dp))

            var isHintDisplayed by remember {
                mutableStateOf(hintUser!="")
            }

            Box(){
                BasicTextField(value =  userEmail, onValueChange ={
                    userEmail = it
                    },
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle.Default,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, CircleShape)
                        .background(Color.White, CircleShape)
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .onFocusChanged {
                            isHintDisplayed = it.isFocused != true && userEmail.isEmpty()
                        },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                if(isHintDisplayed){
                    Text(text = hintUser, color = Color.LightGray,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Box(){

                var isHintDisplayedForPassword by remember {
                    mutableStateOf(hintPassword!="")
                }
                BasicTextField(
                    value =  userPassword,
                    onValueChange ={  userPassword = it },
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle.Default,

                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, CircleShape)
                        .background(Color.White, CircleShape)
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .onFocusChanged {
                            isHintDisplayedForPassword = it.isFocused != true && userPassword.isEmpty()
                        },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )

                if(isHintDisplayedForPassword){
                    Text(text = hintPassword, color = Color.LightGray,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier.padding(40.dp, 0.dp,40.dp, 0.dp)

            ){
                Button(
                    onClick = {
                        viewModel.loginUser(userEmail,userPassword)

                        //eklenen new
                        scope.launch{
                            dataStore.saveEmail(userEmail)
                        }
                        //viewmodel.getFile
                        //viewModel?.loginUser(email = userEmail, password=userPassword)
                      //  navController.navigate("bottom_bar")
                    },
                    modifier = Modifier
                        //.fillMaxWidth()
                        .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)

                ) {
                    Text(
                        text = "LOGIN",
                        fontSize = 22.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(64.dp))

            Row(
                horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(16.dp)
            )
            {

                Text(
                    text = "Don't you have an account?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(text = "Sign Up",
                    modifier = Modifier
                        .clickable {
                            navController.navigate("signup_screen")
                        },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant

                )
            }
            val ctx = LocalContext.current
            authResource?.value?.let{
                when(it){
                    is Resource.Error -> {
                        Toast.makeText(ctx,"Email or password might be wrong or cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        CircularProgressIndicator(color = MaterialTheme.colors.secondary)
                        //Log.e("loading", "login is loading")
                    }
                    is Resource.Success -> {
                        LaunchedEffect("",Unit){
                            Toast.makeText(ctx,"Welcome to movie app", Toast.LENGTH_SHORT).show()
                            navController.navigate("bottom_bar"){
                                popUpTo("login_screen"){inclusive = true}
                            }
                        }
                    }
                }
            }
        }
    }
}