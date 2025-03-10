package com.example.amerikanexpress.ui.screen.sighnUp.data

data class SignUpRequest(
    val email: String,
    val password: String,
    val confirmPassword: String
)

data class SignUpResponse(
    val email: String,
    val id: Int,
    val is_active: Boolean,
    val is_worled: Boolean,
    val created_at: String,
    val updated_at: String
)

data class SignUpError(
    val detail: List<ErrorDetail>
)

data class ErrorDetail(
    val user: List<String>,
    val msg: String,
    val type: String
)