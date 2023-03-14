package com.example.mvipatterntext.ui.faq

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvipatterntext.R
import com.example.mvipatterntext.data.statuesValue.faq.FAQIntent
import com.example.mvipatterntext.data.statuesValue.faq.FAQStatus
import com.example.mvipatterntext.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: FAQViewModel by viewModels()
    private val adapter = FAQAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        getData()

    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.faqIntent.send(FAQIntent.GetFAQ)
            viewModel.state.collect {
                when (it) {

                    is FAQStatus.Idle -> { Log.d(TAG, "idle: ${it}")}

                    is FAQStatus.GetFAQ -> {
                        Log.d(TAG, "getData: ${it.data.data.FAQ[0].question_en}")
                        adapter.setData(it.data.data.FAQ, baseContext)
                        val layoutManager =
                            LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
                        binding.recyclerView.layoutManager = layoutManager
                        binding.recyclerView.adapter = adapter

                    }

                    is FAQStatus.Error -> {
                        Log.d(TAG, " Error: $it")
                    }
                    else -> {}
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity_TAG"
    }
}