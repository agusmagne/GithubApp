package com.example.githubapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.R
import com.example.githubapp.model.GithubReadme
import com.example.githubapp.model.GithubRepositoryDTO
import com.example.githubapp.model.GithubResponse
import com.example.githubapp.services.GithubApi
import com.example.githubapp.services.GithubService
import com.example.githubapp.ui.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel(private val context: Context, private val iMainView: MainView) :
    ViewModel() {

    private val GET_REPOS_ERROR = context.getString(R.string.GET_REPOS_ERROR)
    private val GET_README_ERROR = "Error getting Readme File"

    val liveData: MutableLiveData<MutableList<GithubRepositoryDTO>> = MutableLiveData()

    private var githubApi: GithubApi? = null

    fun searchRepositories(repository: String) {
        if (repository.isNotBlank()) {
            val retrofit = GithubService.getGithubService()
            val githubApi = retrofit.create(GithubApi::class.java)
            this.githubApi = githubApi
            val call = githubApi.getRepositories(repository)
            call.enqueue(createSearchCallback())
        }
    }

    fun openDetailsActivity(repository: GithubRepositoryDTO) {
        val call = githubApi?.getReadme(repository.owner?.login, repository.name)
        call?.enqueue(createOpenDetailsCallback(repository))

    }

    private fun createOpenDetailsCallback(repository: GithubRepositoryDTO): Callback<GithubReadme?> {
        return object : Callback<GithubReadme?> {
            override fun onResponse(call: Call<GithubReadme?>, response: Response<GithubReadme?>) {
                iMainView.createDetailsActivity(repository, response.body())

            }

            override fun onFailure(call: Call<GithubReadme?>, t: Throwable) {
                Toast.makeText(context, GET_README_ERROR, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createSearchCallback(): Callback<GithubResponse<GithubRepositoryDTO>?> {
        return object : Callback<GithubResponse<GithubRepositoryDTO>?> {
            override fun onResponse(
                call: Call<GithubResponse<GithubRepositoryDTO>?>,
                response: Response<GithubResponse<GithubRepositoryDTO>?>
            ) {
                liveData.value = response.body()?.items
            }

            override fun onFailure(
                call: Call<GithubResponse<GithubRepositoryDTO>?>,
                t: Throwable
            ) {
                println(t.message)
                Toast.makeText(context, GET_REPOS_ERROR, Toast.LENGTH_LONG).show()
            }
        }
    }
}