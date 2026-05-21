package com.waterbabu.watertrack.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "deliveries",
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Delivery(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val customerId: Int,
    val bottlesDelivered: Int,
    val pricePerBottle: Double,
    val totalAmount: Double,
    val paymentStatus: PaymentStatus = PaymentStatus.DUE,
    val deliveryDate: Long = System.currentTimeMillis(),
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

enum class PaymentStatus {
    PAID,
    PARTIAL,
    DUE
}
