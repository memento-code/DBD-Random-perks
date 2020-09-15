package com.mementos.dbdtrials

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.core.content.contentValuesOf

open class AppDBController(context: Context) {
    private val sqLiteDatabase: SQLiteDatabase?
    private val TABLES = contentValuesOf(
        "killer" to "killers",
        "perk" to "perks"
    )

    init {
        val appAssetsSQLite = AppAssetsSQLite(context)
        sqLiteDatabase = appAssetsSQLite.writableDatabase
    }

    fun select_all_perks_by_type(type: String): Cursor? {
        return sqLiteDatabase?.rawQuery("SELECT perk_code, is_active, type FROM perks WHERE type = ? ORDER BY perk_code", Array(1){type})
    }

    fun select_all_killers(): Cursor? {
        return sqLiteDatabase?.rawQuery("SELECT killer_code, is_active, 'game-killer' as type FROM killers ORDER BY killer_code", null)
    }

    fun select_random_perks(type: String): Cursor? {
        return sqLiteDatabase?.rawQuery("SELECT perk_code FROM perks WHERE type = ? " +
                "AND is_active ORDER BY RANDOM() LIMIT 4", Array(1){type})
    }

    fun select_random_killer(): Cursor? {
        return sqLiteDatabase?.rawQuery("SELECT killer_code FROM killers WHERE is_active " +
                "ORDER BY RANDOM() LIMIT 1", null)
    }

    fun select_random_trial(type: String): Cursor? {
        return sqLiteDatabase?.rawQuery("SELECT trial_code FROM trials WHERE type = ? " +
                "ORDER BY RANDOM() LIMIT 1", Array(1){type})
    }

    fun change_status_item(perk_id: String, is_active: Boolean, type_data: String, type_perk: String): Long? {
        val values = contentValuesOf(
            "${type_data}_code" to perk_id,
            "is_active" to is_active
        )
        if (type_data == "perk"){ values.put("type", type_perk) }
        Log.i("AppDBController", "Start replace with args: $perk_id $is_active $type_perk")
        val i = sqLiteDatabase?.replace(TABLES.getAsString(type_data), null, values)
        Log.i("AppDBController", "Status replace: $i")

        return i
    }


    fun close() {
        sqLiteDatabase?.close()
    }
}