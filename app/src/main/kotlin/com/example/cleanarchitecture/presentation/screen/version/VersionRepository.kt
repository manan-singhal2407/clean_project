package com.example.cleanarchitecture.presentation.screen.version

import com.example.cleanarchitecture.data.network.model.AppVersion
import com.example.cleanarchitecture.domain.state.DataState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

class VersionRepository(
    private val databaseReference: DatabaseReference,
    private val moshi: Moshi
) {

    fun retrieveAppVersion(): Flow<DataState<AppVersion>> = callbackFlow {
        databaseReference
            .child(APP_VERSION_ENDPOINT)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val result = snapshot.value as HashMap<*, *>
                    val jsonObject = JSONObject(result)
                    val jsonAdapter: JsonAdapter<AppVersion> = moshi.adapter(AppVersion::class.java)
                    CoroutineScope(Dispatchers.IO).launch {
                        runCatching {
                            val appVersion = jsonAdapter.fromJson(jsonObject.toString())
                            if (appVersion == null) {
                                trySend(DataState.error("Failed to convert data to app version model class")).isSuccess
                            } else {
                                trySend(DataState.success(appVersion)).isSuccess
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(DataState.error("Failed to load data")).isSuccess
                }
            })
        awaitClose()
    }

    companion object {
        const val APP_VERSION_ENDPOINT = "AppVersion"
    }
}