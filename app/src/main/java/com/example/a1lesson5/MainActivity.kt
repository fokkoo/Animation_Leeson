package com.example.a1lesson5

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.activity_main)

/*
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<RecyclerViewSampleFragment>(R.id.fragment_container)
            }
        }
*/


        val hideTextViewSampleTransition by lazy {
            val transitionSet = TransitionSet()
            transitionSet.duration = 50000 // duration for test, i know it should be less then 300
            transitionSet.ordering = TransitionSet.ORDERING_TOGETHER

            transitionSet.addTransition(Fade(Fade.OUT))
            transitionSet.addTransition(ChangeBounds())
            transitionSet
        }

        val showTextViewSampleTransition by lazy {

            TransitionSet().duration = 50000 // duration for test, i know it should be less then 300
            TransitionSet().ordering = TransitionSet.ORDERING_TOGETHER
            TransitionSet().addTransition(Fade(Fade.IN))
            TransitionSet().addTransition(ChangeBounds())
        }

        button_transition_animation.setOnClickListener {
            val constraintSetSampleTextViewIsVisible = linerLayout.isVisible

            val transitionSet = if (constraintSetSampleTextViewIsVisible) {
                hideTextViewSampleTransition
            } else {
                showTextViewSampleTransition
            }

            textView.setText("Hello button_transiniom_animation ")
            TransitionManager.beginDelayedTransition(linerLayout)
            linerLayout.isVisible = !linerLayout.isVisible
        }

        imageButton.setOnClickListener {
            if (editTextPersonName.text.isEmpty()) {
                textView.setText("Hello no name Cat")
            } else {
                textView.setText("Hello " + editTextPersonName.text)
            }
        }


        //отработка нажатия кнопки object_animation , класс который наследуется от value_animation

        button_object_animation_alfa.setOnClickListener {
            val animator = ObjectAnimator.ofFloat(linerLayout, View.ALPHA, 0f, 1f)
            //  val animator = ObjectAnimator.ofArgb(Color.BLACK,Color.RED) // не работает

            textView.setText("Hello button_object_animation ")
            animator.duration = 300
            animator.repeatMode = ValueAnimator.REVERSE
            animator.repeatCount = 5

            animator.start()
        }

        //отработка нажатия кнопки object_animation rotation
        button_object_animation_rotation.setOnClickListener {

            val animator = ObjectAnimator.ofFloat(linerLayout, View.ROTATION, 0f, 365f)

            textView.setText("Hello button_object_animation_rotation ")
            animator.duration = 1000
            animator.repeatMode = ValueAnimator.REVERSE
            animator.repeatCount = 2
            animator.interpolator = DecelerateInterpolator()

            animator.start()
        }


        //отработка нажатия кнопки value_animation , когда используется какое то условие
        fun createAlphaValueAnimator(): ValueAnimator {
            val animator = ValueAnimator.ofFloat(0f, 1f)

            animator.duration = 300
            animator.repeatMode = ValueAnimator.REVERSE
            animator.repeatCount = 5

            animator.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Float
                textView5.alpha = value
                textView4.alpha = value
                linerLayout.alpha = value
                editTextPersonName.alpha = value
            }
            return animator
        }

        fun createAlfaAnimator(): ValueAnimator? {
            val animator = ValueAnimator.ofFloat(0f, 1f)

            animator.duration = 1000
            animator.repeatMode = ValueAnimator.REVERSE
            animator.repeatCount = 5
            animator.interpolator = DecelerateInterpolator()

            animator.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Float
                textView5.alpha = value
                textView4.alpha = value
                linerLayout.alpha = value
                editTextPersonName.alpha = value
            }
            return animator
        }

        fun createRotationAnimator(): ValueAnimator? {
            val animator = ObjectAnimator.ofFloat(linerLayout, View.ROTATION, 0f, 365f)

            animator.duration = 1000
            animator.repeatMode = ValueAnimator.REVERSE
            animator.repeatCount = 5
            animator.startDelay = 1000

            animator.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Float
                textView5.alpha = value
                textView4.alpha = value
                linerLayout.alpha = value
                editTextPersonName.alpha = value
            }
            return animator
        }


        //playing together. the change of list chages the resault
        button_object_animation_alfa_and_rotation.setOnClickListener {
            val animatorSet = AnimatorSet()
            val animators = listOf(

                createRotationAnimator(),
                createAlfaAnimator(),

                )
            animatorSet.playTogether(animators)
            animatorSet.start()
        }


        // перемещение объектов каждого по отдельности чтобы не запутаться в двух разных вью

        var push_animation_number = true;

        button_push_animation.setOnClickListener {

            TransitionManager.beginDelayedTransition(constraint_layout_animation)

            val constraintSet = ConstraintSet()
            constraintSet.clone(constraint_layout_animation)

            val parentId = R.id.button_push_animation
            //  val dailyImageViewId = R.id.imageView2
            //   val animatorSampleTextViewId = R.id.textView

            if (push_animation_number == true) {
                textView.setText("Hello true")
                push_animation_number = false;
                constraintSet.connect(
                    R.id.imageButton,
                    ConstraintSet.BOTTOM,
                    R.id.button_push_animation,
                    ConstraintSet.BOTTOM
                )
            } else {
                textView.setText("Hello false")
                push_animation_number = true;
                constraintSet.connect(
                    R.id.imageButton,
                    ConstraintSet.BOTTOM,
                    R.id.button_transition_animation,
                    ConstraintSet.BOTTOM
                )
            }


            constraintSet.applyTo(constraint_layout_animation)
        }


        // view property animator наиболее лаконичный способ
        button_view_property_animation.setOnClickListener {
            textView.setText("Hello button_view_proprty_animation ")

            if (push_animation_number == true) {
                textView.setText("Hello true")
                push_animation_number = false;
                val animator = linerLayout.animate().alpha(0f)

                animator.duration
                animator.start()
            } else {
                textView.setText("Hello false")
                push_animation_number = true;
                val animator = linerLayout.animate().alpha(1f)
                animator.duration
                animator.start()
            }

        }

        button_ARGB_animation.setOnClickListener {
            val animator = createArgbAnimator()
            animator.start()

        }

        button_value_animation.setOnClickListener {
            textView.setText("Hello button_value_animation ")
            createAlphaValueAnimator()

        }
    }

    private fun createArgbAnimator(): Animator {
        val animator = ValueAnimator.ofArgb(Color.BLACK, Color.RED)
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = 1000

        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            textView.setTextColor(value)
        }
        return animator
    }
}