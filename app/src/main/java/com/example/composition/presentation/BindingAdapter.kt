package com.example.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.game_finished_fragment_required_answers_count),
        count
    )
}

@BindingAdapter("userScore")
fun bindUserScore(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.game_finished_fragment_required_answers_count),
        count
    )
    textView.text = String.format(
        textView.context.getString(R.string.game_finished_fragment_your_score),
        count
    )

}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.game_finished_fragment_required_answers_percentage),
        percentage
    )
}

@BindingAdapter("userPercentage")
fun bindUserPercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.game_finished_fragment_your_answers_percentage),
        getPercentage(gameResult)
    )
}


@BindingAdapter("emoji")
fun bindEmoji(imageView: ImageView, winner: Boolean) {
    if (winner) {
        imageView.setImageResource(R.drawable.ic_smile)
    } else {
        imageView.setImageResource(R.drawable.ic_sad)
    }
}


private fun getPercentage(gameResult: GameResult): Int =
    with(gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            countOfRightAnswers * 100 / countOfQuestions
        }
    }