package com.example.githubapp.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.model.GithubRepositoryDTO
import java.text.SimpleDateFormat
import java.util.*

class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var nameTxt: TextView? = itemView.findViewById(R.id.name)
    private var descTxt: TextView? = itemView.findViewById(R.id.description)
    private var scoreTxt: TextView? = itemView.findViewById(R.id.score)
    private var languageTxt: TextView? = itemView.findViewById(R.id.language)
    private var lastupdateTxt: TextView? = itemView.findViewById(R.id.lastupdate)

    fun bindView(model: GithubRepositoryDTO) {
        nameTxt?.text = model.full_name
        descTxt?.text = model.description
        scoreTxt?.text = model.stargazers_count?.toString()
        languageTxt?.text = model.language
        lastupdateTxt?.text = buildLastUpdate(model.updated_at)
    }

    private fun buildLastUpdate(updatedAt: String?): String {
        updatedAt?.let {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.ROOT)
            val calendar = Calendar.getInstance()
            calendar.time = dateFormat.parse(it)!!
            val year = calendar.get(Calendar.YEAR).toString()
            val month = calendar.get(Calendar.MONTH).toString()
            val day = calendar.get(Calendar.DAY_OF_MONTH).toString()
            return "Last modified on $day/$month/$year"
        }
        return ""
    }

}