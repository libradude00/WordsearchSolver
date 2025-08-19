package com.example.wordsearchsolver

import android.content.Context
import android.graphics.Bitmap
import com.googlecode.tesseract.android.TessBaseAPI

class OcrProcessor(private val context: Context) {

    private val tessBaseAPI: TessBaseAPI = TessBaseAPI()

    init {
        val datapath = context.filesDir.absolutePath + "/tesseract/"
        val lang = "eng"

        val dir = java.io.File(datapath + "tessdata/")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val trainedDataFile = java.io.File(dir, "$lang.traineddata")
        if (!trainedDataFile.exists()) {
            context.assets.open("tessdata/$lang.traineddata").use { input ->
                trainedDataFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }

        tessBaseAPI.init(datapath, lang)
    }

    fun processImage(bitmap: Bitmap): String {
        tessBaseAPI.setImage(bitmap)
        return tessBaseAPI.utF8Text
    }

    fun release() {
        tessBaseAPI.end()
    }
}
