package com.example.composelogin.viewModel


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelogin.model.RandomUser
import com.example.composelogin.network.ApiService
import kotlinx.coroutines.launch

class RandomUserViewModel: ViewModel() {
    var randomUserResponse: List<RandomUser> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getRandomUserList(){
        viewModelScope.launch {
            val apiService =ApiService.getInstance()
            try {
                val randomUserList = apiService.getRandomUsers()
                randomUserResponse = randomUserList
            }
            catch (e: Exception){
                errorMessage =e.message.toString()
            }
        }
    }
}