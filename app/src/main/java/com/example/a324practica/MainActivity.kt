package com.example.a324practica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a324practica.ui.theme._324PracticaTheme
import com.example.a324practica.model.Topic
import com.example.a324practica.data.DataSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _324PracticaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicApp(
                        modifier = Modifier.padding(dimensionResource(R.dimen.paddingSmall ))
                    )
                }
            }
        }
    }
}

@Composable
fun TopicApp(modifier: Modifier = Modifier) {
    TopicList(
        topicList = DataSource.topics
    )
}

@Composable
fun TopicList (topicList: List<Topic>, modifier: Modifier = Modifier){

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(dimensionResource(R.dimen.paddingSmall)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.paddingSmall )),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.paddingSmall )),
        modifier = modifier
    ) {
        items(topicList) { topic ->
            TopicCard(topic)
        }
    }
}
@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier){
    Card(modifier = modifier){

        Row{
            Image(
                painter = painterResource( topic.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(
                        width = dimensionResource(R.dimen.imageWidth),
                        height = dimensionResource(R.dimen.imageHeight)
                    )
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )

    Column{
            Text(
                text = LocalContext.current.getString(topic.stringResource),
                modifier = Modifier.padding(start =dimensionResource(R.dimen.paddingMedium),
                    top = dimensionResource(R.dimen.paddingMedium),
                    end = dimensionResource(R.dimen.paddingMedium),
                    bottom = dimensionResource(R.dimen.paddingSmall )
                ),
                style = MaterialTheme.typography.bodyMedium

            )
                Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.ic_grain),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = dimensionResource(R.dimen.paddingMedium ))
                )

                Text(
                    text = topic.stringResourceId.toString(),
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.paddingSmall )),
                    style = MaterialTheme.typography.labelMedium
                )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _324PracticaTheme {
        TopicApp()
    }
}
