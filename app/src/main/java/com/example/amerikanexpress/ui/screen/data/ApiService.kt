package com.example.amerikanexpress.ui.screen.data

import com.example.amerikanexpress.ui.screen.Otp.data.VerificationResponse
import com.example.amerikanexpress.ui.screen.sighnUp.data.SignUpRequest
import com.example.amerikanexpress.ui.screen.sighnUp.data.SignUpResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/register")
    suspend fun signUpUser(@Body request: SignUpRequest): Response<SignUpResponse>

    @GET("verify/{code}")
    fun verifyEmail(
        @Path("code") code: String,
        @Header("accept") accept: String = "application/json",
    ): Call<VerificationResponse>
}