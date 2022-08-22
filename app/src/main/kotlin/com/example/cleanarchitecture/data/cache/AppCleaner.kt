package com.example.cleanarchitecture.data.cache

import com.example.cleanarchitecture.data.cache.database.LocalDatabase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppCleaner @Inject constructor(
    private val localDatabase: LocalDatabase,
) {
    fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            localDatabase.clearAllTables()
        }
    }
}
