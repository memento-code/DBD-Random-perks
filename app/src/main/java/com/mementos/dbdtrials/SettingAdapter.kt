package com.mementos.dbdtrials

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView


class SettingAdapter(private val context: Context,
                  private val dataSource: ArrayList<SettingItemData>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val perk = getItem(position) as SettingItemData

        if (!perk.is_header) {
            val rowView = inflater.inflate(R.layout.list_item, parent, false)
            val perkName = rowView.findViewById<TextView>(R.id.perkName)
            val imagePerk = rowView.findViewById<ImageView>(R.id.imagePerk)
            val checkboxPerk = rowView.findViewById<CheckBox>(R.id.checkboxPerk)
            Log.i("Test", perk.code)

            perkName.text = rowView.resources.getString(
                rowView.context.resources.getIdentifier(
                    perk.code,
                    "string",
                    rowView.context.packageName
                )
            )
            imagePerk.setImageResource(
                context.resources.getIdentifier(
                    perk.code,
                    "drawable",
                    context.packageName
                )
            )
            Log.i("Adapter", perk.is_active.toString())
            Log.i("Adapter", perk.type_data)
            checkboxPerk.isChecked = perk.is_active == 1
            rowView.setOnClickListener {
                checkboxPerk.isChecked = !checkboxPerk.isChecked
                dataSource[position] = SettingItemData(
                    perk.code,
                    perk.type_perk,
                    perk.type_data,
                    if(checkboxPerk.isChecked) 1 else 0,
                    false
                )
                val controller = AppDBController(context)
                controller.change_status_item(perk.code, checkboxPerk.isChecked, perk.type_data, perk.type_perk)

                notifyDataSetChanged()
            }

            return rowView
        }
        else {
            val rowView = inflater.inflate(R.layout.list_section, parent, false)
            val sectionText = rowView.findViewById<TextView>(R.id.header_listview)
            sectionText.text = perk.code

            return rowView
        }
    }


}