package com.tech.redesma.logsheetdataassistancepln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FindUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_user)

        supportActionBar?.title = "Pilih User"
    }
}
