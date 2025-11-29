package com.example.watchsync.data.repository

import com.example.watchsync.data.model.CreditBalance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object CreditRepository {
    private val _creditBalance = MutableStateFlow(
        CreditBalance(
            totalCredits = 50, // Başlangıç kredisi
            dailyCreditsRemaining = 10, // Günlük hak
            lastDailyReset = System.currentTimeMillis()
        )
    )
    val creditBalance: StateFlow<CreditBalance> = _creditBalance.asStateFlow()

    fun useCredit(amount: Int = 1): Boolean {
        val current = _creditBalance.value
        if (current.dailyCreditsRemaining >= amount) {
            _creditBalance.value = current.copy(
                dailyCreditsRemaining = current.dailyCreditsRemaining - amount
            )
            return true
        }
        return false
    }

    fun addCredits(amount: Int) {
        val current = _creditBalance.value
        _creditBalance.value = current.copy(
            totalCredits = current.totalCredits + amount,
            dailyCreditsRemaining = current.dailyCreditsRemaining + amount
        )
    }

    fun resetDailyCredits() {
        val current = _creditBalance.value
        val now = System.currentTimeMillis()
        val oneDayInMillis = 24 * 60 * 60 * 1000L
        
        if (now - current.lastDailyReset >= oneDayInMillis) {
            _creditBalance.value = current.copy(
                dailyCreditsRemaining = 10, // Günlük hak
                lastDailyReset = now
            )
        }
    }

    fun getCurrentBalance(): CreditBalance {
        resetDailyCredits() // Her çağrıda kontrol et
        return _creditBalance.value
    }
}

