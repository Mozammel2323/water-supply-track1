package com.waterbabu.watertrack.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.waterbabu.watertrack.data.dao.CustomerDao
import com.waterbabu.watertrack.data.dao.DeliveryDao
import com.waterbabu.watertrack.data.dao.PaymentDao
import com.waterbabu.watertrack.data.entity.Customer
import com.waterbabu.watertrack.data.entity.Delivery
import com.waterbabu.watertrack.data.entity.Payment

@Database(
    entities = [Customer::class, Delivery::class, Payment::class],
    version = 1,
    exportSchema = false
)
abstract class WaterTrackDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun deliveryDao(): DeliveryDao
    abstract fun paymentDao(): PaymentDao

    companion object {
        @Volatile
        private var INSTANCE: WaterTrackDatabase? = null

        fun getDatabase(context: Context): WaterTrackDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WaterTrackDatabase::class.java,
                    "water_track_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
