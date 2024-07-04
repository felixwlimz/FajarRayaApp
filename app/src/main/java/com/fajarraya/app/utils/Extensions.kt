package com.fajarraya.app.utils

import android.icu.text.NumberFormat
import java.util.Locale

object Extensions {

    fun <T : Number> toRupiah(number : T) : String {
        val localId = Locale("id", "id")
        val currency = NumberFormat.getCurrencyInstance(localId)
        return currency.format(number)
    }

    fun useRegex(pattern : String) : Regex = Regex(pattern)



}