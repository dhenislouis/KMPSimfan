package org.kmp.simfan

import android.content.Context
import android.content.Intent
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.exceptions.ClearCredentialException
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlin.getValue

import android.app.Activity
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import androidx.credentials.ClearCredentialStateRequest
import android.util.Log
import org.kmp.simfan.model.FirebaseTokenRequest


class GoogleAuthClient(
    private val context: Context
) {
    private val webClientId = "550674141157-43n96tcgtr23eumbfkbetsjd6a3ukbcj.apps.googleusercontent.com"
    private val credManager by lazy { CredentialManager.create(context) }

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(webClientId)
        .requestEmail()
        .build()

    private val googleSignInClient = GoogleSignIn.getClient(context, gso)

    fun getSignInIntent(): Intent = googleSignInClient.signInIntent

    fun getSignedInUserFromIntent(data: Intent?): FirebaseTokenRequest? {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        return try {
            val account = task.getResult(ApiException::class.java)!!
            FirebaseTokenRequest(
                token = account.idToken!!,
                name = account.displayName!!
            )
        } catch (e: ApiException) {
            e.printStackTrace()
            null
        }
    }

    fun logOut(onComplete: () -> Unit) {
        googleSignInClient.signOut().addOnCompleteListener {
            onComplete()
        }
    }

    suspend fun signIn(activity: Activity): FirebaseTokenRequest? {
        // Opsi Sign in with Google (via Credential Manager)
        val googleOption = GetGoogleIdOption.Builder()
            .setServerClientId(webClientId)
            // true = hanya akun yg sudah pernah authorize; kalau NoCredentialException,
            // coba ulangi dengan false agar user bisa pilih semua akun.
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleOption)
            .build()

        return try {
            val result = credManager.getCredential(
                context = activity, // penting: activity context
                request = request
            )

            when (val credential = result.credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        try {
                            val google = GoogleIdTokenCredential.createFrom(credential.data)
                            FirebaseTokenRequest(
                                token = google.idToken,
                                name = google.displayName!!
                            )
                        } catch (e: GoogleIdTokenParsingException) {
                            Log.e("GoogleAuthClient", "Invalid Google ID token", e)
                            null
                        }
                    } else null
                }
                else -> null
            }
        } catch (e: GetCredentialException) {
            // Tip: jika e adalah NoCredentialException, ulangi flow dengan
            // setFilterByAuthorizedAccounts(false) agar user bisa pilih akun manapun.
            Log.e("GoogleAuthClient", "getCredential failed: ${e.message}", e)
            null
        }
    }

    suspend fun signOut() {
        try {
            credManager.clearCredentialState(ClearCredentialStateRequest())
        } catch (e: ClearCredentialException) {
            Log.w("GoogleAuthClient", "clearCredentialState failed: ${e.message}", e)
        }
    }
}