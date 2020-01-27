package org.tujhex.dottestapp.github.model

import androidx.recyclerview.widget.DiffUtil

/**
 * @author tujhex
 * since 28.01.20
 */
class GithubUserDiffCallback : DiffUtil.ItemCallback<GithubUserUiModel>() {
    override fun areItemsTheSame(oldItem: GithubUserUiModel, newItem: GithubUserUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GithubUserUiModel,
        newItem: GithubUserUiModel
    ): Boolean {
        return oldItem == newItem
    }
}