package com.example.composelogin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composelogin.model.LoginFormEvent
import com.example.composelogin.ui.theme.ComposeLoginTheme
import com.example.composelogin.ui.theme.black
import com.example.composelogin.viewModel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = viewModel<LoginViewModel>()
                    val state = viewModel.state
                    val context = LocalContext.current
                    LaunchedEffect(key1 = context ){
                        viewModel.validationEvents.collect{ event ->
                            when(event){
                                is LoginViewModel.ValidationEvent.Success ->{
                                    val intent = Intent(applicationContext, UserMainActivity::class.java )
                                    startActivity(intent)
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        verticalArrangement = Arrangement.Center

                    ) {
                        Text(
                            text = "Login",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            )

                        Spacer(modifier = Modifier.padding(40.dp))
                        OutlinedTextField(
                            value = state.email,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black
                            ),
                            shape = RoundedCornerShape(10.dp),
                            onValueChange = {
                                viewModel.onEvent(LoginFormEvent.EmailChanged(it))
                                            },
                            isError = state.emailError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = "Email",
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            )
                        )
                        if(state.emailError != null){
                            Text(
                                text = state.emailError,
                                color = MaterialTheme.colors.error,
                                modifier = Modifier.align(Alignment.Start),
                                fontSize = 12.sp
                            )

                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = state.password,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black


                            ),
                            shape = RoundedCornerShape(10.dp),
                            onValueChange = {
                                viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
                            },
                            isError = state.passwordError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = "Password",
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = PasswordVisualTransformation()
                        )
                        if(state.passwordError != null){
                            Text(
                                text = state.passwordError,
                                color = MaterialTheme.colors.error,
                                fontSize = 12.sp,
                                modifier = Modifier.align(Alignment.Start)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(

                            onClick = {
                                viewModel.onEvent(LoginFormEvent.Login)
                            },

                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Login",
                                fontSize = 16.sp,
                            )

                        }

                    }
                }
            }
        }
    }
}