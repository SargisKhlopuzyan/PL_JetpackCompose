package com.sargis.khlopuzyan.data.repository

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class MediaStoreUtil(
    private val context: Context,
) {
    suspend fun saveImage(bitmap: Bitmap) {
        withContext(Dispatchers.IO) {
            val resolver = context.contentResolver
            val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
                )
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

            val timeInMillis = System.currentTimeMillis()

            val imageContentValues = ContentValues().apply {
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES
                )
                put(
                    MediaStore.Images.Media.DISPLAY_NAME,
                    "${timeInMillis}_image" + ".jpg"
                )
                put(
                    MediaStore.Images.Media.MIME_TYPE,
                    "image/jpg"
                )
                put(
                    MediaStore.Images.Media.DATE_ADDED,
                    timeInMillis
                )
                put(
                    MediaStore.Images.Media.IS_PENDING,
                    1
                )
            }

            val imageMediaStoreUri = resolver.insert(
                imageCollection, imageContentValues
            )

            imageMediaStoreUri?.let { uri ->
                try {
                    resolver.openOutputStream(uri)?.let { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    }
                    imageContentValues.clear()
                    imageContentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                    resolver.update(uri, imageContentValues, null, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    resolver.delete(uri, null, null)
                }
            }
        }
    }

    suspend fun saveVideo(file: File) {
        withContext(Dispatchers.IO) {
            val resolver = context.contentResolver

            val videoCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Video.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
                )
            } else {
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            }

            val timeInMillis = System.currentTimeMillis()

            val videoContentValues = ContentValues().apply {
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_MOVIES
                )
                put(
                    MediaStore.Video.Media.DISPLAY_NAME,
//                    file.name
                    "${timeInMillis}_video"
                )
                put(
                    MediaStore.Video.Media.MIME_TYPE,
                    "video/mp4"
                )
                put(
                    MediaStore.Video.Media.DATE_ADDED,
                    timeInMillis
                )
                put(
                    MediaStore.Video.Media.IS_PENDING,
                    1
                )
            }

            val videoMediaStoreUri = resolver.insert(
                videoCollection, videoContentValues
            )

            videoMediaStoreUri?.let { uri ->
                try {
                    resolver.openOutputStream(uri)?.use { outputStream ->
                        // We need to create inputStream so we can copy input stream in output stream
                        resolver.openInputStream(
                            Uri.fromFile(file)
                        )?.use { inputStream ->
                            inputStream.copyTo(outputStream)
                        }
                    }

                    videoContentValues.clear()
                    videoContentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                    resolver.update(uri, videoContentValues, null, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    resolver.delete(uri, null, null)
                }
            }
        }
    }

    suspend fun saveAudio(file: File) {
        withContext(Dispatchers.IO) {
            val resolver = context.contentResolver

            val audioCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Audio.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
                )
            } else {
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }

            val timeInMillis = System.currentTimeMillis()

            val audioContentValues = ContentValues().apply {
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_MUSIC
                )
                put(
                    MediaStore.Audio.Media.DISPLAY_NAME,
//                    file.name
                    "${timeInMillis}_audio"
                )
                put(
                    MediaStore.Audio.Media.MIME_TYPE,
                    "audio/mp3"
                )
                put(
                    MediaStore.Audio.Media.DATE_ADDED,
                    timeInMillis
                )
                put(
                    MediaStore.Audio.Media.IS_PENDING,
                    1
                )
            }

            val audioMediaStoreUri = resolver.insert(
                audioCollection, audioContentValues
            )

            audioMediaStoreUri?.let { uri ->
                try {
                    resolver.openOutputStream(uri)?.use { outputStream ->
                        // We need to create inputStream so we can copy input stream in output stream
                        resolver.openInputStream(
                            Uri.fromFile(file)
                        )?.use { inputStream ->
                            inputStream.copyTo(outputStream)
                        }
                    }

                    audioContentValues.clear()
                    audioContentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                    resolver.update(uri, audioContentValues, null, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    resolver.delete(uri, null, null)
                }
            }
        }
    }

    suspend fun downloadImage(url: String) {
        withContext(Dispatchers.IO) {
            val loader = ImageLoader(context)

            val request = ImageRequest.Builder(context)
                .data(url)
                .allowHardware(false) // Disable hardware bitmaps.
                .build()

            val resultImage = (loader.execute(request) as SuccessResult).image

            val bitmap = resultImage.toBitmap()

            saveImage(bitmap)
        }
    }

    fun getRawAudioFile(resourceId: Int): File {
        val inputStream = context.resources.openRawResource(resourceId)

        val audioFile = File.createTempFile(
            "temp_audio", ".mp3", context.cacheDir
        )

        audioFile.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }

        return audioFile
    }
}