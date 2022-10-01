package com.example.healthify.repository

import androidx.lifecycle.LiveData
import com.example.healthify.database.WorkoutDatabaseDao
import com.example.healthify.model.Workout2

class WorkoutRepository(private val workoutDatabaseDao: WorkoutDatabaseDao) {
    val getAllData: LiveData<List<Workout2>> = workoutDatabaseDao.getAllData()

    suspend fun addWorkout2(workout: Workout2) {
        workoutDatabaseDao.insert(workout)
    }

    suspend fun updateWorkout2(workout: Workout2) {
        workoutDatabaseDao.update(workout)
    }

    suspend fun deleteWorkout2(workout: Workout2) {
        workoutDatabaseDao.delete(workout)
    }

    suspend fun deleteAllWorkout2() {
        workoutDatabaseDao.deleteAllWorkout()
    }
}
