package com.example.mvipatterntext.ui.faq

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvipatterntext.data.statuesValue.faq.FAQIntent
import com.example.mvipatterntext.data.statuesValue.faq.FAQStatus
import com.example.mvipatterntext.domain.FAQRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class FAQViewModel : ViewModel() {

    val faqIntent = Channel<FAQIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<FAQStatus>(FAQStatus.Idle)

    val state: StateFlow<FAQStatus> get() = _state

    init {
        makeFAQ()
    }

    private fun makeFAQ() {
        viewModelScope.launch {
            faqIntent.consumeAsFlow().collect {
                when (it) {
                    is FAQIntent.GetFAQ -> faqRepo()
                }
            }
        }
    }

    private fun faqRepo() {
        viewModelScope.launch {
            _state.value = try {
                FAQStatus.GetFAQ(FAQRepository().getFAQ())
            } catch (e: Exception) {
                FAQStatus.Error(e.message)
            }
        }
    }
}