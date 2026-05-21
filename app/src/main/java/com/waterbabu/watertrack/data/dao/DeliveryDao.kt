package com.waterbabu.watertrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.waterbabu.watertrack.data.entity.Delivery
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryDao {
    @Insert
    suspend fun insert(delivery: Delivery): Long

    @Update
    suspend fun update(delivery: Delivery)

    @Delete
    suspend fun delete(delivery: Delivery)

    @Query("SELECT * FROM deliveries WHERE id = :id")
    suspend fun getDeliveryById(id: Int): Delivery?

    @Query("SELECT * FROM deliveries ORDER BY deliveryDate DESC")
    fun getAllDeliveries(): Flow<List<Delivery>>

    @Query("SELECT * FROM deliveries WHERE customerId = :customerId ORDER BY deliveryDate DESC")
    fun getDeliveriesByCustomer(customerId: Int): Flow<List<Delivery>>

    @Query("""
        SELECT * FROM deliveries 
        WHERE DATE(deliveryDate / 1000, 'unixepoch') = DATE('now') 
        ORDER BY deliveryDate DESC
    """)
    fun getTodayDeliveries(): Flow<List<Delivery>>

    @Query("""
        SELECT SUM(bottlesDelivered) FROM deliveries 
        WHERE DATE(deliveryDate / 1000, 'unixepoch') = DATE('now')
    """)
    fun getTodayBottlesCount(): Flow<Int?>

    @Query("""
        SELECT SUM(totalAmount) FROM deliveries 
        WHERE DATE(deliveryDate / 1000, 'unixepoch') = DATE('now')
    """)
    fun getTodayTotalAmount(): Flow<Double?>
}
