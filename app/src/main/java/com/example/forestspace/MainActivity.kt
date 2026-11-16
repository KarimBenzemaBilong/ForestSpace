
package com.example.forestspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// =====================================
// 1. DATA MODEL
// =====================================

data class Artwork(
    val image: Int,
    val title: String,
    val artist: String,
    val year: String,
    val description: String
)

// =====================================
// 2. DATA 5 KARYA SENI
// =====================================

val artworkList = listOf(

    Artwork(
        image = R.drawable.hutan_hujan_tropis,
        title = "The Wonders of Tropical Rainforest",
        artist = "ForestSpace Nature Collection",
        year = "2026",
        description = "Hutan hujan tropis merupakan hutan yang memiliki curah hujan tinggi sepanjang tahun dan keanekaragaman hayati yang luar biasa."
    ),

    Artwork(
        image = R.drawable.hutan_gugur,
        title = "The Beauty of Deciduous Forest",
        artist = "ForestSpace Nature Collection",
        year = "2026",
        description = "Hutan gugur adalah hutan yang sebagian besar pepohonannya menggugurkan daun pada musim tertentu, terutama saat musim gugur."
    ),

    Artwork(
        image = R.drawable.hutan_taiga,
        title = "The Silent Beauty of Taiga Forest",
        artist = "ForestSpace Nature Collection",
        year = "2026",
        description = "Hutan taiga merupakan hutan yang didominasi oleh pohon berdaun jarum seperti pinus, cemara, dan spruce."
    ),

    Artwork(
        image = R.drawable.hutan_bakau,
        title = "The Guardians of Coastal Forest",
        artist = "ForestSpace Nature Collection",
        year = "2026",
        description = "Hutan bakau adalah ekosistem hutan yang tumbuh di kawasan pesisir dan dipengaruhi oleh pasang surut air laut."
    ),

    Artwork(
        image = R.drawable.hutan_lumut,
        title = "The Enchanted World of Moss Forest",
        artist = "ForestSpace Nature Collection",
        year = "2025",
        description = "Hutan lumut merupakan kawasan hutan yang memiliki kelembapan tinggi sehingga permukaan tanah, bebatuan, dan batang pohon banyak ditumbuhi lumut."
    )
)

// =====================================
// 3. COLOR
// =====================================

val ForestGreen = Color(0xFF355E3B)
val Cream = Color(0xFFF5F2E9)
val LightGreen = Color(0xFFE1E8DC)

// =====================================
// 4. MAIN ACTIVITY
// =====================================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ForestSpaceApp()
        }
    }
}

// =====================================
// 5. MAIN APP
// =====================================

@Composable
fun ForestSpaceApp() {

    var currentIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val totalArtwork = artworkList.size

    // NEXT FUNCTION
    fun nextArtwork() {
        currentIndex = (currentIndex + 1) % totalArtwork
    }

    // PREVIOUS FUNCTION
    fun previousArtwork() {
        currentIndex =
            (currentIndex - 1 + totalArtwork) % totalArtwork
    }

    // CURRENT ARTWORK
    val currentArtwork = artworkList[currentIndex]

    ForestSpaceScreen(
        artwork = currentArtwork,
        currentPage = currentIndex + 1,
        totalPage = totalArtwork,
        onNext = { nextArtwork() },
        onPrevious = { previousArtwork() }
    )
}

// =====================================
// 6. FOREST SPACE SCREEN
// =====================================

@Composable
fun ForestSpaceScreen(
    artwork: Artwork,
    currentPage: Int,
    totalPage: Int,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {

    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Cream
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // HEADER
                Text(
                    text = "🌿 ForestSpace",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestGreen
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Discover the beauty of nature",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ARTWORK IMAGE
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp),

                    shape = RoundedCornerShape(12.dp),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Image(
                        painter = painterResource(
                            id = artwork.image
                        ),

                        contentDescription = artwork.title,

                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),

                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // ARTWORK INFORMATION
                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(12.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = LightGreen
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        // TITLE
                        Text(
                            text = artwork.title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = ForestGreen
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // ARTIST AND YEAR
                        Text(
                            text = "${artwork.artist} (${artwork.year})",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // DESCRIPTION
                        Text(
                            text = artwork.description,
                            fontSize = 15.sp,
                            lineHeight = 23.sp,
                            color = Color.DarkGray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // PAGE INDICATOR
                Text(
                    text = "$currentPage / $totalPage",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestGreen
                )

                Spacer(modifier = Modifier.height(16.dp))

                // NAVIGATION BUTTONS
                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween,

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // PREVIOUS
                    Button(
                        onClick = onPrevious,

                        colors = ButtonDefaults.buttonColors(
                            containerColor = ForestGreen
                        ),

                        shape = RoundedCornerShape(10.dp)
                    ) {

                        Text(
                            text = "Previous",
                            fontSize = 14.sp
                        )
                    }

                    // NEXT
                    Button(
                        onClick = onNext,

                        colors = ButtonDefaults.buttonColors(
                            containerColor = ForestGreen
                        ),

                        shape = RoundedCornerShape(10.dp)
                    ) {

                        Text(
                            text = "Next",
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // FOOTER
                Text(
                    text = "© 2026 ForestSpace",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

// =====================================
// 7. COMPOSABLE PREVIEW
// =====================================

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "ForestSpace Preview"
)
@Composable
fun ForestSpacePreview() {
    ForestSpaceApp()
}