package org.tujhex.dottestapp.github.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_github_user.view.*

/**
 * @author tujhex
 * since 28.01.20
 */
class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(githubUserUiModel: GithubUserUiModel) {
        itemView.github_user_login.text = githubUserUiModel.name
        Picasso.get().cancelRequest(itemView.github_user_image)
        Picasso.get()
            .load(githubUserUiModel.url)
            .resize(240, 240)
            .into(itemView.github_user_image)
}
}