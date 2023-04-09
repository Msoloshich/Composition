package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }

        }
        parseGameResult()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun parseGameResult() {
        setResultImage()
        setResultTexts()
    }

    private fun setResultImage() {
        if (gameResult.winner) {
            binding.emojiResult.setImageResource(R.drawable.ic_smile)
        } else {
            binding.emojiResult.setImageResource(R.drawable.ic_sad)
        }
    }

    private fun setResultTexts() {
        with(binding) {
            tvRequiredAnswers.text = String.format(
                getString(R.string.game_finished_fragment_required_answers_count),
                gameResult.gameSettings.minCountOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.game_finished_fragment_required_answers_percentage),
                gameResult.gameSettings.minPercentOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.game_finished_fragment_your_score),
                gameResult.countOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                getString(R.string.game_finished_fragment_your_answers_percentage),
                getPercentage()
            )
        }
    }

    private fun getPercentage(): Int {
        return gameResult.countOfRightAnswers * 100 / gameResult.countOfQuestions
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        private const val KEY_GAME_RESULT = "gameResult"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }


}