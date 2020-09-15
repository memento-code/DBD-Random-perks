package com.mementos.dbdtrials

import android.content.Context
import com.abnerescocio.assetssqlite.lib.AssetsSQLite

class AppAssetsSQLite(context: Context): AssetsSQLite(context, DATABASE_NAME) {
    companion object {
        const val DATABASE_NAME = "app_data.db"
    }
}