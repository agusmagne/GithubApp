package com.example.githubapp.ui

import com.example.githubapp.model.GithubReadme
import com.example.githubapp.model.GithubRepositoryDTO

interface MainView {

    fun createDetailsActivity(repositoryDTO: GithubRepositoryDTO, readme: GithubReadme?)

}