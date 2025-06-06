package com.sargis.khlopuzyan.presentation.unit_test

import android.content.Context

class ResourceComparer {
    fun isEqual(context: Context, resId: Int, string: String) : Boolean {
        return context.getString(resId) == string
    }
}