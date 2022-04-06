package id.anantyan.exerciseproject.repository

import id.anantyan.exerciseproject.data.MessagesDao
import id.anantyan.exerciseproject.model.Messages

class MessagesRepository(private val usersDao: MessagesDao) {

    fun select() = usersDao.select()

    fun selectById(id: Int) = usersDao.selectById(id)

    suspend fun insert(item: Messages) = usersDao.insert(item)

    suspend fun update(item: Messages) = usersDao.update(item)

    suspend fun delete(item: Messages) = usersDao.delete(item)

    suspend fun deleteAll() = usersDao.deleteAll()
}