package com.example.amerikanexpress.ui.screen.Otp.data

data class VerificationResponse(
    val message: String,
    val status: String
)

data class ValidationError(
    val detail: List<ErrorDetail>
)

data class ErrorDetail(
    val loc: List<String>,
    val msg: String,
    val type: String
)