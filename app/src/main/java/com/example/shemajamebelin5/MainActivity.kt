package com.example.shemajamebelin5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.shemajamebelin5.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity()class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()
    private val fieldValues: MutableMap<Int, String> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Observer()
        ClickListeners()
    }

    private fun ClickListeners() {
        binding.btnRegister.setOnClickListener {
            val fieldsChildCount = binding.mainkt.childCount
            for (index in (0..fieldsChildCount)) {
                val field = binding.mainkt.getChildAt(index)
                if (field is MyFields) {
                    field.valuesFromMutable().forEach() {
                        fieldValues[it.key] = it.value
                    }
                }
            }
            val values = mutableListOf<String>()
            for (i in fieldValues) {
                values.add(i.value)
            }
            binding.tvFields.text = values.toString()
        }
    }

    private fun Observer() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getList().collect {
                    when (it) {
                        is ResultBuilder.Success -> {
                            binding.mainkt.removeAllViews()
                            it.list.forEach {
                                val fieldView = MyFields(this@MainActivity).apply { addFields(it) }
                                binding.mainkt.addView(fieldView)
                            }
                        }
                        is ResultBuilder.Error -> {
                            Toast.makeText(this@MainActivity, it.errorMSg, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}