package net.mzhasanah.fiveinone.exerciseproject.repository

import net.mzhasanah.fiveinone.exerciseproject.data.api.MessageAPI
import net.mzhasanah.fiveinone.exerciseproject.data.api.MessagesRequest
import net.mzhasanah.fiveinone.exerciseproject.data.api.MessagesResponse
import retrofit2.Response

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