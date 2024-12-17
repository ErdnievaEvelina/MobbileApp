package com.example.mobileappeducation.fragment

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mobileappeducation.presentation.BookEvents
import com.example.mobileappeducation.presentation.BookState
import com.example.mobileappeducation.ui.theme.Blue
import com.example.mobileappeducation.ui.theme.Purple40
import com.example.mobileappeducation.ui.theme.PurpleGrey80

@OptIn(UnstableApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: BookState,
    navController: NavController,
    onEvent:(BookEvents)->Unit,
    name: String?,
    image: String?,
    author: String?,
    url: String?

) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (name != null) {
                    state.title.value = name
                }
                if (image != null) {
                    state.image.value = image
                }
                if (author != null) {
                    state.author.value = author
                }
                onEvent(
                    BookEvents.SaveBook(
                        title = state.title.value,
                        author = state.author.value,
                        image = state.image.value
                    )
                )
                navController.navigate("Inform")

            }) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Save book"
                )
            }
        }
    )
    { paddingValues ->


        Column(
            modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier.fillMaxWidth().size(350.dp).background(Blue),
                contentAlignment = Alignment.Center
            ) {
                if (image != null) {
                    AsyncImage(
                        model = image, contentDescription = name,
                        modifier
                            .height(300.dp)
                            .width(300.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                }
            }

            Spacer(modifier.height(20.dp))
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors().copy(containerColor = PurpleGrey80)
            ) {
                Box(modifier.height(80.dp).width(250.dp), contentAlignment = Alignment.Center) {
                    if (name != null) {
                        Text(
                            text = name,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Spacer(modifier.height(15.dp))
            Text(text = "Author:", fontSize = 20.sp)
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors().copy(containerColor = PurpleGrey80)
            ) {

                Box(
                    modifier.height(80.dp).width(280.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (author != null) {
                        Text(
                            text = author,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier.height(10.dp))
            HyperText(url = url)


        }
    }
}
@Composable
fun HyperText(url: String?){
    val urlHandler= LocalUriHandler.current
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text="Ссылка для чтения книги онлайн", fontSize = 20.sp)
        Text(text="Click here",color=Color.Blue, textDecoration = TextDecoration.Underline,
            modifier=Modifier.clickable {
                if (url != null) {
                    urlHandler.openUri(url)
                }
            })

    }

}