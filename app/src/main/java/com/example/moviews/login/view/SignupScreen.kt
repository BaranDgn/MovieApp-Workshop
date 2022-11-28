package com.example.moviews.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviews.R
import com.example.moviews.login.viewmodel.LoginViewModel
import com.example.moviews.util.Resource

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    hintName : String = "",
    hintUser : String = "",
    hintPassword : String = ""
){

        var userName by remember { mutableStateOf("") }
        var Email by remember { mutableStateOf("") }
        var Password by remember { mutableStateOf("") }

        val authResource = viewModel?.signupFlow?.collectAsState()

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
                    Image(bitmap = ImageBitmap.imageResource(id = R.drawable.movie_register_icon),
                        contentDescription = "registerIcon")
                }
                Spacer(modifier = Modifier.height(50.dp))
                var isHintDisplayed by remember {
                    mutableStateOf(hintUser!="")
                }

                //UserNAme
                Box() {
                    BasicTextField(value =  userName, onValueChange ={
                        userName = it
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
                                isHintDisplayed = it.isFocused != true && Email.isEmpty()
                            },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    if(isHintDisplayed){
                        Text(text = hintName, color = Color.LightGray,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))



                //userEmail
                Box(){
                    BasicTextField(value =  Email, onValueChange ={
                        Email = it
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
                                isHintDisplayed = it.isFocused != true && Email.isEmpty()
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

                    //userPassword
                    BasicTextField(value =  Password, onValueChange ={
                        Password = it
                    },  maxLines = 1,
                        singleLine = true,
                        textStyle = TextStyle.Default,
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(5.dp, CircleShape)
                            .background(Color.White, CircleShape)
                            .padding(horizontal = 20.dp, vertical = 12.dp)
                            .onFocusChanged {
                                isHintDisplayed = it.isFocused != true && Password.isEmpty()
                            },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )
                    if(isHintDisplayed){
                        Text(text = hintPassword, color = Color.LightGray,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                //Button
                Box(
                    modifier = Modifier.padding(40.dp, 0.dp,40.dp, 0.dp)

                ){
                    Button(
                        onClick = {
                            viewModel.signupUser(userName,Email,Password)
                            navController.navigate("login_screen")

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
                            text = "SIGN UP",
                            fontSize = 22.sp,
                            color = Color.White
                        )
                    }
                }
                authResource?.value?.let{
                    when(it){
                        is Resource.Error -> {
                            Log.e("error" , "error in login")
                        }
                        is Resource.Loading -> {
                            //CircularProgressIndicator( modifier = Modifier.)
                            Log.e("loading", "login is loading")
                        }
                        is Resource.Success -> {
                            LaunchedEffect("",Unit){
                                navController.navigate("bottom_bar"){
                                    popUpTo("signup_screen"){inclusive = true}
                                }
                            }
                        }
                    }
                }
            }

        }

    authResource.value?.let{
        when(it){
            is Resource.Error -> {

            }
            is
            Resource.Loading -> {

            }
            is Resource.Success -> {
                LaunchedEffect(Unit){
                    navController.navigate("login_screen"){
                        popUpTo("signup_screen"){inclusive=true}
                    }
                }
            }
        }

    }





}