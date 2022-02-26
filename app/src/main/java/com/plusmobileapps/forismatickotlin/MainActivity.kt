package com.plusmobileapps.forismatickotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plusmobileapps.forismatickotlin.ui.theme.ForismaticKotlinTheme
import com.plusmobileapps.forismatickotlin.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForismaticKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GetQuoteUI(viewModel.state) { viewModel.getQuoteClicked() }
                }
            }
        }
    }
}

@Composable
fun GetQuoteUI(stateFlow: StateFlow<MainViewState>, onGetQuoteClick: () -> Unit) {
    val state = stateFlow.collectAsState()
    Scaffold(topBar = { TopAppBar(title = { Text("Forismatic Kotlin") }) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (val currentState = state.value) {
                MainViewState.Empty -> Text("No quotes yet, please click the button below to get one")
                is MainViewState.Loaded -> QuoteUI(model = currentState.quote)
                MainViewState.Loading -> CircularProgressIndicator()
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(onClick = onGetQuoteClick, enabled = state.value != MainViewState.Loading) {
                Text("Get Quote")
            }
        }
    }
}

@Composable
fun QuoteUI(model: GetQuoteModel) {
    Card {
        Column {
            Text(model.quoteText, style = Typography.h4)
            Text(text = model.quoteText)
            Text(text = model.quoteLink)
            Text(model.senderLink)
            Text(model.senderName)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ForismaticKotlinTheme {
        GetQuoteUI(
            stateFlow = MutableStateFlow(MainViewState.Loaded(GetQuoteModel(
                quoteText = "Some amazing quote",
                quoteAuthor = "Some author",
                senderName = "",
                senderLink = "",
                quoteLink = ""
            ))),
            onGetQuoteClick = {})
    }
}