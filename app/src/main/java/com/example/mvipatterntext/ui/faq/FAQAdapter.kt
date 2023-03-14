package com.example.mvipatterntext.ui.faq

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mvipatterntext.data.model.faq.FAQSubModel
import com.example.mvipatterntext.databinding.FaqViewBinding

class FAQAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var model = ArrayList<FAQSubModel>()
    private lateinit var context: Context

    fun setData(model: ArrayList<FAQSubModel>, context: Context) {
        this.model = model
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FaqViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model[position])
    }

    override fun getItemCount(): Int {
        return model.size
    }
}

class ViewHolder(val binding: FaqViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: FAQSubModel) {
        binding.questionEn.text = data.question_en
        binding.answerEn.text = data.answer_en
        binding.questionAr.text = data.question_ar
        binding.answerAr.text = data.answer_en

    }
}