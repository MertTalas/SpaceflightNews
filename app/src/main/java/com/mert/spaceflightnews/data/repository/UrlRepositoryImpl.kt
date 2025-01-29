package com.mert.spaceflightnews.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.mert.spaceflightnews.domain.repository.UrlRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UrlRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UrlRepository {
    override fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Could not open the URL",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}