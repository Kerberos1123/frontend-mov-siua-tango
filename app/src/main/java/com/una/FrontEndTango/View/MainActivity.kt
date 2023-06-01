package com.una.FrontEndTango.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.una.FrontEndTango.R
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.una.FrontEndTango.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {

    private var user_type = 1 // 0: ADMIN - 1:GUARDA - 2:PROFE - 3:ESTUDIANTE - 4:DEBUG
    private val fragment_directions = linkedMapOf(0 to R.id.menuAdmin, 1 to R.id.menuGuarda,
        2 to R.id.menuProfe, 3 to R.id.menuEstudiante, 4 to R.id.buttonHome
    )

    // Variables de control del grafo de navegacion
    private lateinit var navController: NavController
    private lateinit var navGraph : NavGraph
    private lateinit var navHostFragment : NavHostFragment


    //PARA VIEWBINDING
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // With View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.buttonHome, R.id.buttonProfile)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)



        /* sin viewbinding

        // Seleccionar el view display de fragments y seleccionarlo como el host de navegacion de fragments
        navHostFragment = supportFragmentManager.findFragmentById(R.id.app_fragment) as NavHostFragment

        val graphInflater = navHostFragment.navController.navInflater
        navGraph = graphInflater.inflate(R.navigation.nav_graph) // Grafo de navegacion
        navController = navHostFragment.navController // Controlador de navegacion

        // Seleccionar el fragment inicial y navegar
        fragment_directions[user_type]?.let { navGraph.setStartDestination(it) } // Seleccionar el fragment inicial segun el tipo de usuario
        navController.graph = navGraph // Reiniciar el fragment inicial


        // Navegacion de la barra de abajo
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {

                //R.id.buttonBack ->navController.navigate(R.id.buttonHome)
                R.id.buttonHome -> fragment_directions[user_type]?.let { it1 -> navController.navigate(it1) }
                R.id.buttonProfile ->navController.navigate(R.id.buttonProfile)


            }
            true
            }

         */
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}