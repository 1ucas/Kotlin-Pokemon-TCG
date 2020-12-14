package com.manobray.testutils

import java.io.File

fun loadJsonFromResources(fileName: String): String {
    val url = ClassLoader.getSystemResource(fileName)
    val file = File(url.path)
    return file.readText()
}