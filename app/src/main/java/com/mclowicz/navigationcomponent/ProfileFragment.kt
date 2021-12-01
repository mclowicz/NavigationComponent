package com.mclowicz.navigationcomponent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mclowicz.navigationcomponent.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding : FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)
    }
}