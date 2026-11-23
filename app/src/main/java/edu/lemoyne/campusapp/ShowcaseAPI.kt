package edu.lemoyne.campusapp

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ShowcaseAPI {
    @GET("/rest/v1/showcase_items")
    suspend fun getShowcaseItem(
        @Header("apikey") apiKey: String,
        @Header("Authorization") authorization: String
    ): List<ShowcaseItem>

    @POST("auth/v1/token?grant_type=password")
    suspend fun signIn(
        @Header("apikey") apiKey: String,
        @Body request: LoginRequest
    ): LoginResponse
}

object ShowcaseService {
    const val API_KEY = "sb_publishable_vWex0IKbIHnMq7NLIdJYdw__ZeYPb6k"
    const val BASE_URL = "https://ujdjrkenjtkcacixdyse.supabase.co"

    val api: ShowcaseAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ShowcaseAPI::class.java)
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    @SerializedName("access_token") val accessToken: String
)