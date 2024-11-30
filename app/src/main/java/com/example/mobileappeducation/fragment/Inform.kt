package com.example.mobileappeducation.fragment

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val list = listOf(
    "Русский язык",
    "Литература",
    "Алгебра",
    "Геометрия",
    "Английский язык",
    "Физика",
    "Биология",
    "Химия",
    "Информатика и ИКТ",
    "География",
    "История",
    "Основы безопасности и жизнедеятельности",
    "Обществознание",
    "Экономика",
    "Программирование"
)
@Composable
fun Inform(){
    MyApp(list=list)

}
@Composable
fun MyApp(modifier: Modifier = Modifier, list: List<String>) {
    Column(modifier.fillMaxSize()) {
        Text(text="Каталог курсов", fontWeight = FontWeight.Bold,fontSize=18.sp,
            modifier=Modifier.padding(18.dp))

        val textState = remember {
            mutableStateOf(TextFieldValue(""))
        }
        SearchView(state = textState, placeHolder = "Search here...", modifier = modifier)

        val searchedText = textState.value.text

        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(items = list.filter {
                it.contains(searchedText, ignoreCase = true)
            }, key = {it}) {item ->
                ColumnItem(item = item)
            }
        }
    }
}

@Composable
fun ColumnItem(item: String) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = item, modifier = Modifier.padding(vertical = 10.dp), fontSize = 20.sp)
        Divider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    state: MutableState<TextFieldValue>,
    placeHolder: String,
    modifier: Modifier
) {
    TextField(
        value = state.value,
        onValueChange = {value->
            state.value = value
        },
        modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(30.dp)),
        placeholder = {
            Text(text = placeHolder)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White
        ),
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black, fontSize = 20.sp
        )
    )

}


