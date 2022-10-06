package com.example.composelogin.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.composelogin.model.RandomUser

@Composable
fun RandomUserItem( randomUser: RandomUser){

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
            .height(110.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ){
        Surface() {
            Column (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .fillMaxHeight()


            ){
                Text(
                    text = randomUser.name,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = randomUser.username,
                    style = MaterialTheme.typography.caption,
                    modifier =  Modifier.background(Color.LightGray)
                        .padding(4.dp)
                )
                Text(
                    text = randomUser.email,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = randomUser.address,
                    style = MaterialTheme.typography.body2,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}