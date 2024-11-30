package com.example.mobileappeducation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobileappeducation.fragment.DetailScreen
import com.example.mobileappeducation.fragment.Home
import com.example.mobileappeducation.fragment.Inform
import com.example.mobileappeducation.fragment.Profile
import com.example.mobileappeducation.model.Book

import com.example.mobileappeducation.screens.Screens
import com.example.mobileappeducation.ui.theme.BlueJC
import com.example.mobileappeducation.ui.theme.MobileAppEducationTheme
import com.example.mobileappeducation.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAppEducationTheme {
                Surface(
                    modifier=Modifier.fillMaxSize(),
                    color=MaterialTheme.colorScheme.background
                ) {
                   MyBottomApp()
                    /*val navController= rememberNavController()
                    var booksList by remember {
                        mutableStateOf(listOf<Book>())
                    }
                    val scope= rememberCoroutineScope()
                    LaunchedEffect(true) {
                        scope.launch(Dispatchers.IO) {
                            val response=try{
                                RetrofitInstance.api.getBooksList()
                            }catch(e:IOException){
                                Toast.makeText(this@MainActivity,"app error${e.message}",Toast.LENGTH_SHORT).show()
                                return@launch
                            }catch(e:HttpException){
                                Toast.makeText(this@MainActivity,"http error${e.message}",Toast.LENGTH_SHORT).show()
                                return@launch
                            }
                            if (response.isSuccessful && response.body()!=null ){
                                withContext(Dispatchers.Main){
                                    booksList=response.body()!!.books
                                }
                            }
                        }
                    }
                    NavHost(navController=navController, startDestination = "Profile") {
                        composable(route="Profile") {
                            Profile(booksList=booksList,navController=navController)
                        }
                        composable(route="DetailScreen"){
                            DetailScreen()
                        }
                    }*/
                }
            }
        }
    }
}
@Composable
fun MyBottomApp(){
    val navController= rememberNavController()
    val context= LocalContext.current
    var booksList by remember {
        mutableStateOf(listOf<Book>())
    }
    val scope= rememberCoroutineScope()
    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            val response=try{
                RetrofitInstance.api.getBooksList()
            }catch(e:IOException){
                Toast.makeText(context,"app error${e.message}",Toast.LENGTH_SHORT).show()
                return@launch
            }catch(e:HttpException){
                Toast.makeText(context,"http error${e.message}",Toast.LENGTH_SHORT).show()
                return@launch
            }
            if (response.isSuccessful && response.body()!=null ){
                withContext(Dispatchers.Main){
                    booksList=response.body()!!.books
                }
            }
        }
    }
    NavHost(navController=navController, startDestination = "Profile") {
        composable(Screens.Profile.screen) {
            Profile(booksList=booksList,navController=navController)
        }
        composable("DetailScreen"){
            DetailScreen(navController=navController)
        }
    }
    val navigationController= rememberNavController()
    val selected= remember {
        mutableStateOf(Icons.Default.Home)
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = BlueJC) {
                IconButton(onClick = {selected.value=Icons.Default.Home
                    navigationController.navigate(Screens.Home.screen){popUpTo(0)}},
                    modifier=Modifier.weight(1f)) {
                    Icon(Icons.Default.Home,contentDescription = null,modifier=Modifier.size(26.dp),
                        tint=if(selected.value==Icons.Default.Home) Color.White else Color.DarkGray)

                }
                IconButton(onClick = {selected.value=Icons.Default.Search
                    navigationController.navigate(Screens.Inform.screen){popUpTo(0)}},
                    modifier=Modifier.weight(1f)) {
                    Icon(Icons.Default.Search,contentDescription = null,modifier=Modifier.size(26.dp),
                        tint=if(selected.value==Icons.Default.Search) Color.White else Color.DarkGray)

                }
                IconButton(onClick = {selected.value=Icons.Default.Person
                    navigationController.navigate(Screens.Profile.screen){popUpTo(0)}},
                    modifier=Modifier.weight(1f)) {
                    Icon(Icons.Default.Person,contentDescription = null,modifier=Modifier.size(26.dp),
                        tint=if(selected.value==Icons.Default.Person) Color.White else Color.DarkGray)

                }
            }
        }
    ){ paddingValues ->
        NavHost(navController=navigationController,
            startDestination = Screens.Home.screen,
            modifier=Modifier.padding(paddingValues)) {
            composable(Screens.Home.screen){Home()}
            composable(Screens.Inform.screen){ Inform() }
            composable(Screens.Profile.screen){ Profile(booksList =booksList , navController = navController) }
        }

    }


}



