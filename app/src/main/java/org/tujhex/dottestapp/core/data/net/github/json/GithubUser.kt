package org.tujhex.dottestapp.core.data.net.github.json

import com.google.gson.annotations.SerializedName

/**
 * @author tujhex
 * since 27.01.20
 */
data class GithubUser (
    @SerializedName("login") val login:String,
    @SerializedName("id") val id:Long,
    @SerializedName("avatar_url") val avatarUrl:String,
    @SerializedName("url") val url:String
)