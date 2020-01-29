package org.tujhex.dottestapp.domain.cases.vk.profile

import com.vk.api.sdk.auth.VKAccessToken
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.dottestapp.core.data.dto.vk.VkErrorDto
import org.tujhex.dottestapp.core.data.dto.vk.VkProfileResponse
import org.tujhex.dottestapp.core.data.dto.vk.VkResponse
import org.tujhex.dottestapp.core.data.net.vk.VkApiService
import org.tujhex.dottestapp.domain.cases.vk.token.FetchVkTokenUseCase
import org.tujhex.dottestapp.domain.cases.vk.token.model.AuthToken
import org.tujhex.dottestapp.domain.model.vk.VkProfileEntity

/**
 * @author tujhex
 * since 29.01.20
 */
class RefreshUserProfileUseCaseTest {

    lateinit var refreshUserProfileUseCase: RefreshUserProfileUseCase

    lateinit var vkApiService: VkApiService
    lateinit var fetchVkTokenUseCase: FetchVkTokenUseCase
    lateinit var cacheStorage: CacheStorage.Reactive<VkProfileResponse>


    @Suppress("UNCHECKED_CAST")
    @Before
    fun setUp() {

        vkApiService = mockkClass(VkApiService::class)
        fetchVkTokenUseCase = mockkClass(FetchVkTokenUseCase::class)
        cacheStorage =
            mockkClass(CacheStorage.Reactive::class) as CacheStorage.Reactive<VkProfileResponse>
        refreshUserProfileUseCase =
            RefreshUserProfileUseCase.Impl(vkApiService, cacheStorage, fetchVkTokenUseCase)
    }

    @Test
    fun test_vkTokenWithoutUserId_Error() {
        val testObserver = TestObserver.create<VkProfileEntity>()
        val vkAuthToken = VKAccessToken(mapOf("access_token" to ""))
        val authToken = AuthToken.Vk(vkAuthToken)

        every { fetchVkTokenUseCase.fetchToken() } returns Single.just(authToken)

        refreshUserProfileUseCase.fetchProfile().subscribe(testObserver)

        verify (exactly = 1){ fetchVkTokenUseCase.fetchToken() }
        verify (exactly = 0){ vkApiService.getInfo(any(), any()) }
        verify (exactly = 0){ cacheStorage.store(any()) }
        testObserver.assertError(IllegalArgumentException::class.java)
        testObserver.assertNotComplete()
        testObserver.assertTerminated()
    }

    @Test
    fun test_getProfile_Error() {
        val testObserver = TestObserver.create<VkProfileEntity>()
        val vkAuthToken = VKAccessToken(
            mapOf(
                "access_token" to "",
                "user_id" to "0"
            )
        )
        val authToken = AuthToken.Vk(vkAuthToken)
        val errorDto = VkErrorDto("Test vk exception")
        val response = VkResponse.Error(errorDto)

        every { fetchVkTokenUseCase.fetchToken() } returns Single.just(authToken)
        every { vkApiService.getInfo(any(), any()) } returns Single.just(response)

        refreshUserProfileUseCase.fetchProfile().subscribe(testObserver)

        verify (exactly = 1){ fetchVkTokenUseCase.fetchToken() }
        verify (exactly = 1){ vkApiService.getInfo(any(), any()) }
        verify (exactly = 0){ cacheStorage.store(any()) }
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValue { value -> value is VkProfileEntity.Error }
        testObserver.assertTerminated()
    }

    @Test
    fun test_getProfile_Success() {
        val testObserver = TestObserver.create<VkProfileEntity>()
        val vkAuthToken = VKAccessToken(
            mapOf(
                "access_token" to "",
                "user_id" to "0"
            )
        )
        val authToken = AuthToken.Vk(vkAuthToken)
        val profile = VkResponse.Profile(listOf(VkProfileResponse("test_name", "test_last_name", "")))

        every { fetchVkTokenUseCase.fetchToken() } returns Single.just(authToken)
        every { vkApiService.getInfo(any(), any()) } returns Single.just(profile)
        every { cacheStorage.store(any()) } returns Unit

        refreshUserProfileUseCase.fetchProfile().subscribe(testObserver)

        verify (exactly = 1){ fetchVkTokenUseCase.fetchToken() }
        verify (exactly = 1){ vkApiService.getInfo(any(), any()) }
        verify (exactly = 1){ cacheStorage.store(any()) }
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValue { value -> value is VkProfileEntity.Profile }
        testObserver.assertTerminated()
    }

}