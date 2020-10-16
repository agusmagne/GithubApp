package com.example.githubapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepositoryDTO(
    val full_name: String? = "",
    val name: String? = "",
    val owner: GithubUserDTO?,
    val description: String?,
    val forks_count: Int?,
    val html_url: String?,
    val url: String?,
    val open_issues_count: Int?,
    val language: String?,
    val stargazers_count: Int?,
    val updated_at: String?
) : Parcelable

@Parcelize
class GithubUserDTO(val login: String, val avatar_url: String, val url: String) : Parcelable

@Parcelize
class GithubReadme(val html_url: String? = "", val size: Int? = 1): Parcelable
