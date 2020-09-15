package com.mementos.dbdtrials

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(SurvivorFragment(), getString(R.string.survivor))
        adapter.addFragment(KillerFragment(), getString(R.string.killer))

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        supportActionBar?.hide()
    }

}
