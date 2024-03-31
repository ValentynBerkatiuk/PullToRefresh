package com.simpleappsdev.pulltorefresh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simpleappsdev.pulltorefresh.ui.theme.PullToRefreshTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PullToRefreshTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val items = (1..100).map { "item $it" }
                    
                    var isRefreshing by rememberSaveable {
                        mutableStateOf(false)
                    }
                    val scope = rememberCoroutineScope()

                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ){
                        pullToRefreshWithListOfItems(
                            items = items,
                            content = { itemTitle ->
                                 Text(
                                     text = itemTitle,
                                     modifier = Modifier.padding(16.dp)
                                 )
                            },
                            isRefreshing = isRefreshing,
                            onRefresh = {
                                scope.launch {
                                    isRefreshing = true
                                    delay(3000L)
                                    isRefreshing = false
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PullToRefreshTheme {
        Greeting("Android")
    }
}