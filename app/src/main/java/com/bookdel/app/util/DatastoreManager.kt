package com.bookdel.app.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bookdel.app.Data.UserDetails
import kotlinx.coroutines.flow.map
import java.util.Locale

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "myDataStore")
class DatastoreManager(val context: Context) {
    companion object {
       private val USERNAME = stringPreferencesKey("username")
    }

    suspend fun updatePreferences(username: String) {
        context.dataStore.edit {
            it[USERNAME] = username
        }
    }

    fun getPreferences() = context.dataStore.data.map { UserDetails("${it[USERNAME]}".replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }) }
}