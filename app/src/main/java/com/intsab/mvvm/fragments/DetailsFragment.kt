package com.intsab.mvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.intsab.mvvm.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    var _name: String? = ""
    var _email: String? = ""
    var _description: String? = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _name = arguments?.getString("name")
        _email = arguments?.getString("email")
        _description = arguments?.getString("details")

        return FragmentDetailsBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            name = _name
            email = _email
            description = _description // Attach your view model here
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}