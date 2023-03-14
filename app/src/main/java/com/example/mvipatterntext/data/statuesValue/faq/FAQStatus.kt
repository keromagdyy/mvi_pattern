package com.example.mvipatterntext.data.statuesValue.faq

import com.example.mvipatterntext.data.model.faq.FAQModel


sealed class FAQStatus {

    object Idle : FAQStatus()
    object Loading : FAQStatus()
    data class GetFAQ(val data: FAQModel) : FAQStatus()
    data class Error(val error: String?) : FAQStatus()

}