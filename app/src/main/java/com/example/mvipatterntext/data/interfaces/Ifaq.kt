package com.example.mvitestapp.data.interfaces

import com.example.mvipatterntext.data.model.faq.FAQModel
import com.example.mvitestapp.shared.ConstantLinks
import retrofit2.http.GET

interface Ifaq {

    @GET(ConstantLinks.APP_FAQ)
    suspend fun getFAQ () : FAQModel
}