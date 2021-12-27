package com.example.workmangernew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest

import androidx.work.WorkManager
import com.example.workmangernew.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_main)

        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        dataBinding.buttonEnqueue.setOnClickListener {
            setOneTimeWorkRequest()
        }

    }

    private fun setOneTimeWorkRequest() {
        val workManager = WorkManager.getInstance(applicationContext)
        val constraint = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val uploadWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setConstraints(constraint)
            .build()

        workManager.enqueue(uploadWorkRequest)
        workManager.getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(this, Observer {
                dataBinding.tvTv.text= it.state.name
                if (it.state.isFinished){
                    val data = it.outputData
                    val message =data.getString(MyWorker.KEY_DATA)

                    Toast.makeText(this," $message",Toast.LENGTH_SHORT).show()

                }

            })
    }
}