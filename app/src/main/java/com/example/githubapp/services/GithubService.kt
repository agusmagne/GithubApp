package com.example.githubapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubService {
    companion object {
        fun getGithubService(): Retrofit {
            return Retrofit.Builder().baseUrl(GithubApiConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}