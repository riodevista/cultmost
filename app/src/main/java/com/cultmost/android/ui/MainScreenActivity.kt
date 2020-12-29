package com.cultmost.android.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.cultmost.android.R
import com.cultmost.android.ui.ar.ArModelsFragment
import com.cultmost.android.ui.courses.CoursesFragment
import com.cultmost.android.ui.main.MainScreenFragment
import com.cultmost.android.ui.materials.MaterialsFragment
import com.cultmost.android.usecases.AuthUseCase
import com.cultmost.android.util.CameraPermissionHelper
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.withIdentifier
import com.mikepenz.materialdrawer.model.interfaces.withName
import kotlinx.android.synthetic.main.activity_main.*


class MainScreenActivity : AppCompatActivity() {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    // Set to true ensures requestInstall() triggers installation if necessary.
    private val mUserRequestedInstall = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setupToolbar()
//        switchToMainFragment()
        setupNavDrawer()
        slider.setSelection(0L, true)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            root,
            toolbar,
            com.mikepenz.materialdrawer.R.string.material_drawer_open,
            com.mikepenz.materialdrawer.R.string.material_drawer_close
        )
        root.addDrawerListener(actionBarDrawerToggle)
    }

    private fun setupNavDrawer() {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        val item0 = PrimaryDrawerItem().withIdentifier(0).withName(R.string.main_screen)
        val item1 = PrimaryDrawerItem().withIdentifier(1).withName(R.string.materials)
        val item2 = PrimaryDrawerItem().withIdentifier(2).withName(R.string.courses)
        val item3 = PrimaryDrawerItem().withIdentifier(3).withName(R.string.ar_models)
        val item4 = PrimaryDrawerItem().withIdentifier(4).withName(R.string.about_us)
        val item5 = PrimaryDrawerItem().withIdentifier(5).withName(
            if (AuthUseCase.getUserEmail(applicationContext).equals("no_email"))
                "Авторизация"
            else
                getString(R.string.logout)
        )


        slider.headerView = ImageView(this).apply {
            setImageResource(R.drawable.splash_logo)
            scaleType = ImageView.ScaleType.FIT_CENTER
            adjustViewBounds = true
            val paddingTop = resources.getDimensionPixelSize(R.dimen.top_header_padding)
            val paddingLeft = resources.getDimensionPixelSize(R.dimen.left_header_padding)
            setPadding(
                paddingLeft,
                paddingTop,
                paddingLeft,
                paddingTop / 2
            )
        }
        // get the reference to the slider and add the items
        slider.itemAdapter.add(
            item0,
            DividerDrawerItem(),
            item1,
            DividerDrawerItem(),
            item2,
            DividerDrawerItem(),
            item3,
            DividerDrawerItem(),
            item4,
            DividerDrawerItem(),
            item5
        )

        // specify a click listener
        slider.onDrawerItemClickListener = { v, drawerItem, position ->
            // do something with the clicked item :D
            when (drawerItem.identifier) {
                0L -> {
                    switchToMainFragment()
                }
                1L -> {
                    switchToMaterialsFragment()
                }
                2L -> {
                    switchToCoursesFragment()
                }
                3L -> {
                    switchToARFragment()
                }
                4L -> {
                    switchToAboutUsFragment()
                }
                5L -> {
                    AuthUseCase.logout(applicationContext)
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }

            }
            false
        }
    }

    private fun switchToMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainScreenFragment.newInstance())
            .commit()
    }

    private fun switchToMaterialsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MaterialsFragment.newInstance())
            .commit()
    }

    private fun switchToCoursesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, CoursesFragment.newInstance())
            .commit()
    }

    private fun switchToARFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, ArModelsFragment.newInstance())
            .commit()
    }

    private fun switchToAboutUsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, AboutUsFragment.newInstance())
            .commit()
    }


    override fun onResume() {
        super.onResume()
        actionBarDrawerToggle.syncState()

        // ARCore requires camera permission to operate.
        if (!CameraPermissionHelper.hasCameraPermission(this)) {
            CameraPermissionHelper.requestCameraPermission(this)
            return
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (!CameraPermissionHelper.hasCameraPermission(this)) {
            Toast.makeText(
                this,
                "Camera permission is needed to run this application",
                Toast.LENGTH_SHORT
            )
                .show()
            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(this)) {
                // Permission denied with checking "Do not ask again".
                CameraPermissionHelper.launchPermissionSettings(this)
            }
            finish()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(_outState: Bundle) {
        var outState = _outState
        //add the values which need to be saved from the drawer to the bundle
        outState = slider.saveInstanceState(outState)

        //add the values which need to be saved from the accountHeader to the bundle
//        outState = headerView.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }


    override fun onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (root.isDrawerOpen(slider)) {
            root.closeDrawer(slider)
        } else {
            super.onBackPressed()
        }
    }

    fun showCourses() {
        slider.setSelectionAtPosition(5, false)
        switchToCoursesFragment()
    }

    fun showMaterials() {
        slider.setSelectionAtPosition(3, false)
        switchToMaterialsFragment()
    }

    fun showArModels() {
        slider.setSelectionAtPosition(7, false)
        switchToARFragment()
    }
}