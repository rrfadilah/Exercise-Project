package com.rizky.exercise_project.repository

import com.rizky.exercise_project.data.api.MessageAPI
import com.rizky.exercise_project.data.api.MessagesRequest
import com.rizky.exercise_project.data.api.MessagesResponse
import retrofit2.Response

/**
 * com.rizky.exercise_project.repository
 *
 * Created by Rizky Fadilah on 18/05/22.
 * https://github.com/rizkyfadilah
 *
 */

class MessageRepository(
    private val messageAPI: MessageAPI
) {
    suspend fun getMessage(): Response<List<MessagesResponse>> {
        return messageAPI.getMessages()
    }

    suspend fun postMessage(message: MessagesRequest): Response<MessagesResponse> {
        return messageAPI.postMessages(message)
    }

    suspend fun deleteMessage(id: String): Response<Unit> {
        return messageAPI.deleteMessages(id)
    }

    suspend fun updateMessage(id: String, message: MessagesRequest): Response<MessagesResponse> {
        return messageAPI.updateMessages(id, message)
    }
}
