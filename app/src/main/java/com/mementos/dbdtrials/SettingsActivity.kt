package com.mementos.dbdtrials

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    private val listItems = ArrayList<SettingItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val type = intent.getStringExtra("type")
        Log.i("Settings type", type!!)

        val recyclerPerks = findViewById<ListView>(R.id.perkList)
        val controller = AppDBController(this)
        val allKillers = controller.select_all_killers()
        val allPerks = controller.select_all_perks_by_type(type)

        if (type == "killer") {
            getListItemsByType(allKillers, "killer")
        }

        getListItemsByType(allPerks, "perk")
        controller.close()

        val adapter = SettingAdapter(this, listItems)
        recyclerPerks.adapter = adapter
    }

    fun getListItemsByType(cursor: Cursor?, type: String){
        listItems.add(SettingItemData(
            code = type.capitalize(),
            is_active = 1,
            is_header = true,
            type_data = type,
            type_perk = "killer"
        ))
        Log.i("wtf", type)

        with(cursor!!){
            while (moveToNext()){
                listItems.add(
                    SettingItemData(
                        getString(getColumnIndexOrThrow("${type}_code")),
                        getString(getColumnIndexOrThrow("type")),
                        type,
                        is_active=getInt(getColumnIndexOrThrow("is_active"))
                        )
                )
            }
        }
    }



}
