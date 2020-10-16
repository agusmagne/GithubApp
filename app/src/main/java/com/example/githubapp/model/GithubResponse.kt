package com.example.githubapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class GithubResponse<T>(
    @field:Expose
    @field:SerializedName("total_count")
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: MutableList<T>
)