package org.tujhex.dottestapp.data.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import org.tujhex.dottestapp.core.data.dto.vk.VkResponse
import org.tujhex.dottestapp.core.data.net.vk.json.VkResponseDeserializer
import org.tujhex.dottestapp.main.MainScope

/**
 * @author tujhex
 * since 27.01.20
 */
@Module
class GsonModule {
    @Provides
    @MainScope
    fun provideVkResponseDeserializer(): JsonDeserializer<VkResponse?> {
        return VkResponseDeserializer()
    }

    @Provides
    @MainScope
    fun provideGson(vkResponseDeserializer: JsonDeserializer<VkResponse?>): Gson {
        return GsonBuilder()
            .registerTypeAdapter(VkResponse::class.java, vkResponseDeserializer)
            .create()
    }
}