package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var binding : ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarHistory)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"
        }
        binding?.toolbarHistory?.setNavigationOnClickListener {
            onBackPressed()
        }
        val dao = (application as HistoryApp).db.historyDao()
        getAllCompletedDates(dao)

    }
    private fun getAllCompletedDates(historyDao : HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect{
                if (it.isNotEmpty()){
                    binding?.tvExerciseCompleted?.visibility = View.VISIBLE
                    binding?.rvExerciseHistory?.visibility = View.VISIBLE
                    binding?.tvNoRecordsFound?.visibility = View.GONE

                    val dates = ArrayList<String> ()
                    for (i in it){
                        dates.add(i.date)
                    }

                    val adapter = HistoryAdapter(dates)
                    binding?.rvExerciseHistory?.adapter = adapter
                    binding?.rvExerciseHistory?.layoutManager =  LinearLayoutManager(this@HistoryActivity)

                }else{
                    binding?.tvExerciseCompleted?.visibility = View.GONE
                    binding?.rvExerciseHistory?.visibility = View.GONE
                    binding?.tvNoRecordsFound?.visibility = View.VISIBLE
                }
            }
        }
    }
}