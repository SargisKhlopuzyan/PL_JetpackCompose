package com.sargis.khlopuzyan.presentation.ui.biometric

import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

//https://www.youtube.com/watch?v=_dCRQ9wta-I&ab_channel=PhilippLackner
class BiometricPromptManager(
    private val context: FragmentActivity,
) {

    private val resultChannel = Channel<BiometricResult>()
    val promptResults = resultChannel.receiveAsFlow()

    fun showBiometricPrompt(
        title: String,
        description: String,
    ) {
        val manager = BiometricManager.from(context)
        val authenticators = if (Build.VERSION.SDK_INT >= 30)
            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
        else
            BIOMETRIC_STRONG

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setAllowedAuthenticators(authenticators)
            .setConfirmationRequired(false)

        if (Build.VERSION.SDK_INT < 30) {
            promptInfo.setNegativeButtonText("Cancel")
        }

        when (manager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                resultChannel.trySend(BiometricResult.HardwareUnavailable)
//                return
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                resultChannel.trySend(BiometricResult.AuthenticatorNotSet)
                return
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                resultChannel.trySend(BiometricResult.FeatureUnavailable)
                return
            }

            else -> Unit

//            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
//                resultChannel.trySend(BiometricResult.)
//            }
//
//            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
//                resultChannel.trySend(BiometricResult.)
//            }
//
//            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
//                resultChannel.trySend(BiometricResult.)
//            }
//
//            BiometricManager.BIOMETRIC_SUCCESS -> {
//                resultChannel.trySend(BiometricResult.AuthenticatorSuccess)
//            }
        }

        val prompt = BiometricPrompt(
            context,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence,
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    resultChannel.trySend(BiometricResult.AuthenticatorError(errString.toString()))
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    resultChannel.trySend(BiometricResult.AuthenticatorSuccess)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    resultChannel.trySend(BiometricResult.AuthenticatorFailed)
                }
            }
        )
        prompt.authenticate(promptInfo.build())
    }

    sealed interface BiometricResult {
        data object HardwareUnavailable : BiometricResult
        data object FeatureUnavailable : BiometricResult
        data class AuthenticatorError(val error: String) : BiometricResult
        data object AuthenticatorFailed : BiometricResult
        data object AuthenticatorSuccess : BiometricResult
        data object AuthenticatorNotSet : BiometricResult
    }
}