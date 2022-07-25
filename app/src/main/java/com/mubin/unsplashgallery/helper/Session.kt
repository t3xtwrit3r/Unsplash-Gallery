package com.mubin.unsplashgallery.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.NonNull
import androidx.core.content.edit

object Session {

    private val Pref_Name = "com.mubin.unsplashgallery.app.session"

    private lateinit var pref: SharedPreferences

    private val Is_Write = "isWrite"
    private val Is_Read = "isRead"
    private val Is_First_Visit = "isFirstVisit"

    fun init(@NonNull context: Context) {
        pref = context.getSharedPreferences(Pref_Name, Context.MODE_PRIVATE)
    }

    fun clearSession() {
        pref.edit {
            clear()
        }
    }

    var isWrite: Boolean
        get() {
            return pref.getBoolean(Is_Write, false)
        }
        set(value) {
            pref.edit {
                putBoolean(Is_Write, value)
            }
        }

    var isRead: Boolean
        get() {
            return pref.getBoolean(Is_Read, false)
        }
        set(value) {
            pref.edit {
                putBoolean(Is_Read, value)
            }
        }

    var isFirstVisit: Boolean
        get() {
            return pref.getBoolean(Is_First_Visit, true)
        }
        set(value) {
            pref.edit {
                putBoolean(Is_First_Visit, value)
            }
        }
}