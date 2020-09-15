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
import kotlinx.android.synthetic.main.survivor_tab.view.*

class SurvivorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.survivor_tab, container, false)!!
        val context = context!!

        MobileAds.initialize(context) {}
        val adRequest = AdRequest.Builder().build()
        root.adView.loadAd(adRequest)

        root.surivorTrialButtom.setOnClickListener{
            val controller = AppDBController(context)
            val cursorPerk = controller.select_random_perks("survivor")
            val cursorTrial = controller.select_random_trial("survivor")

            with(cursorPerk!!){
                while (moveToNext()){
                    val perk_id = getString(getColumnIndexOrThrow("perk_code"))
                    Log.i("Database - perk", perk_id)
                    when (position){
                        0 -> {
                            root.survivorPerkImage1.setImageResource(context.resources.getIdentifier(perk_id, "drawable", context.packageName))
                            root.survivorPerkName1.text = getText(context.resources.getIdentifier(perk_id, "string", context.packageName))
                        }
                        1 -> {
                            root.survivorPerkImage2.setImageResource(context.resources.getIdentifier(perk_id, "drawable", context.packageName))
                            root.survivorPerkName2.text = getText(context.resources.getIdentifier(perk_id, "string", context.packageName))}
                        2 -> {
                            root.survivorPerkImage3.setImageResource(context.resources.getIdentifier(perk_id, "drawable", context.packageName))
                            root.survivorPerkName3.text = getText(context.resources.getIdentifier(perk_id, "string", context.packageName))
                        }
                        3 -> {
                            root.survivorPerkImage4.setImageResource(context.resources.getIdentifier(perk_id, "drawable", context.packageName))
                            root.survivorPerkName4.text = getText(context.resources.getIdentifier(perk_id, "string", context.packageName))
                        }
                    }
                }
            }

            with(cursorTrial!!){
                moveToFirst()
                val trial = getString(getColumnIndexOrThrow("trial_code"))
                Log.i("Database - trial_code", trial)
                root.trialSurvivor.text = getText(context.resources.getIdentifier(trial, "string", context.packageName))
            }

            controller.close()
        }


        root.survivorPerksSetting.setOnClickListener{
            val intent = Intent(context, SettingsActivity::class.java)
            intent.putExtra("type", "survivor")
            startActivity(intent)
        }

        return root
    }
    }