package com.mementos.dbdtrials

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.killer_tab.view.*

class KillerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.killer_tab, container, false)!!
        val context = context!!

        MobileAds.initialize(context) {}
        val adRequest = AdRequest.Builder().build()
        root.adView.loadAd(adRequest)


        root.killerBottomTrial.setOnClickListener{
            val controller = AppDBController(context)
            val cursorPerk = controller.select_random_perks("killer")
            val cursorKiller = controller.select_random_killer()

            with(cursorPerk!!){
                while (moveToNext()){
                    val perk_id = getString(getColumnIndexOrThrow("perk_code"))
                    Log.i("Database - perk", perk_id)
                    when (position){
                        0 -> {
                            root.imagePerkKiller1.setImageResource(context.resources.getIdentifier(perk_id, "drawable", context.packageName))
                            root.descriptionKillerPerk1.text = getText(context.resources.getIdentifier(perk_id, "string", context.packageName))
                        }
                        1 -> {
                            root.imagePerkKiller2.setImageResource(context.resources.getIdentifier(perk_id, "drawable", context.packageName))
                            root.descriptionKillerPerk2.text = getText(context.resources.getIdentifier(perk_id, "string", context.packageName))}
                        2 -> {
                            root.imagePerkKiller3.setImageResource(context.resources.getIdentifier(perk_id, "drawable", context.packageName))
                            root.descriptionKillerPerk3.text = getText(context.resources.getIdentifier(perk_id, "string", context.packageName))
                        }
                        3 -> {
                            root.imagePerkKiller4.setImageResource(context.resources.getIdentifier(perk_id, "drawable", context.packageName))
                            root.descriptionKillerPerk4.text = getText(context.resources.getIdentifier(perk_id, "string", context.packageName))
                        }
                    }
                }
            }
            with(cursorKiller!!){
                moveToFirst()
                val killer = getString(getColumnIndexOrThrow("killer_code"))
                Log.i("Database - killer", killer)
                root.imageKiller.setImageResource(context.resources.getIdentifier(killer, "drawable", context.packageName))
                root.KillerName.text = getText(context.resources.getIdentifier(killer, "string", context.packageName))
            }

            controller.close()
        }

        root.settingKillers.setOnClickListener{
            val intent = Intent(context, SettingsActivity::class.java)
            intent.putExtra("type", "killer")
            startActivity(intent)
        }

        return root
    }
}



