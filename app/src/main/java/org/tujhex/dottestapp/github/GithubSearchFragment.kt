package org.tujhex.dottestapp.github

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_github_search.*
import org.tujhex.dottestapp.R
import org.tujhex.dottestapp.core.HasDiComponent
import org.tujhex.dottestapp.core.MessageUtils
import org.tujhex.dottestapp.core.ui.HasChangeableToolbar
import org.tujhex.dottestapp.core.ui.HasDrawer
import org.tujhex.dottestapp.github.model.GithubSearchViewModel
import org.tujhex.dottestapp.github.model.GithubUserUiModel
import org.tujhex.dottestapp.main.MainComponent
import org.tujhex.dottestapp.main.model.MainProviderFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author tujhex
 * since 26.01.20
 */
class GithubSearchFragment : Fragment() {

    @Inject
    lateinit var factory: MainProviderFactory
    @Inject
    lateinit var messageUtils: MessageUtils

    private var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_github_search, container, false)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is HasDiComponent<*>) {
            (activity as HasDiComponent<MainComponent>).getComponent().plusGithub().inject(this)
        }
        disposable?.dispose()
    }

    override fun onDetach() {
        super.onDetach()
        disposable?.dispose()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val githubSearchListAdapter = GithubSearchListAdapter()
        github_search_result_list.adapter = githubSearchListAdapter
        github_search_result_list.layoutManager = LinearLayoutManager(context)

        activity?.let { activity ->
            if (activity is HasDrawer) {
                activity.unlockDrawer()
            }
            if (activity is HasChangeableToolbar) {
                activity.setupAppBar(toolbar)
            }

            val viewModel =
                ViewModelProvider(activity, factory)[GithubSearchViewModel::class.java]
            viewModel.hasNext.observe(viewLifecycleOwner, Observer<Boolean> {})
            viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {})
            viewModel.currentPage.observe(viewLifecycleOwner, Observer<Int> {})
            github_search_result_list.addOnScrollListener(
                RecyclerEndlessScrollListener(
                    viewModel.isLoading,
                    viewModel.hasNext,
                    viewModel.currentPage
                ) { page -> viewModel.nextPage(page) })
            viewModel.users.observe(
                viewLifecycleOwner,
                Observer<List<GithubUserUiModel>> { data ->
                    githubSearchListAdapter.submitList(data)
                })
            disposable = RxSearchView.queryTextChanges(search_view)
                .debounce(600, TimeUnit.MILLISECONDS)
                .subscribe({ viewModel.refreshQuery(it.toString()) },
                           { error -> messageUtils.showError(error) })

        }
    }

}