package com.example.mobileappeducation.screens

sealed class Screens(val screen:String) {
  data object Home:Screens("home")
  data object Inform:Screens("inform")
  sealed class ProfileScreen:Screens(""){
    data object Profile:Screens("profile")
    data object BookDetails:Screens("DetailScreen")
  }


}