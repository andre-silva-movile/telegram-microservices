package message.processor.joke.components

import message.core.log.Log
import message.core.mapper.Mapper
import org.apache.http.HttpHeaders
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.util.EntityUtils
import java.io.IOException

class JokeService
constructor(private val httpClient: HttpClient,
            private val mapper: Mapper) {

    fun get(): Joke? {
        try {
            Log.application.info("Request to get a brand new joke")
            val get = HttpGet("https://us-central1-kivson.cloudfunctions.net/charada-aleatoria")
            get.addHeader(HttpHeaders.ACCEPT, "application/json")
            val response = httpClient.execute(get)
            return if (Integer.valueOf(200) == response.statusLine.statusCode) {
                Log.application.info("Joke received with success")
                mapper.deserialize(EntityUtils.toString(response.entity, "UTF-8"), Joke::class.java)
            } else null
        } catch (e: IOException) {
            return null
        }

    }

}