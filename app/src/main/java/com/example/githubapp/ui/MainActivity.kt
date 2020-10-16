package com.example.githubapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.adapter.RepositoryAdapter
import com.example.githubapp.model.GithubReadme
import com.example.githubapp.model.GithubRepositoryDTO
import com.example.githubapp.viewmodel.RepositoryViewModel

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var viewModel: RepositoryViewModel
    private lateinit var rvAdapter: RepositoryAdapter

    private var edtxt: EditText? = null
    private var rv: RecyclerView? = null
    private var progressbar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = RepositoryViewModel(this, this)
        initViews()
        buildRv()
        viewModel.liveData.observe(this,
            { t -> updateRv(t) })
        edtxt?.setOnEditorActionListener { textView, i, keyEvent ->
            showProgress()
            viewModel.searchRepositories(textView.text.toString().trim())
            false
        }
    }

    private fun updateRv(newList: List<GithubRepositoryDTO>) {
        rvAdapter.updateList(newList)
        rvAdapter.notifyDataSetChanged()
        hideProgress()
    }

    private fun initViews() {
        edtxt = findViewById(R.id.search_edtxt)
        rv = findViewById(R.id.rv)
        progressbar = findViewById(R.id.progressbar)
    }

    private fun buildRv() {
        rv?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            rvAdapter = RepositoryAdapter(mutableListOf()) {
                viewModel.openDetailsActivity(it)
            }
            adapter = rvAdapter
        }
    }

    override fun showProgress() {
        progressbar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressbar?.visibility = View.INVISIBLE
    }

    override fun createDetailsActivity(repositoryDTO: GithubRepositoryDTO, readme: GithubReadme?) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("DETAILS", repositoryDTO)
        intent.putExtra("README", readme)
        startActivity(intent)
    }

}