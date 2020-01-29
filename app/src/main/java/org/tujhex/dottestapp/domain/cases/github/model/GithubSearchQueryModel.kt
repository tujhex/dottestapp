package org.tujhex.dottestapp.domain.cases.github.model

import org.tujhex.dottestapp.github.model.GithubUserUiModel

/**
 * @author tujhex
 * since 29.01.20
 */
data class GithubSearchQueryModel(val users: List<GithubUserUiModel>, val totalCount: Int)