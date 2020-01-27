package org.tujhex.dottestapp.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.tujhex.dottestapp.core.MessageUtils
import org.tujhex.dottestapp.domain.cases.vk.profile.GetUserProfileUseCase
import org.tujhex.dottestapp.login.navigation.LoginScreen
import org.tujhex.navigation.Command
import org.tujhex.navigation.Navigator

/**
 * @author tujhex
 * since 27.01.20
 */
class DrawerViewModel(
    getUserProfileUseCase: GetUserProfileUseCase,
    private val navigator: Navigator,
    private val messageUtils: MessageUtils
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
                       {error -> messageUtils.showError(error)})
            .apply {
                compositeDisposable.add(this)
            }
    }

    fun logout() {
        navigator.navigate(Command.Root)
        navigator.navigate(Command.Forward(LoginScreen()))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}