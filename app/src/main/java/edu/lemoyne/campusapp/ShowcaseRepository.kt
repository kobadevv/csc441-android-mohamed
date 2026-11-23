package edu.lemoyne.campusapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShowcaseRepository (private val api: ShowcaseAPI = ShowcaseService.api) {

    suspend fun loadItems(): List<ShowcaseItem> =
        withContext(Dispatchers.IO) {
            api.getShowcaseItem(ShowcaseService.API_KEY)
        }
}