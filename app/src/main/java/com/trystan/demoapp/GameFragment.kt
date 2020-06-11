package com.trystan.demoapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.trystan.demoapp.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        viewModel.score.observe( viewLifecycleOwner, Observer { newScore ->
            binding.score.text = "Score: $newScore"

        })

        viewModel.currentCategory.observe( viewLifecycleOwner, Observer { newCategory ->
            binding.categoryName.text = newCategory
        })

        viewModel.question.observe( viewLifecycleOwner, Observer { newQuestion ->
            binding.question.text = newQuestion.question

            Glide.with(this).load(Uri.parse("file:///android_asset/${newQuestion.image}")).into(binding.imageView2)

            var rGroup = binding.radioGroup
            rGroup.removeAllViews()
            for ((index, answer) in newQuestion.answers.withIndex()) {
                var newRdbtn = createRadioButton(answer, index)
                rGroup.addView(newRdbtn)
            }
        })

        binding.submitButton.setOnClickListener { view: View ->
            val id = binding.radioGroup.checkedRadioButtonId

            // Make sure that a radio button has been selected
            if (id != -1) {

                viewModel.checkQuestion(id)

                if (viewModel.currentQuestion.value!! < viewModel.amountOfQuestion.value!!.minus(1)) {
                    viewModel.updateQuestion(viewModel.currentQuestion.value ?: 0)
                } else {

                    if (viewModel.score.value!! == viewModel.amountOfQuestion.value!!) {
                        view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                    } else {
                        val bundle = bundleOf("amount" to 66)
                        view.findNavController().navigate(R.id.action_gameFragment_to_gameLostFragment, bundle)
                    }
                }
            }

        }

        return binding.root
    }

    private fun createRadioButton(answer: String, id: Int):RadioButton {
        val colorstatelist = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_enabled)
            ),
            intArrayOf(Color.GRAY, Color.WHITE)
        )

        val rdb = RadioButton(context)

        rdb.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        rdb.text = answer
        rdb.id = id
        rdb.setTextColor(resources.getColor(R.color.colorText))
        rdb.buttonTintList = colorstatelist

        return rdb
    }

}
