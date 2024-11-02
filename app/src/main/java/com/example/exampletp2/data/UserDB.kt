package com.example.exampletp2.data

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.exampletp2.TpDesaMobileApp
import com.example.exampletp2.data.model.User
import com.example.exampletp2.utils.Constants

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        private var INSTANCE: UserDB? = null

        // Singleton
        fun getDatabase(): UserDB {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    TpDesaMobileApp.instance.applicationContext,
                    UserDB::class.java,
                    Constants.DB_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}