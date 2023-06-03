package com.example.dogify.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import platform.Foundation.*
import platform.UIKit.UIImage
import platform.UIKit.UIImageView

@Composable
actual fun AsyncImage(
    modifier: Modifier,
    imageUrl: String,
    contentDescription: String?,
    contentScale: ContentScale,
) {
    UIKitView(modifier = modifier, factory = {
        val imageView = UIImageView()
        imageView
    }, update = {
        val url = NSURL(string = imageUrl)
        NSURLSession().dataTaskWithURL(url = url) { data, response, error ->
            if (data != null) {
                val uiImage = UIImage(data = data)
                //Run It in main Thread
                it.image = uiImage

            }
        }
    })
}


//        val data = NSData.dataWithContentsOfURL(url = url)
//        if(data !=null){
//            val uiImage = UIImage(data = data)
//            it.image = uiImage
//        }


