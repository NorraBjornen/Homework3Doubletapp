package com.example.domain.usecases

import com.example.domain.entities.HabitType
import com.example.domain.entities.ToastContentType

class ToastContentUseCase {
    fun getToastContentType(restTimes: Int, habitType: Int): ToastContentType {
        return if(habitType == HabitType.GOOD.value) {
            when(restTimes) {
                0 -> ToastContentType.GOOD_EXCEED
                1 -> ToastContentType.GOOD_ENOUGH
                else -> ToastContentType.GOOD_NOT_EXCEED
            }
        } else {
            when(restTimes) {
                0 -> ToastContentType.BAD_EXCEED
                1 -> ToastContentType.BAD_ENOUGH
                else -> ToastContentType.BAD_NOT_EXCEED
            }
        }
    }
}