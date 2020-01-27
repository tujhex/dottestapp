package org.tujhex.dottestapp.github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.tujhex.dottestapp.R
import org.tujhex.dottestapp.github.model.GithubUserDiffCallback
import org.tujhex.dottestapp.github.model.GithubUserUiModel
import org.tujhex.dottestapp.github.model.GithubUserViewHolder

/**
 * @author tujhex
 * since 28.01.20
 */
class GithubSearchListAdapter :
    ListAdapter<GithubUserUiModel, GithubUserViewHolder>(GithubUserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        return GithubUserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_github_user,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}