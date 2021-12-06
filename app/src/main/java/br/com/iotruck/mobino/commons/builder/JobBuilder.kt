package br.com.iotruck.mobino.commons.builder

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.core.content.ContextCompat.getSystemService

object JobBuilder {

    fun <T> buildJobsServices(packageContext: Context, service: Class<T>): Int {

        val jobScheduler = getSystemService(packageContext, service) as JobScheduler

        val jobInfo = JobInfo.Builder(10, ComponentName(packageContext, service))

        val job = jobInfo.setRequiresCharging(false)
            .setMinimumLatency(1)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setOverrideDeadline(3 * 60 * 1000).build()

        return jobScheduler.schedule(job)

    }
}