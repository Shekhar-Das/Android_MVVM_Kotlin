package com.das.bd.android_mvvm_kotlin.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.das.bd.android_mvvm_kotlin.data.db.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var instanse: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instanse ?: synchronized(LOCK) {
            instanse ?: buildDatabase(context).also {
                instanse = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyDatabaseDB"
        ).build()
    }
}