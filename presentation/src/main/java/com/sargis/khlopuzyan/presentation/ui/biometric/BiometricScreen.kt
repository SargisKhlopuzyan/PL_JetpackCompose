package com.sargis.khlopuzyan.presentation.ui.biometric

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.biometric.BiometricManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BiometricScreen() {

    val activity = LocalActivity.current as FragmentActivity

    val promptManager by lazy {
        BiometricPromptManager(activity)
    }

    var biometricResult =
        promptManager.promptResults.collectAsStateWithLifecycle(initialValue = null)

    val enrollLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        println("Activity result: $it")
    }

    LaunchedEffect(biometricResult) {
        if (biometricResult is BiometricPromptManager.BiometricResult.AuthenticatorNotSet)
            if (Build.VERSION.SDK_INT >= 30 /*Build.VERSION_CODES.R*/) {
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BiometricManager.Authenticators.BIOMETRIC_STRONG or
                                BiometricManager.Authenticators.DEVICE_CREDENTIAL
                    )
                }
                enrollLauncher.launch(enrollIntent)
            }
    }

    Scaffold { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    promptManager.showBiometricPrompt(
                        title = "Sample prompt",
                        description = "Sample prompt description",
                    )
                }
            ) {
                Text(text = "Authenticate")
            }

            if (biometricResult.value != null) {
                biometricResult.value?.let { result ->
                    Text(
                        text = when (result) {
                            is BiometricPromptManager.BiometricResult.AuthenticatorError -> result.error
                            BiometricPromptManager.BiometricResult.AuthenticatorFailed -> "Authentication failed"
                            BiometricPromptManager.BiometricResult.AuthenticatorNotSet -> "Authentication not set"
                            BiometricPromptManager.BiometricResult.AuthenticatorSuccess -> "Authentication success"
                            BiometricPromptManager.BiometricResult.FeatureUnavailable -> "Feature unavailable"
                            BiometricPromptManager.BiometricResult.HardwareUnavailable -> "Hardware unavailable"
                        }
                    )
                }
            }
        }
    }
}