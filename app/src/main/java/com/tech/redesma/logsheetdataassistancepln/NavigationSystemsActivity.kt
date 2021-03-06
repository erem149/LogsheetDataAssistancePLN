package com.tech.redesma.logsheetdataassistancepln

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_navigation_systems.*

class NavigationSystemsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_systems)

        verifyUserIsLoggedIn()

        supportActionBar?.title = "Social Activity"

        float_add_data.setOnClickListener {
            val intent = Intent (this, AddItem::class.java)
            startActivity(intent)
        }

        float_view_data.setOnClickListener {
            val intent = Intent (this, ListItem::class.java)
            startActivity(intent)
        }
    }

    private fun verifyUserIsLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.menu_combine -> {
                val intent = Intent (this, CombineActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_select_user -> {
                val intent = Intent (this, FindUserActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_my_navigation -> {
                val intent = Intent (this, MyNavigationActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



}
