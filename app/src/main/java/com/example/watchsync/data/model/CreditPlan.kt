package com.example.watchsync.data.model

data class CreditPlan(
    val id: String,
    val name: String,
    val credits: Int,
    val bonusCredits: Int = 0,
    val price: Double,
    val currency: String = "TL",
    val isPopular: Boolean = false
)
