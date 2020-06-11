package com.trystan.demoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trystan.demoapp.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), OnCategoryItemClickListener {

    private val viewModel: GameViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)

        // Create binder for objects in fragment
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

//        var categoryList = viewModel.generateDummyList(10)
        binding.categoryList.adapter = CategoryAdapter(viewModel.categories, this)
        binding.categoryList.layoutManager = LinearLayoutManager(context)
        binding.categoryList.setHasFixedSize(true)

        return binding.root
    }

    override fun onCategoryClick(category: CategoryItem, position: Int, view: View) {
        // Toast.makeText(context, "This category is: ${category.text} with ID: ${category.id}", Toast.LENGTH_SHORT).show()
        viewModel.setupGame(category.id)
        view.findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
    }
}
