package com.example.composelogin.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserListScreen(){
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(
                text = "Search",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(10.dp))
            SearchBar(
                hint = "Search..",
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier= Modifier,
    hint:String ="",
    onSearch: (String) -> Unit = {}
){
    var text by  remember{
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier){
        BasicTextField(
            value =text,
            onValueChange ={
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .background(Color.LightGray, RoundedCornerShape(10.dp))
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = true
                }

        )
        if(isHintDisplayed){
            Text(
                text = hint,
                color = Color.Gray,
                modifier = Modifier
                    .padding(20.dp, vertical = 12.dp),

            )


        }
    }
}