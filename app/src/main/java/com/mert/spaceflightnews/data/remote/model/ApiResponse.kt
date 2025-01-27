package com.mert.spaceflightnews.data.remote.model

data class ApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ApiArticle>
)