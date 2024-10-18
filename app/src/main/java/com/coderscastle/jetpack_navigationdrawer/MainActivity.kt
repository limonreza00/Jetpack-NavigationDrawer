package com.coderscastle.jetpack_navigationdrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderscastle.jetpack_navigationdrawer.ui.theme.JetpackNavigationDrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackNavigationDrawerTheme {
                Screen()
            }
        }
    }
}


@Composable
fun Screen (modifier: Modifier= Modifier){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent()
            }
        }
    ) {

        Scaffold (
            topBar = {
                TopBar(
                    onOpenDrawer = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ) {innerPadding->
            ScreenContent(modifier = Modifier.padding(innerPadding))
        }

    }


}

@Composable
fun ScreenContent(modifier: Modifier) {

}

@Composable
fun DrawerContent (){

    Text(
        text = "Drawer Content",
        modifier = Modifier.padding(start = 10.dp),
        fontSize = 20.sp
    )

    HorizontalDivider()

    NavigationDrawerItem(
        icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Item 1") },
        label = { Text(text = "Profile") },
        selected = false,
        onClick = {}
    )
    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Item 1") },
        label = { Text(text = "Profile") },
        selected = false,
        onClick = {}
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar (
    onOpenDrawer: () -> Unit = {}
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,

        ),
        title = {Text(text = "Drawer Title")},
        navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Navigation Menu Icon",
                    modifier = Modifier.padding(start = 8.dp)
                        .clickable { onOpenDrawer() }
                )
        }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetpackNavigationDrawerTheme {
        Screen()
    }
}