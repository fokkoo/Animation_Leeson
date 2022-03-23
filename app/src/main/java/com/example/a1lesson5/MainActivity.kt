package com.example.a1lesson5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        button_transiniom_animation.setOnClickListener{
            textView.setText("Hello button_transiniom_animation ")
            TransitionManager.beginDelayedTransition(linerLayout)
            linerLayout.isVisible = !linerLayout.isVisible
        }

        imageButton.setOnClickListener {
            if (editTextPersonName.text.isEmpty()){
                textView.setText("Hello no name Cat")
            }
            else{
                textView.setText("Hello "+editTextPersonName.text)

                TransitionManager.beginDelayedTransition(linerLayout)
                linerLayout.isVisible = !linerLayout.isVisible
        }


        }
    }
}