package com.una.FrontEndTango.Repository

import com.una.FrontEndTango.Model.TaskRequest
import  com.una.FrontEndTango.Service.TaskService

class TaskRepository constructor(
    private val taskService: TaskService
){
    suspend fun getAllTask() = taskService.getAllTasks()

    suspend fun getTaskById(id : Long) = taskService.getTaskById(id)

    suspend fun deleteTaskById(id : Long) = taskService.deleteTaskById(id)

    suspend fun createTask(taskRequest: TaskRequest) = taskService.createTask(taskRequest)

    suspend fun updateTask(taskRequest: TaskRequest) = taskService.updateTask(taskRequest)
}