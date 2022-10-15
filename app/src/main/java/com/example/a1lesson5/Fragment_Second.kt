package com.example.a1lesson5

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.*
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment__second.*


class Fragment_Second : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

      override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
      ): View? {
        // Inflate the layout for this fragment

          val view = inflater.inflate(R.layout.fragment__second, container, false)

          initView(view)

          return view
    }

    private fun initView(view: View) {


        val button_transition_animation_second_fragment = view.findViewById<Button>(R.id.button_transition_animation_second_fragment)

        val texttextViewFragment2 = view.findViewById<TextView>(R.id.textViewFragment2)

        val linerLayoutFragmentt2= view.findViewById<LinearLayout>(R.id.linerLayoutFragment2)

        texttextViewFragment2.setText("Hello")

        button_transition_animation_second_fragment?.setOnClickListener {
          //  createRotationAnimatorSecondFragment()
            createAlfaAnimator()
            
            textViewFragment2.setText("Button pressed")

            val toast = Toast.makeText(
                getActivity()?.getApplicationContext(),
                "Пора покормить кота!", Toast.LENGTH_SHORT
            )
            toast.show()

        }
    }

    fun createAlfaAnimator(): ValueAnimator? {
        val animator = ValueAnimator.ofFloat(0f, 1f)

        animator.duration = 1000
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = 50
        animator.interpolator = DecelerateInterpolator()

        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
         //   textViewFragment2.alpha = value
         //   imageViewFragment2.alpha = value
            imageViewFragment2.alpha = value
            textViewFragment2.alpha = value

         //   linerLayoutFragment2.alpha = value
        }
        return animator
    }

    fun createRotationAnimatorSecondFragment(): ValueAnimator? {
        val animator = ObjectAnimator.ofFloat(linerLayoutFragment2, View.ROTATION, 0f, 365f)

        animator.duration = 1000
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = 500
        animator.startDelay = 1000

        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            imageViewFragment2.alpha = value
            textViewFragment2.alpha = value
         //   linerLayout.alpha = value
         //   editTextPersonName.alpha = value
        }
        return animator
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_Second().apply {
                arguments = Bundle().apply {

                }
            }
    }
}