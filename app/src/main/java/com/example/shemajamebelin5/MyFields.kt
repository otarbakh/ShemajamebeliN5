package com.example.shemajamebelin5

import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.widget.EditText
import androidx.cardview.widget.CardView
import com.example.shemajamebelin5.databinding.SinglefieldBinding




class MyFields(context: Context) : CardView(context) {
    private var binding = SinglefieldBinding.inflate(LayoutInflater.from(context), this, true)

    private var fields: List<Datas> = emptyList()
    private val fieldMTBL: MutableMap<Int, String> = mutableMapOf()


    fun addFields(fieldList: List<Datas>) {
        fields = fieldList
        for (field in fieldList) {
            val customField = EditText(context)
            customField.layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            customField.hint = field.hint
            customField.inputType =
                if (field.keyboard == "text") InputType.TYPE_CLASS_TEXT else InputType.TYPE_CLASS_NUMBER
            binding.singlechuvak
//            binding.singlechuvak.addView(customField)
        }
    }
    fun valuesFromMutable(): MutableMap<Int, String> {
        val fieldsChildCount = binding.singlechuvak.childCount
        for (index in (0..fieldsChildCount)) {
            val field = binding.singlechuvak.getChildAt(index)
            if (field is EditText) {
                val fieldModel = fields.find { model ->
                    model.hint == field.hint
                }
                fieldModel?.let {
                    fieldMTBL[it.fieldId] = field.text.toString()
                }
            }
        }
        return fieldMTBL
    }

}