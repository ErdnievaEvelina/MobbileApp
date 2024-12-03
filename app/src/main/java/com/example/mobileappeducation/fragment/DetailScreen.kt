package com.example.mobileappeducation.fragment

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import coil.compose.AsyncImage
import com.example.mobileappeducation.ui.theme.Purple


@OptIn(UnstableApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    name: String?,
    image: String?,
    author: String?,
    url: String?

) {
    Column(
        modifier
            .background(Purple)
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 45.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (image != null){
            AsyncImage(
                model = image, contentDescription = name,
                modifier
                    .height(350.dp)
                    .width(360.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

        }

        Spacer(modifier.height(20.dp))
        if (name != null){
            Text(text=name,color= Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        }
        Spacer(modifier.height(15.dp))
        if(author!=null){
            Text(text="Author:",color=Color.White,fontSize=20.sp)
            Text(text=author,color=Color.White, fontSize = 20.sp, textAlign = TextAlign.Center)
        }
        Spacer(modifier.height(15.dp))
        HyperText(url=url)

    }
}
@Composable
fun HyperText(url: String?){
    val urlHandler= LocalUriHandler.current
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text="Ссылка для чтения книги онлайн",color=Color.White, fontSize = 20.sp)
        Text(text="Click here",color=Color.Blue, textDecoration = TextDecoration.Underline,
            modifier=Modifier.clickable {
                if (url != null) {
                    urlHandler.openUri(url)
                }
            })

    }

}