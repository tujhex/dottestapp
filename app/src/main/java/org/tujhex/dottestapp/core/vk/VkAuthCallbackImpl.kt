package org.tujhex.dottestapp.core.vk

import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.tujhex.dottestapp.R
import org.tujhex.dottestapp.core.MessageUtils
import org.tujhex.dottestapp.core.cases.vk.StoreVkTokenUseCase
import org.tujhex.navigation.Navigator

/**
 * @author tujhex
 * since 26.01.20
 */
class VkAuthCallbackImpl(
    private val messageUtils: MessageUtils,
    private val navigator: Navigator,
    private val storeVkTokenUseCase: StoreVkTokenUseCase,
    private val compositeDisposable: CompositeDisposable
) : VKAuthCallback {
    override fun onLogin(token: VKAccessToken) {
        storeVkTokenUseCase.storeToken(token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { error -> messageUtils.showError(error) })
            .apply { compositeDisposable.add(this) }
    }

    override fun onLoginFailed(errorCode: Int) {
        messageUtils.showMessage(R.string.vk_auth_error)
    }

}