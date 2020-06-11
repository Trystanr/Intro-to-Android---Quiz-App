package com.trystan.demoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.trystan.demoapp.databinding.FragmentGameLostBinding
import com.trystan.demoapp.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)

        arguments?.get("amount")
        binding.textviewScore.text = arguments?.getString("amount")

        binding.buttonHome.setOnClickListener { view: View ->
            view.findNavController().popBackStack()
        }

        return binding.root
    }
}