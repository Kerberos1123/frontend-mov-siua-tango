package com.una.FrontEndTango.View.Unit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.una.FrontEndTango.R

/**
 * A simple [Fragment] subclass.
 * Use the [ActiveUnits.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActiveUnits : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_active_units, container, false)
    }

}