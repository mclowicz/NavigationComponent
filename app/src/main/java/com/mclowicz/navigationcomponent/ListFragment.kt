package com.mclowicz.navigationcomponent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mclowicz.navigationcomponent.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)
    }
}