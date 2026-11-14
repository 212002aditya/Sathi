package com.sathiai

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.content.Intent
import java.util.Locale

class VoiceAssistant(
    private val context: Context
) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech =
        TextToSpeech(context, this)

    private var speechRecognizer: SpeechRecognizer? = null

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale("hi", "IN")
        }
    }

    fun speak(message: String) {
        tts.speak(
            message,
            TextToSpeech.QUEUE_FLUSH,
            null,
            "SATHI_AI"
        )
    }

    fun startListening(
        onResult: (String) -> Unit
    ) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            speak("Speech recognition available nahi hai")
            return
        }

        speechRecognizer =
            SpeechRecognizer.createSpeechRecognizer(context)

        val intent = Intent(
            RecognizerIntent.ACTION_RECOGNIZE_SPEECH
        )

        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            "hi-IN"
        )

        speechRecognizer?.setRecognitionListener(
            object : android.speech.RecognitionListener {

                override fun onResults(results: android.os.Bundle?) {
                    val text = results
                        ?.getStringArrayList(
                            SpeechRecognizer.RESULTS_RECOGNITION
                        )?.firstOrNull()

                    if (text != null) {
                        onResult(text)
                    }
                }

                override fun onReadyForSpeech(params: android.os.Bundle?) {}
                override fun onBeginningOfSpeech() {}
                override fun onRmsChanged(rmsdB: Float) {}
                override fun onBufferReceived(buffer: ByteArray?) {}
                override fun onEndOfSpeech() {}
                override fun onError(error: Int) {}
                override fun onPartialResults(partialResults: android.os.Bundle?) {}
                override fun onEvent(eventType: Int, params: android.os.Bundle?) {}
            }
        )

        speechRecognizer?.startListening(intent)
    }

    fun stop() {
        speechRecognizer?.destroy()
        tts.stop()
        tts.shutdown()
    }
}
