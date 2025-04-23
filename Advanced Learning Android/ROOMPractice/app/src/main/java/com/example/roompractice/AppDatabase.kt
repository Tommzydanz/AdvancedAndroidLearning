package com.example.roompractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    //abstract function that defines and abstract method: i.e userdao with no body
    //this method is use to obtain an instance of the userdao interface which provides method to interact with the database table associated with the user entity

    abstract fun userDao(): UserDao

    // then it defines a companion object and uses createDatabase method to initialize the database
    companion object {
        lateinit var database: Database

        private fun createContext(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "user_db"
            ).build()
    }
}