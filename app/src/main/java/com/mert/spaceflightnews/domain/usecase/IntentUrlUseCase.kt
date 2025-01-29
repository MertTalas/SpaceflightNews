package com.mert.spaceflightnews.domain.usecase

import com.mert.spaceflightnews.domain.repository.UrlRepository
import javax.inject.Inject

class IntentUrlUseCase @Inject constructor(
    private val urlRepository: UrlRepository
) {

    operator fun invoke(url: String) {
        urlRepository.openUrl(url)
    }
}