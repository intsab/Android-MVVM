package com.intsab.mvvm.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.intsab.mvvm.Utils.AppUtils


class ShowPushNotificationTask(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        AppUtils.showNotification(
            context,
            inputData.getStringArray("title")?.firstOrNull(),
            inputData.getStringArray("desc")?.firstOrNull()
        )
        return Result.success()
    }


}