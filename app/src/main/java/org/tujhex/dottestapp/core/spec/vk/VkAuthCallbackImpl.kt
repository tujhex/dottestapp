package org.tujhex.dottestapp.core.spec.vk

import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.tujhex.dottestapp.R
import org.tujhex.dottestapp.core.MessageUtils
import org.tujhex.dottestapp.domain.cases.vk.profile.RefreshUserProfileUseCase
import org.tujhex.dottestapp.domain.cases.vk.token.StoreVkTokenUseCase
import org.tujhex.dottestapp.github.navigation.GithubSearchScreen
import org.tujhex.navigation.Command
import org.tujhex.navigation.Navigator

/**
 * @author tujhex
 * since 26.01.20
 */
class VkAuthCallbackImpl(
    private val messageUtils: MessageUtils,
    private val navigator: Navigator,
    private val storeVkTokenUseCase: StoreVkTokenUseCase,
    private val refreshUserProfileUseCase: RefreshUserProfileUseCase,
    private val compositeDisposable: CompositeDisposable
) : VKAuthCallback {
    override fun onLogin(token: VKAccessToken) {
        storeVkTokenUseCase.storeToken(token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                           refreshUserProfileUseCase
                               .fetchProfile()
                               .observeOn(AndroidSchedulers.mainThread())
                               .subscribe({
                                              navigator.navigate(
                                                  Command.Forward(
                                                      GithubSearchScreen()
                                                  )
                                              )
                                          },
                                          { error -> messageUtils.showError(error) })
                               .apply { compositeDisposable.add(this) }
                       }, { error -> messageUtils.showError(error) })
            .apply { compositeDisposable.add(this) }
    }

    override fun onLoginFailed(errorCode: Int) {
        messageUtils.showMessage(R.string.vk_auth_error)
    }

}