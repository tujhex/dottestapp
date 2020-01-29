package org.tujhex.dottestapp.github.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.tujhex.dottestapp.core.MessageUtils
import org.tujhex.dottestapp.core.spec.kotlin.safeOr
import org.tujhex.dottestapp.domain.cases.github.GithubSearchUserUseCase

/**
 * @author tujhex
 * since 29.01.20
 */
class GithubSearchViewModel(
    private val githubSearchUserUseCase: GithubSearchUserUseCase,
    private val messageUtils: MessageUtils
) :
    ViewModel() {
    private val _queryResult = MutableLiveData<List<GithubUserUiModel>>()
    private val _hasNext = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()
    private val _currentPage = MutableLiveData<Int>()

    val users: LiveData<List<GithubUserUiModel>> = _queryResult
    val hasNext: LiveData<Boolean> = _hasNext
    val isLoading = MutableLiveData<Boolean>()
    val currentPage: LiveData<Int> = _currentPage
    private var queryCache: String? = null

    fun refreshQuery(query: String) {
        _currentPage.postValue(0)
        if (query.isEmpty()) {
            _queryResult.postValue(null)
            return
        }
        queryCache = query
        val disposable = githubSearchUserUseCase.searchUser(query, _currentPage.value.safeOr(0))
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.postValue(true) }
            .doFinally { isLoading.postValue(false) }
            .subscribe({ data ->
                           _queryResult.postValue(data.users)
                           _hasNext.postValue(data.users.size < data.totalCount)
                       },
                       { error -> messageUtils.showError(error) })
        compositeDisposable.add(disposable)
    }

    fun nextPage(page: Int) {
        if (queryCache == null) {
            return
        }
        _currentPage.value = (page)
        val disposable =
            githubSearchUserUseCase.searchUser(queryCache!!, _currentPage.value.safeOr(page))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.postValue(true) }
                .doFinally { isLoading.postValue(false) }
                .subscribe({ data ->
                               val composed = _queryResult.value.orEmpty().toMutableList()
                               composed.addAll(data.users)
                               _queryResult.postValue(composed)
                               _hasNext.postValue(composed.size < data.totalCount)
                           },
                           { error -> messageUtils.showError(error) })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}