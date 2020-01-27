package org.tujhex.dottestapp.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.tujhex.dottestapp.domain.vk.cases.profile.GetUserProfileUseCase

/**
 * @author tujhex
 * since 27.01.20
 */
class DrawerViewModel(
    getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {
    private val _profileName = MutableLiveData<CharSequence>()
    private val _profileImageUrl = MutableLiveData<CharSequence>()
    val profileName: LiveData<CharSequence> = _profileName
    val profileImageUrl: LiveData<CharSequence> = _profileImageUrl
    private val compositeDisposable = CompositeDisposable()

    init {
        getUserProfileUseCase
            .getProfile()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                           _profileName.postValue("${data.firstName} ${data.lastName}")
                           _profileImageUrl.postValue(data.urlPhoto)
                       },
                       {})
            .apply {
                compositeDisposable.add(this)
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}