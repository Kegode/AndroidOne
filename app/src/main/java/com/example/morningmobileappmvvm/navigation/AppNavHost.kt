package com.example.morningmobileappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.morningmobileappmvvm.ui.theme.screens.clients.AddClient
import com.example.morningmobileappmvvm.ui.theme.screens.home.HomeScreens
import com.example.morningmobileappmvvm.ui.theme.screens.login.Login
import com.example.morningmobileappmvvm.ui.theme.screens.register.Greeting

@Composable
fun AppNavHost(
    navController: NavHostController =rememberNavController(),
    startDestination: String = ROUTE_REGISTER){

    NavHost(navController=navController,
        startDestination=startDestination){
        composable(ROUTE_REGISTER){ Greeting("John",navController)}
        composable(ROUTE_HOME){ HomeScreens(navController)}
        composable(ROUTE_LOGIN){ Login(navController)}
        composable(ROUTE_ADD_CLIENT){ AddClient(navController)}
    }

}