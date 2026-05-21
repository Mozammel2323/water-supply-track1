package com.waterbabu.watertrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.waterbabu.watertrack.data.entity.Payment
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Insert
    suspend fun insert(payment: Payment): Long

    @Update
    suspend fun update(payment: Payment)

    @Delete
    suspend fun delete(payment: Payment)

    @Query("SELECT * FROM payments WHERE id = :id")
    suspend fun getPaymentById(id: Int): Payment?

    @Query("SELECT * FROM payments ORDER BY paymentDate DESC")
    fun getAllPayments(): Flow<List<Payment>>

    @Query("SELECT * FROM payments WHERE customerId = :customerId ORDER BY paymentDate DESC")
    fun getPaymentsByCustomer(customerId: Int): Flow<List<Payment>>

    @Query("""
        SELECT SUM(amount) FROM payments 
        WHERE DATE(paymentDate / 1000, 'unixepoch') = DATE('now')
    """)
    fun getTodayPaymentAmount(): Flow<Double?>

    @Query("""
        SELECT SUM(amount) FROM payments 
        WHERE strftime('%Y-%m', paymentDate / 1000, 'unixepoch') = strftime('%Y-%m', 'now')
    """)
    fun getMonthlyPaymentAmount(): Flow<Double?>
}
