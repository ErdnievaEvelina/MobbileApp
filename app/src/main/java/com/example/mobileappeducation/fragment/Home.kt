package com.example.mobileappeducation.fragment


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.mobileappeducation.R
import com.example.mobileappeducation.ui.theme.Purple


@Composable
fun Home(){
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter= painterResource(id= R.drawable.photo),contentDescription = null)
        Spacer(modifier=Modifier.padding(4.dp))
        CardExample(description)
    }
}
val description= "С радостью распахиваем перед тобой двери этого волшебного мира — нашего обучающего приложения, " +
        "где каждый шаг ведет к новым открытиям! Здесь, среди ярких красок и увлекательных идей, ты " +
        "станешь исследователем, способным покорить вершины знания и разгадывать тайны, " +
        "скрытые в глубинах увлекательных уроков." +
        "Дерзай, исследуй и созидай! Добро пожаловать в мир, " +
        "где обучение становится увлекательным приключением!"
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
