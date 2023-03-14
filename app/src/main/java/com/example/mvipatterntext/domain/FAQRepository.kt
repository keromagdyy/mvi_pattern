package com.example.mvipatterntext.domain

import com.akhnaton.foodvisits.shared.RetrofitClient
import com.example.mvitestapp.data.interfaces.Ifaq

class FAQRepository {
    private val retrofit = RetrofitClient.getInstance(Ifaq::class.java)

    suspend fun getFAQ() = retrofit.getFAQ()
}