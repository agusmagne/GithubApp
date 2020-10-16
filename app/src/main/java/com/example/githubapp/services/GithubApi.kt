package com.example.githubapp.services

import com.example.githubapp.model.GithubReadme
import com.example.githubapp.model.GithubRepositoryDTO
import com.example.githubapp.model.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET(GithubApiConfig.SEARCH_REPO)
    fun getRepositories(@Query("q") repository: String?): Call<GithubResponse<GithubRepositoryDTO>>

    @GET("/repos/{owner}/{repo}/readme")
    fun getReadme(
        @Path("owner") owner: String? = "",
        @Path("repo") repo: String? = ""
    ): Call<GithubReadme>
}