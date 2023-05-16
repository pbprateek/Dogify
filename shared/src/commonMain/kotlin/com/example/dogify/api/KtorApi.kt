package com.example.dogify.api

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal abstract class KtorApi {

    private val jsonConfiguration = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }


    //HttpClient = which under the hood will use the different Android or iOS HttpClientEngine from the platform-specific dependencies that we've added.
    val client = HttpClient {
        install(ContentNegotiation) {
            json(jsonConfiguration)
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom("https://dog.ceo")
            path("api", path)
        }
    }
}