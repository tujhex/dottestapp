package org.tujhex.dottestapp.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_github_search.*
import org.tujhex.dottestapp.R
import org.tujhex.dottestapp.core.ui.HasChangeableToolbar
import org.tujhex.dottestapp.core.ui.HasDrawer

/**
 * @author tujhex
 * since 26.01.20
 */
class GithubSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_github_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            if (activity is HasDrawer) {
                activity.unlockDrawer()
            }
            if (activity is HasChangeableToolbar) {
                activity.setupAppBar(toolbar)
            }
        }

        val githubSearchListAdapter = GithubSearchListAdapter()
        github_search_result_list.adapter = githubSearchListAdapter
        github_search_result_list.layoutManager = LinearLayoutManager(context)
    }

}