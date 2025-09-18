package org.kmp.simfan

import android.content.Context
import android.content.Intent
import androidx.credentials.exceptions.ClearCredentialException
import com.google.android.gms.common.api.ApiException
import kotlin.getValue

import android.app.Activity
import androidx.credentials.ClearCredentialStateRequest
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

import androidx.lifecycle.lifecycleScope
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import org.kmp.simfan.model.FirebaseTokenRequest
import kotlinx.coroutines.tasks.await


class GoogleAuthClient(
    private val context: Context
) {
    private val webClientId = "550674141157-43n96tcgtr23eumbfkbetsjd6a3ukbcj.apps.googleusercontent.com"
    private val credentialManager = CredentialManager.create(context)

    suspend fun getGoogleIdTokenInternal(): GoogleIdTokenCredential {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(webClientId)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val result = credentialManager.getCredential(context, request)
        val cred = result.credential as CustomCredential
        return GoogleIdTokenCredential.createFrom(cred.data)
    }

    suspend fun signInAndGetFirebaseIdToken(): FirebaseTokenRequest {
        val googleCred = getGoogleIdTokenInternal()

        val firebaseIdToken = signInFirebaseAndGetIdToken(googleCred.idToken)

        return FirebaseTokenRequest(
            token = firebaseIdToken,
            name  = googleCred.displayName.orEmpty()
        )
    }

    suspend fun signOut() {
        try {
            FirebaseAuth.getInstance().signOut()
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
        } catch (e: ClearCredentialException) {

            throw e
        }
    }

    suspend fun signInFirebaseAndGetIdToken(googleIdToken: String): String {
        val credential = GoogleAuthProvider.getCredential(googleIdToken, null)

        val authResult = FirebaseAuth.getInstance()
            .signInWithCredential(credential)
            .await()

        val user = authResult.user ?: error("Firebase user null setelah sign-in")

        return user.getIdToken(true).await().token ?: error("Firebase ID token null")
    }

    private fun parseCredential(credential: Credential): FirebaseTokenRequest {
        if (credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            try {
                val googleCred = GoogleIdTokenCredential.createFrom(credential.data)
                return FirebaseTokenRequest(
                    token = googleCred.idToken,
                    name = googleCred.displayName ?: ""
                )
            } catch (e: GoogleIdTokenParsingException) {
                throw e
            }
        }
        error("Unsupported credential type: ${credential::class.java.simpleName}")
    }
}