package com.example.a1lesson5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.appcompat.widget.SwitchCompat

class FragmentSettings : Fragment() {


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
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        initView(view)
        return view

    }

    private fun initView(view: View) {


        val radioButtonReplace = view.findViewById<RadioButton>(R.id.radioButtonReplace)
        val radioButtonAdd = view.findViewById<RadioButton>(R.id.radioButtonAdd)

        val switchCompatUseBackstack = view.findViewById<SwitchCompat>(R.id.switchUseBackeStack)
        val switchCompatDeliteBiforeRemove = view.findViewById<SwitchCompat>(R.id.switchDeliteBiforeRemove)
        val switchCompatBackAsRemove = view.findViewById<SwitchCompat>(R.id.switchBackAsRemove)

       // radioButtonAdd.setOnCheckedChangeListener(button,is)



        radioButtonAdd.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            FragmentSettings.radioButtonAddConstant = isChecked
            // save in shared preferensec
            writeSettings()
        }
        radioButtonAdd.setChecked(radioButtonAddConstant)


        radioButtonReplace.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            FragmentSettings.radioButtonReplaceConstant = isChecked
            // save in shared preferensec
            writeSettings()
        }
        radioButtonReplace.setChecked(radioButtonReplaceConstant)


        switchCompatBackAsRemove.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            FragmentSettings.switchCompatBackAsRemoveConstant = isChecked
            // save in shared preferensec
            writeSettings()
        }
        switchCompatBackAsRemove.setChecked(switchCompatBackAsRemoveConstant)


        switchCompatDeliteBiforeRemove.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            FragmentSettings.switchCompatDeliteBiforeRemoveConstant = isChecked
            // save in shared preferensec
            writeSettings()
        }
        switchCompatDeliteBiforeRemove.setChecked(switchCompatDeliteBiforeRemoveConstant)


        switchCompatUseBackstack.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            FragmentSettings.switchCompatUseBackstackConstant = isChecked
            // save in shared preferensec
            writeSettings()
        }
        switchCompatUseBackstack.setChecked(switchCompatUseBackstackConstant)


    }


    companion object {
        //statick field for saving information from settings
        var radioButtonAddConstant = false
        var radioButtonReplaceConstant = false

        var switchCompatUseBackstackConstant = false
        var switchCompatDeliteBiforeRemoveConstant = false
        var switchCompatBackAsRemoveConstant = false


        // constants for SharedPreferences
        val SETTINGS = "Settings"

        val RADIO_BUTTON_ADD_FRAGMENT = "radioButtonAdd_FRAGMENT"
        val RADIO_BUTTON_REPLACE_FRAGMENT = "radioButtonReplace_FRAGMENT"

        val SWITCH_CompatBackAsRemove_FRAGMENT = "switchCompatBackAsRemove_FRAGMENT"
        val SWITCH_CompatDeliteBiforeRemove_FRAGMENT = "switchCompatDeliteBiforeRemove_FRAGMENT"
        val SWITCH_CompatUseBackstack_FRAGMENT = "switchCompatUseBackstack_FRAGMENT"

       @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSettings().apply {
                arguments = Bundle().apply {

                }
            }
    }


    // Shared preferenses for saving data
    private fun writeSettings() {
        requireActivity().getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(RADIO_BUTTON_ADD_FRAGMENT, FragmentSettings.radioButtonAddConstant)
            .putBoolean(RADIO_BUTTON_REPLACE_FRAGMENT, FragmentSettings.radioButtonReplaceConstant)

            .putBoolean(SWITCH_CompatBackAsRemove_FRAGMENT, FragmentSettings.switchCompatBackAsRemoveConstant)
            .putBoolean(SWITCH_CompatDeliteBiforeRemove_FRAGMENT, FragmentSettings.switchCompatDeliteBiforeRemoveConstant)
            .putBoolean(SWITCH_CompatUseBackstack_FRAGMENT, FragmentSettings.switchCompatUseBackstackConstant)
            .apply()
    }
}