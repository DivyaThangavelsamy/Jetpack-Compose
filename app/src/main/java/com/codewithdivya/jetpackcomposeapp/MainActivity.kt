package com.codewithdivya.jetpackcomposeapp

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codewithdivya.jetpackcomposeapp.ui.theme.JetPackComposeAppTheme
import com.codewithdivya.jetpackcomposeapp.ui.theme.SampleData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }
}

data class Message(val author : String, val body : String )

@Composable
fun Greeting(message : Message) {
    Row (modifier = Modifier.padding(all = 8.dp)){
        Image(
            painter = painterResource(R.drawable.rose_image),
            contentDescription = "Contact profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)

                    )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = message.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)){
                Text(
                    text = message.body,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(all = 4.dp)
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            Greeting(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    JetPackComposeAppTheme {
        Conversation(SampleData.conversationSample)
    }
}


@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    JetPackComposeAppTheme {
        Greeting(
            message = Message("Android","Jetpack Compose")
        )
    }
}