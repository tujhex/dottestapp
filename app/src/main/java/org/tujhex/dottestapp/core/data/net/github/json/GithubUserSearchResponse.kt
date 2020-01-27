package org.tujhex.dottestapp.core.data.net.github.json

import com.google.gson.annotations.SerializedName

/**
 * @author tujhex
 * since 27.01.20
 */
data class GithubUserSearchResponse(
    @SerializedName("total_count") val totalCount: Long,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val users: List<GithubUser>
)