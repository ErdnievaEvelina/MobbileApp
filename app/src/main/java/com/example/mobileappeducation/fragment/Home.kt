package com.example.mobileappeducation.fragment


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobileappeducation.R
import com.example.mobileappeducation.ui.theme.Purple


@Composable
fun Home(navController: NavController){
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter= painterResource(id= R.drawable.photo),contentDescription = null)
        Spacer(modifier=Modifier.padding(4.dp))
        CardExample(description)
        Spacer(modifier=Modifier.padding(6.dp))
        TestGreeting()
        Spacer(modifier=Modifier.padding(5.dp))
        CardQuestion(navController = navController)
    }
}
val description= "С радостью распахиваем перед тобой двери этого волшебного мира — нашего обучающего приложения, " +
        "где каждый шаг ведет к новым открытиям! Здесь, среди ярких красок и увлекательных идей, ты " +
        "станешь исследователем, способным покорить вершины знания и разгадывать тайны, " +
        "скрытые в глубинах увлекательных уроков."

@Composable
fun CardExample(description:String) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .animateContentSize(),
            colors=CardDefaults.cardColors().copy(containerColor = Purple),
            onClick = { expanded = !expanded }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Дорогой Искатель Знаний!", fontStyle = FontStyle.Italic, color = Color.White)
                if (expanded) {
                    Text(
                        text = description,
                        modifier = Modifier.padding(top = 16.dp),
                        color = Color.White
                    )
                }
            }
        }
    }
}
@Composable
fun TestGreeting(){
    Column(modifier=Modifier.padding(5.dp)) {
        Text(text="Доступные тесты", fontStyle = FontStyle.Italic)
    }
}
@Composable
fun CardQuestion(navController: NavController){
    Card(shape= RoundedCornerShape(16.dp),
        onClick = {navController.navigate("QuizApp")}) {
        Row {
            Card(shape= CircleShape,
                modifier=Modifier.padding(all=4.dp).size(60.dp),
                colors=CardDefaults.
                cardColors().
                copy(containerColor = Color.White)) {
                Box(modifier=Modifier.fillMaxSize()){
                    Image(painter= painterResource(id=R.drawable.python),contentDescription = null,
                        modifier=Modifier.size(60.dp).align(Alignment.Center))
                }
            }
            Column(modifier=Modifier.weight(1f).padding(start=10.dp)
                .align(Alignment.CenterVertically)) {
                Text(text= "Python", fontStyle = FontStyle.Italic)
                Text(text="Тест")
            }
        }

    }
    Spacer(modifier=Modifier.height(8.dp))
    Card(shape= RoundedCornerShape(16.dp),
        onClick = {navController.navigate("QuizAppJava")}) {
        Row {
            Card(shape= CircleShape,
                modifier=Modifier.padding(all=4.dp).size(60.dp),
                colors=CardDefaults.
                cardColors().
                copy(containerColor = Color.White)) {
                Box(modifier=Modifier.fillMaxSize()){
                    Image(painter= painterResource(id=R.drawable.java),contentDescription = null,
                        modifier=Modifier.size(60.dp).align(Alignment.Center))
                }
            }
            Column(modifier=Modifier.weight(1f).padding(start=10.dp)
                .align(Alignment.CenterVertically)) {
                Text(text= "Java", fontStyle = FontStyle.Italic)
                Text(text="Тест")
            }
        }

    }
    Spacer(modifier=Modifier.height(8.dp))
    Card(shape= RoundedCornerShape(16.dp),
        onClick = {navController.navigate("QuizAppKotlin")}) {
        Row {
            Card(shape= CircleShape,
                modifier=Modifier.padding(all=4.dp).size(60.dp),
                colors=CardDefaults.
                cardColors().
                copy(containerColor = Color.White)) {
                Box(modifier=Modifier.fillMaxSize()){
                    Image(painter= painterResource(id=R.drawable.kotlin),contentDescription = null,
                        modifier=Modifier.size(60.dp).align(Alignment.Center))
                }
            }
            Column(modifier=Modifier.weight(1f).padding(start=10.dp)
                .align(Alignment.CenterVertically)) {
                Text(text= "Kotlin", fontStyle = FontStyle.Italic)
                Text(text="Тест")
            }
        }

    }
    Spacer(modifier=Modifier.height(8.dp))

}


