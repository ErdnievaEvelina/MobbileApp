package com.example.mobileappeducation.fragment


import android.util.Log

import androidx.annotation.OptIn
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController


import coil.compose.AsyncImage
import com.example.mobileappeducation.model.Book
import com.example.mobileappeducation.ui.theme.Purple


@Composable
fun Profile(modifier: Modifier=Modifier,booksList: List<Book>, navController: NavController) {
    Column(modifier.fillMaxSize()) {
        val textState= remember {
            mutableStateOf(TextFieldValue(""))
        }

        val searchText=textState.value.text
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(items=booksList.filter{
                it.title.contains(searchText,ignoreCase = true)
            },key={ it.id }){ item->
                BookItem(
                    itemName=item.title,
                    itemImage=item.image,
                    itemAuthor=item.authors,
                    itemUrl=item.url,
                    navController=navController
                )


            }
        }
    }

}

@OptIn(UnstableApi::class)
@Composable
fun BookItem(
    itemName: String,
    itemImage: String,
    navController: NavController,
    itemAuthor: String,
    itemUrl: String
) {
    Card(
        modifier=Modifier.wrapContentSize().
        padding(10.dp),
        onClick = {

            navController.navigate("DetailScreen?name=${itemName}&image=${itemImage}&author=${itemAuthor}&url=${itemUrl}")
            Log.d("onClick","find")
        },
        colors=CardDefaults.cardColors(containerColor= Purple)
    ) {
        Column(modifier=Modifier.wrapContentSize().padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            AsyncImage(model=itemImage, contentDescription = itemName,
                modifier=Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp)))
            Spacer(modifier=Modifier.height(20.dp))
            Text(text=itemName,fontSize=20.sp, textAlign = TextAlign.Center,
                modifier=Modifier.fillMaxSize().basicMarquee())
        }

    }
    Spacer(modifier=Modifier.height(12.dp))

}


//@Composable
/*fun SearchView(state: MutableState<TextFieldValue>, placeholder: String, modifier: Modifier) {
    TextField(value=state.value, onValueChange = {value->
        state.value=value
    }, modifier = Modifier.fillMaxWidth().padding(10.dp).clip(RoundedCornerShape(30.dp)),
        placeholder={Text(text=placeholder)}, maxLines = 1, singleLine = true)

}*/

