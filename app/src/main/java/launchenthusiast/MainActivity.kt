package launchenthusiast

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import launchenthusiast.adapters.RecyclerAdapter
import launchenthusiast.apiservices.API
import launchenthusiast.apiservices.PollAPIList
import launchenthusiast.cache.APICache
import launchenthusiast.cache.ImageCache
import launchenthusiast.domain.models.*
import launchenthusiast.views.ViewPopulator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        engine_view.apply {
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        initializePermissions()
    }

    private fun initializePermissions(){
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
        }

        APICache.initialize(this)
        ImageCache.initialize(this)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            WRITE_EXTERNAL_STORAGE_REQUEST_CODE->{
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    println("cum")
                else{
                    APICache.initialize(this)
                    ImageCache.initialize(this)
                }

            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(nav_view.checkedItem?.itemId == item.itemId) return true
        when(item.itemId){
            R.id.nav_engines -> {
                PollAPIList(fun(l : List<Engine>){
                    engine_view.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = RecyclerAdapter(l, DatabaseObject.ENGINE)
                    }
                }, API.LAUNCH_ENTHUSIAST).execute(DatabaseObject.ENGINE)
            }
            R.id.nav_rockets -> {
                PollAPIList(fun(l : List<Rocket>){
                    engine_view.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = RecyclerAdapter(l, DatabaseObject.ROCKET)
                    }
                }, API.LAUNCH_ENTHUSIAST).execute(DatabaseObject.ROCKET)
            }
            R.id.nav_launches -> {
                PollAPIList<LaunchLibraryLaunch>(fun(l : List<LaunchLibraryLaunch>){
                    engine_view.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = RecyclerAdapter(l, DatabaseObject.LAUNCH_LIBRARY_LAUNCH)
                    }
                }, API.LAUNCH_LIBRARY).execute(DatabaseObject.LAUNCH_LIBRARY_LAUNCH)
            }
        }

        drawer_layout.closeDrawers()
        return true
    }
}