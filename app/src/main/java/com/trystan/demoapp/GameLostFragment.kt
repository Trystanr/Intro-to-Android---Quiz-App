package com.trystan.demoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.trystan.demoapp.databinding.FragmentGameBinding
import com.trystan.demoapp.databinding.FragmentGameLostBinding

/**
 * A simple [Fragment] subclass.
 * Use the [GameLostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameLostFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Bind the layout for this fragment
        val binding: FragmentGameLostBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_lost, container, false)

        arguments?.get("amount")
        binding.textviewScore.text = arguments?.getString("amount")

        binding.buttonHome.setOnClickListener { view: View ->
            view.findNavController().popBackStack()
        }

        return binding.root


    }
}