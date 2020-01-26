package org.tujhex.dottestapp.core.data.net.vk.json

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.tujhex.dottestapp.core.data.dto.vk.VkResponse
import java.lang.reflect.Type

/**
 * @author tujhex
 * since 27.01.20
 */
class VkResponseDeserializer : JsonDeserializer<VkResponse?> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): VkResponse? {
        if (json == null) {
            return null
        }
        json.asJsonObject
            .let { obj ->
                return if (obj.has(VkJsonNames.error)) {
                    context!!.deserialize(json, VkResponse.Error::class.java) as VkResponse
                } else {
                    context!!.deserialize(json, VkResponse.Profile::class.java) as VkResponse
                }
            }
    }
}