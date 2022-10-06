package com.example.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composelogin.model.RandomUser
import com.example.composelogin.ui.theme.ComposeLoginTheme
import com.example.composelogin.view.RandomUserItem
import com.example.composelogin.view.UserListScreen
import com.example.composelogin.viewModel.RandomUserViewModel

class UserMainActivity : ComponentActivity() {
    val randomUserViewModel by viewModels<RandomUserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RandomUserList(randomUserList = randomUserViewModel.randomUserResponse)
                    randomUserViewModel.getRandomUserList()
                    UserListScreen()

                }

            }
        }
    }
}

@Composable
fun  RandomUserList(randomUserList: List<RandomUser>){

    LazyColumn{
        itemsIndexed(items = randomUserList){ _, item ->  RandomUserItem(randomUser = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeLoginTheme {
        val randomUser = RandomUser("dev", "joshua",
            "josh@gmail.com", "12 luke street")
        RandomUserItem(randomUser = randomUser)
    }
}