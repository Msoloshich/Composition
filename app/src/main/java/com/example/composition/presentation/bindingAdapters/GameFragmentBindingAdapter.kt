package com.example.composition.presentation.bindingAdapters

import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("answerProgressColor")
fun bindAnswerProgressColor(textView: TextView, enoughCount: Boolean) {
    textView.setTextColor(getColorByState(enoughCount, textView.context))
}

@BindingAdapter("progressBarParams")
fun bindProgressBarParams(progressBar: ProgressBar, percentOfRightAnswers: Int) {
    progressBar.setProgress(percentOfRightAnswers, true)
}

@BindingAdapter("progressBarTint")
fun bindProgressBarTint(progressBar: ProgressBar, enoughPercent: Boolean) {
    val color = getColorByState(enoughPercent, progressBar.context)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(goodState: Boolean, context: Context): Int {
    val colorIsRed = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorIsRed)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}