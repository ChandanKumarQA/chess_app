package com.chessmaster.play.ui.components.coach

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.util.Log
import java.util.Locale
import kotlin.random.Random

/**
 * Text-to-Speech manager configured for a friendly female chess coach voice.
 * Speaks tactical hints and encouraging corrections on wrong moves.
 */
class CoachVoiceManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isInitialized = false

    private val wrongMovePhrases = listOf(
        "That's not the right move. Try again!",
        "Oops! Look carefully for a better move.",
        "Not quite. Try another piece!",
        "Watch out! There's a stronger move on the board.",
        "Almost, but you can do better. Try once more!",
        "Careful! That move allows a counterattack."
    )

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                tts?.setLanguage(Locale.getDefault())
            }

            // Configure natural female voice
            setupFemaleVoice()
            isInitialized = true
            Log.d("CoachVoiceManager", "Coach Voice TTS successfully initialized.")
        } else {
            Log.e("CoachVoiceManager", "Failed to initialize TTS.")
        }
    }

    private fun setupFemaleVoice() {
        try {
            // Check available voices for female voice keywords
            val voices = tts?.voices
            val femaleVoice = voices?.firstOrNull { voice ->
                val name = voice.name.lowercase()
                !voice.isNetworkConnectionRequired &&
                        (name.contains("female") || name.contains("en-us-x-sfg") || name.contains("en-us-x-tpd"))
            }

            if (femaleVoice != null) {
                tts?.voice = femaleVoice
            }
        } catch (e: Exception) {
            Log.w("CoachVoiceManager", "Could not query specific voice, falling back to pitch adjustment: ${e.message}")
        }

        // Setting a higher pitch gives a distinctly clear, warm female voice tone
        tts?.setPitch(1.28f)
        tts?.setSpeechRate(0.96f)
    }

    fun speakHint(hintText: String) {
        speak(hintText)
    }

    fun speakWrongMove() {
        val phrase = wrongMovePhrases[Random.nextInt(wrongMovePhrases.size)]
        speak(phrase)
    }

    fun speak(text: String) {
        if (!isInitialized) return
        try {
            tts?.stop()
            val params = Bundle().apply {
                putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.0f)
            }
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, params, "COACH_UTTERANCE_${System.currentTimeMillis()}")
        } catch (e: Exception) {
            Log.e("CoachVoiceManager", "Error speaking: ${e.message}")
        }
    }

    fun stop() {
        try {
            tts?.stop()
        } catch (e: Exception) {
            Log.e("CoachVoiceManager", "Error stopping TTS: ${e.message}")
        }
    }

    fun shutdown() {
        try {
            tts?.stop()
            tts?.shutdown()
            tts = null
            isInitialized = false
        } catch (e: Exception) {
            Log.e("CoachVoiceManager", "Error shutting down TTS: ${e.message}")
        }
    }
}
