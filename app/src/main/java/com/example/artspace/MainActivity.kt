package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val artworks = listOf(
        ArtworkData(
            R.drawable.images1,
            "Mona Lisa",
            "Leonardo da Vinci",
            "1503-1506",
            "A portrait of Lisa Gherardini, known as Mona Lisa, famous for her enigmatic smile."
        ),
        ArtworkData(
            R.drawable.vincent___st_,
            "St. Paul's from the Surrey Side",
            "George Vincent",
            "1820",
            "A landscape view from Surrey side of Blackfriars Bridge with St. Paul's Cathedral in the background."
        ),
        ArtworkData(
            R.drawable.john_scott,
            "The South Shields collier brig Mary",
            "John Scott",
            "1855",
            "A collier brig showing two views of the same vessel, painted in 1855."
        ),
        ArtworkData(
            R.drawable.vincent,
            "Starry Night",
            "Vincent van Gogh",
            "1889",
            "A famous post-impressionist painting representing a dreamy interpretation of the artist’s asylum room’s sweeping view."
        )
    )


    var currentArtworkIndex by remember { mutableStateOf(0) }
    val currentArtwork = artworks[currentArtworkIndex]

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkDisplay(currentArtwork.imageResId)
        Spacer(modifier = modifier.size(16.dp))
        ArtworkTitle(currentArtwork.title, currentArtwork.artist, currentArtwork.year, currentArtwork.description)
        Spacer(modifier = modifier.size(25.dp))
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFeba443)),
                onClick = {
                    currentArtworkIndex = if (currentArtworkIndex > 0) currentArtworkIndex - 1 else artworks.size - 1
                }
            ) {
                Text(text = "Previous", fontSize = 16.sp)
            }

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFeba443)),
                onClick = {
                    currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
                }
            ) {
                Text(text = "Next", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ArtworkDisplay(@DrawableRes imageResId: Int) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .size(300.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ArtworkTitle(title: String, artist: String, year: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFeba443))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Text(text = "By $artist", fontSize = 18.sp)
        Text(text = "Year: $year", fontSize = 16.sp)
        Text(text = description, fontSize = 14.sp)
    }
}

data class ArtworkData(
    @DrawableRes val imageResId: Int,
    val title: String,
    val artist: String,
    val year: String,
    val description: String
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}
