package com.example.baynews.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    // dummy data (nanti kamu ganti)
    val name = "Your Name"
    val email = "youremail@example.com"
    val repository = "https://github.com/yourname/baynews"
    val github = "https://github.com/yourname"
    val linkedin = "https://linkedin.com/in/yourname"
    val description =
        "BayNews adalah aplikasi berita berbasis NewsAPI yang menampilkan headline dan daftar berita dengan infinite scrolling."

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Icon(Icons.Filled.Person, contentDescription = null)
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text(text = name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(2.dp))
                        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                            Icon(Icons.Filled.Email, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text(text = email, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }

                Spacer(Modifier.height(14.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(Modifier.height(14.dp))

        Text(
            text = "Links",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(10.dp))

        LinkCard(label = "Repository", value = repository)
        LinkCard(label = "GitHub", value = github)
        LinkCard(label = "LinkedIn", value = linkedin)
    }
}

@Composable
private fun LinkCard(label: String, value: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Search, contentDescription = null)
            Spacer(Modifier.width(10.dp))
            Column {
                Text(text = label, style = MaterialTheme.typography.labelMedium)
                Spacer(Modifier.height(2.dp))
                Text(text = value, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
