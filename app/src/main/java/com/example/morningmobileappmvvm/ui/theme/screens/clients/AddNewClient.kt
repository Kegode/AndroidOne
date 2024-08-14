package com.example.morningmobileappmvvm.ui.theme.screens.clients


import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.morningmobileappmvvm.R
import com.example.morningmobileappmvvm.data.ClientViewModel



@Composable
fun AddClient(navController: NavController){
    val imageUri = rememberSaveable() {
        mutableStateOf<Uri?>(null)
    }
    val painter = rememberImagePainter(
        data = imageUri.value ?: R.drawable.ic_person,
        builder = {
            crossfade(true)
        }
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it }
    }

    var firstname by remember {
        mutableStateOf(value = "")
    }
    var lastname by remember {
        mutableStateOf(value = "")
    }
    var gender by remember {
        mutableStateOf(value = "")
    }
    var age by remember {
        mutableStateOf(value = "")
    }
    var bio by remember {
        mutableStateOf(value = "")
    }
    Scaffold (
        bottomBar = {
            BottomAppBar (
                actions = {
                          IconButton(onClick = { /*TODO*/ }) {
                              Icon(Icons.Filled.Home,
                                  contentDescription ="Home Icon" )
                          }
                          IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Filled.Settings,
                                    contentDescription = "Settings Icon")
                            }
                          IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Filled.Email,
                                    contentDescription = "Email Icon")
                            }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()) {
                        Icon(Icons.Filled.AccountCircle,
                            contentDescription ="Profile Icon" )
                    }
                }
            )




        }
    ){innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
                .fillMaxWidth()
        ){
            Text(text = "INSERT NEW CLIENT",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.Magenta))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "GO BACK")
                }
                val context = LocalContext.current
                Button(onClick = {
                    val clientRepository =
                        ClientViewModel(navController, context)
                    imageUri.value?.let { uri ->
                        clientRepository
                            .saveClient(uri, firstname,
                                lastname, gender, age, bio)
                    } ?: run {
                        Toast.makeText(context,
                            "Please select an image",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                ) {
                    Text(text = "SAVE")
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card (
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(180.dp)
                ){
                    Image(painter = painter ,
                        contentDescription = null,
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { launcher.launch("image/*") },
                        contentScale = ContentScale.Crop)
                }
                Text(text = "Change Picture Here")
            }
            OutlinedTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter First Name")},
                placeholder = { Text(text = "Please Enter First Name")},
                value = firstname,
                onValueChange = {
                        newName -> firstname = newName
                })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter Last Name")},
                placeholder = { Text(text = "Please Enter Last Name")},
                value = lastname,
                onValueChange = {
                        newName -> lastname = newName
                })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter your Gender")},
                placeholder = { Text(text = "Please Enter your Gender")},
                value = gender,
                onValueChange = {
                        newName -> gender = newName
                })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter your Age")},
                placeholder = { Text(text = "Please Enter your Age")},
                value = age,
                onValueChange = {
                        newName -> age = newName
                })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .height(160.dp)
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter description")},
                placeholder = { Text(text = "Please Enter brief description")},
                value = bio,
                singleLine = false,
                onValueChange = {
                        newName -> bio = newName
                })

        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddClientPreview(){
    AddClient(rememberNavController())
}