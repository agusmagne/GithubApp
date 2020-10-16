package com.example.githubapp.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.githubapp.R
import com.example.githubapp.model.GithubReadme
import com.example.githubapp.model.GithubRepositoryDTO
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    private var details: GithubRepositoryDTO? = null
    private var readme: GithubReadme? = null

    private var profileImgView: ImageView? = null
    private var nameTxt: TextView? = null
    private var creditsTxt: TextView? = null
    private var readmeWebView: WebView? = null
    private var progressbar: ProgressBar? = null
    private var noReadmeTxt: TextView? = null
    private var forksTxt: TextView? = null
    private var issuesTxt: TextView? = null
    private var descTxt: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        details = intent.getParcelableExtra("DETAILS")
        readme = intent.getParcelableExtra("README")
        initViews()
        bindView()
    }

    private fun initViews() {
        profileImgView = findViewById(R.id.profile_picture)
        nameTxt = findViewById(R.id.title)
        creditsTxt = findViewById(R.id.credits)
        readmeWebView = findViewById(R.id.webview)
        progressbar = findViewById(R.id.progressbar_d)
        noReadmeTxt = findViewById(R.id.no_readme_txt)
        forksTxt = findViewById(R.id.forks)
        issuesTxt = findViewById(R.id.open_issues)
        descTxt = findViewById(R.id.description_d)
    }

    private fun bindView() {
        Picasso.with(this).load(details?.owner?.avatar_url)
            .into(profileImgView)
        nameTxt?.text = details?.full_name
        creditsTxt?.text = buildCredits(details?.owner?.login)
        forksTxt?.text = buildForks()
        issuesTxt?.text = buildIssues()
        descTxt?.text = details?.description
        loadReadmeFile()
    }

    private fun buildForks(): String {
        return "Forks: ${details?.forks_count}"
    }

    private fun buildIssues(): String {
        return "Open issues: ${details?.open_issues_count}"
    }

    private fun loadReadmeFile() {
        readme?.size?.let { size ->
            if (size > 1) {
                readmeWebView?.let { webview ->
                    webview.webViewClient = buildWebViewClient()
                    readme?.html_url?.let {
                        webview.loadUrl(it)
                    }
                }
            } else {
                hideReadme()
                hideProgress()
            }
        }
    }

    private fun showReadme() {
        noReadmeTxt?.visibility = View.INVISIBLE
        readmeWebView?.visibility = View.VISIBLE
    }

    private fun hideReadme() {
        noReadmeTxt?.visibility = View.VISIBLE
        readmeWebView?.visibility = View.INVISIBLE
    }

    private fun showProgress() {
        progressbar?.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressbar?.visibility = View.INVISIBLE
    }

    private fun buildWebViewClient(): WebViewClient {
        return object : WebViewClient() {
            override fun onPageFinished(webView: WebView?, url: String?) {
                hideProgress()
                showReadme()
            }
        }
    }

    private fun buildCredits(username: String?): String {
        username?.let {
            return "By @$username"
        }
        return ""
    }
}