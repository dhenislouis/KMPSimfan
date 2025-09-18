package org.kmp.simfan.network.api

import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import org.kmp.simfan.network.entity.CameraResponse
import org.kmp.simfan.network.lib.NetworkClient
import simfan.composeapp.generated.resources.Res

interface CameraApi {
    suspend fun extractInfo(request: String): Result<CameraResponse>

    companion object Companion {
        private const val MODEL_VERSION = "gemini-2.5-flash"
        private const val URL = "v1beta/models/$MODEL_VERSION:generateContent"

        private suspend fun apiKey(): String {
            return Res.readBytes("files/goog_api_key.txt").decodeToString()
        }

        fun create() = object : CameraApi {
            override suspend fun extractInfo(request: String): Result<CameraResponse> {
                return runCatching {
                    NetworkClient
                        .create
                        .post("${NetworkClient.SimfanBaseUrl}$URL") {
                            setBody(request)
                            header("Content-Type", "application/json")
                            header("X-goog-api-key", apiKey())
                        }
                        .body()
                }
            }
        }
    }
}

