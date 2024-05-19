package com.example.ntp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ntp.R
import com.example.ntp.ui.JokeDialog
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import org.json.JSONObject

@Composable
fun JokesScreen(navigateBack: () -> Unit) {
    var btnClicked by remember { mutableStateOf(false) }
    val client = HttpClient(CIO)
    var responseText by remember { mutableStateOf("") }
    var openDialog by rememberSaveable { mutableStateOf(false) }
    val closeDialog = {
        openDialog = false
        responseText = ""
    }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { btnClicked = true }) {
                Text(text = stringResource(R.string.tell_me_a_joke))
            }
            LaunchedEffect(btnClicked) {
                if (btnClicked) {
                    val response: HttpResponse =
                        client.get("https://v2.jokeapi.dev/joke/Any?type=single")
                    responseText = response.bodyAsText()
                    btnClicked = false
                }
            }
        }
        if (responseText.isNotEmpty()) {
            val jsonData = JSONObject(responseText)
            if (jsonData.has("joke")) {
                JokeDialog(closeDialog = closeDialog, joke = jsonData.getString("joke"))
            }
        }
    }
}