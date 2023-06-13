package com.una.FrontEndTango.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.una.FrontEndTango.R
import com.una.FrontEndTango.ViewModel.RequestViewModel
import com.una.FrontEndTango.ViewModel.RequestViewModelFactory
import com.una.FrontEndTango.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {

    private var user_type = 0 // 0: ADMIN - 1:GUARDA - 2:PROFE - 3:ESTUDIANTE - 4:DEBUG
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

    val requestViewModel by viewModels<RequestViewModel> ()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // With View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)


        navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Seleccionar el fragment inicial y navegar
        val graphInflater = navHostFragment.navController.navInflater
        navGraph = graphInflater.inflate(R.navigation.nav_graph) // Grafo de navegacion

        fragment_directions[user_type]?.let { navGraph.setStartDestination(it) } // Seleccionar el fragment inicial segun el tipo de usuario
        navController.graph = navGraph // Reiniciar el fragment inicial

        val bottomNavigationView = binding.bottomNavigationView


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {

                //R.id.buttonBack ->navController.navigate(R.id.buttonHome)
                R.id.buttonHome -> fragment_directions[user_type]?.let { it1 -> navController.navigate(it1) }
                R.id.buttonProfile ->navController.navigate(R.id.buttonProfile)
            }
            true
            }

        setContentView(binding.root)
    }

}