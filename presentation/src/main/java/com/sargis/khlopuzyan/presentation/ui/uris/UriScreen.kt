package com.sargis.khlopuzyan.presentation.ui.uris

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.sargis.khlopuzyan.presentation.ui.shoppingList.common.CommonTopAppBar
import java.io.File
import java.io.FileOutputStream

// https://www.youtube.com/watch?v=4Ob0plBL084&ab_channel=PhilippLackner
@Composable
fun UriScreen() {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CommonTopAppBar("Uri")
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp)
        ) {

            /**
             * 1st TYPE: RESOURCE URI
             * */
            Button(onClick = {
                val uri =
                    "android.resource://${context.packageName}/drawable/ic_launcher_background".toUri()
                val drawableBytes = context.contentResolver.openInputStream(uri)?.use {
                    it.readBytes()
                }
                println("drawable size: ${drawableBytes?.size}")
            }) {
                Text(text = "1 TYPE: RESOURCE URI")
            }
            //********************************************************
            /**
             * 2nd TYPE: FILE URI
             * */
            Button(onClick = {
                val uri =
                    "android.resource://${context.packageName}/drawable/ic_launcher_background".toUri()
                val drawableBytes = context.contentResolver.openInputStream(uri)?.use {
                    it.readBytes()
                }
                val file = File(context.filesDir, "ic_launcher_background.jpg")
                FileOutputStream(file).use { fileOutputStream ->
                    fileOutputStream.write(drawableBytes)
                }
                println("file.toUri(): ${file.toUri()}")
            }) {
                Text(text = "2 TYPE: FILE URI")
            }
            //********************************************************
            /**
             * 3rd TYPE: CONTENT URI
             * */
            val pickImage = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { contentUri ->
                    println("Content uri: $contentUri")
                    contentUri?.let {
                        val imageBytes = context.contentResolver.openInputStream(contentUri)?.use {
                            it.readBytes()
                        }
                        println("imageBytes size: ${imageBytes?.size}")
                    }
                }
            )
            //********************************************************
            /**
             * 4th TYPE: DATA URI
             * */
            val dataUri = Uri.parse("data:text/plain,charset=UTF-8,Hello%20World")
            Button(onClick = {
                pickImage.launch("image/*")
            }) {
                Text(text = "3 TYPE: CONTENT URI")
            }
        }
    }
}

@Preview
@Composable
fun UriScreenPreview() {
    UriScreen()
}