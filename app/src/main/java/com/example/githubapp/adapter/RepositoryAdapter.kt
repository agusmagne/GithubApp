package com.example.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.model.GithubRepositoryDTO
import com.example.githubapp.ui.RepositoryViewHolder

class RepositoryAdapter(
    private val repositories: MutableList<GithubRepositoryDTO>,
    private val onRepositoryClick: (GithubRepositoryDTO) -> Unit
) :
    RecyclerView.Adapter<RepositoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repository_single, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bindView(repository)
        holder.itemView.setOnClickListener { onRepositoryClick(repository) }
    }

    override fun getItemCount(): Int = repositories.size

    fun updateList(newList: List<GithubRepositoryDTO>?) {
        newList?.let {
            repositories.clear()
            repositories.addAll(it)
        }
    }


}
