package com.example.workmangernew

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

import android.util.Log
import androidx.work.Data

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class MyWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {


    companion object{
        const val KEY_DATA = "key_data"
    }

    override fun doWork(): Result {

        try {
            for (i in 0..500000) {
                Log.i("TAG", "doWork: $i")
            }

            val time=  SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())

            val outputData = Data.Builder()
                .putString(KEY_DATA,currentDate)
                .build()

            return Result.success(outputData);
        } catch (e: Exception) {
            return Result.failure()
        }

    }


}