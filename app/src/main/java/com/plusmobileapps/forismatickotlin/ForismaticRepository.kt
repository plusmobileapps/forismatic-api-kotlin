package com.plusmobileapps.forismatickotlin

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Inject
import javax.inject.Singleton

interface ForismaticRepository {
    suspend fun getQuote(): GetQuoteModel
}

@Singleton
class ForismaticRepositoryImpl @Inject constructor(engine: HttpClientEngine): ForismaticRepository {

    private val client = HttpClient(engine) {
        install(ContentNegotiation) {
            json()
        }
    }

    override suspend fun getQuote(): GetQuoteModel = client.get(API_URL).body()
}

private const val API_URL = "https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en"