package com.emproto.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emproto.composetutorial.ui.theme.ComposeTutorialTheme

class ComposeBasicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun MyApp(modifier: Modifier = Modifier) {
    val shouldShowOnBoarding = remember {
        mutableStateOf(true)
    }
    Surface(modifier) {
        if(shouldShowOnBoarding.value){
            OnBoardingScreen(onContinueClicked = { shouldShowOnBoarding.value = false})
        }else{
            Greetings()
        }
    }
}

@Composable
fun OnBoardingScreen( onContinueClicked : () -> Unit,
                      modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Compose!")
        Button(modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked ) {
            Text(text = "Continue")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = listOf( "Tom","Jerry")
){
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for(name in names){
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = remember {
        mutableStateOf(false)
    }
    val extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello,")
                Text(text = name)
            }
            ElevatedButton(onClick = {
                expanded.value = !expanded.value
            }) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ComposeTutorialTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun GreetingsPreview(){
    ComposeTutorialTheme {
        Greetings()
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun onBoardingPreview(){
    ComposeTutorialTheme {
        OnBoardingScreen(onContinueClicked = { })
    }
}

