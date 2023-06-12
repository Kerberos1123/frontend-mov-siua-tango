package com.una.FrontEndTango.View.Guarda

//IMPORTS VIEJOS
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.una.FrontEndTango.R
import com.una.FrontEndTango.Adapter.RecyclerAdapterRequests
import com.una.FrontEndTango.Model.Request
import com.una.FrontEndTango.ViewModel.RequestViewModel
import com.una.FrontEndTango.ViewModel.RequestViewModelFactory

//IMPORTS NUEVOS
import com.una.FrontEndTango.databinding.FragmentRequestsGuardaBinding
import com.una.FrontEndTango.Adapter.RequestAdapter
import com.una.FrontEndTango.ViewModel.StateRequest

class RequestsGuarda : Fragment() {

    //-------------     Comentado para binding y mockapi
    /*
    //variables de referencia
    private lateinit var adapter : RecyclerAdapterRequests
    private lateinit var recyclerView: RecyclerView

    private lateinit var requestArrayList: ArrayList<Request>

    //Arrays para titulos y descripciones
    lateinit var titulo : Array<String>
    lateinit var descripcion :Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requests_guarda, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //se inicializa data
        dataInitialize()

        //seteamos parametros del recycle view y su layout manager
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.reciclerView)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = RecyclerAdapterRequests(requestArrayList)
        recyclerView.adapter = adapter


        // Agregar el click listener
        adapter.setOnItemClickListener(object: RecyclerAdapterRequests.onItemClickListener{
            override fun onItemClick(position: Int) { // Implementar logica al hacer click en seccion de recycler
                findNavController().navigate(R.id.action_requestsGuarda_to_requestDetails)
            }
        })
        // --- ---

    }
    private fun dataInitialize(){ //inicializacion de datos


        requestArrayList = arrayListOf<Request>()    //se crea arraylist que almacena el contenido de las request

        titulo = arrayOf( //se inicializan datos de los titulos

            getString(R.string.T1)
        )
        descripcion = arrayOf( //se inicializan datos de los descripcion

            getString(R.string.D1)
        )

        for ( i in titulo.indices){

            //creamos objeto request

            val request = Request(titulo[i],descripcion[i])

            //se añade a la lista
            requestArrayList.add(request)
        }
    }
*/

    private var _binding: FragmentRequestsGuardaBinding? = null
    private val binding get() = _binding!!

    private val requestViewModel: RequestViewModel by activityViewModels{
        RequestViewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adapter = RequestAdapter()

        _binding = FragmentRequestsGuardaBinding.inflate(inflater,container,false)

        binding.reciclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        binding.reciclerView.layoutManager = layoutManager

        //traemos todas las requests del webservice
        requestViewModel.findAllRequest()
        requestViewModel.state.observe(viewLifecycleOwner) { state ->

            when (state) {
                StateRequest.Loading -> {
                    // TODO: If you need do something in loading
                }
                is StateRequest.Error -> {
                    // TODO: If you need do something in error
                }
                is StateRequest.SuccessList -> {
                    state.requestList?.let { adapter.setRequestList(it) }
                }
                else -> {
                    // TODO: Not state loaded
                }
            }

        }

        return binding.root

    }
}