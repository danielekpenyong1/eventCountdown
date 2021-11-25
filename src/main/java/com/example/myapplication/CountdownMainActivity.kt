package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class CountdownMainActivity : AppCompatActivity() {
    var Start_time = 60000L
    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false
    var time_in_milliseconds = 0L
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown_main)
        val intent = intent
        val receivedcountdowntime = intent.getLongExtra("date",1)
        val receivedcountdowntime2 = intent.getLongExtra("time",1)
        val data ="$receivedcountdowntime $receivedcountdowntime2".toLong()
        time_in_milliseconds = data
        startTimer(time_in_milliseconds)
        
        val newTimer: View = findViewById(R.id.floatingActionButton2)
        newTimer.setOnClickListener{
            addnewTimer()
        }
        val Pauser: Button = findViewById(R.id.button)
        Pauser.setOnClickListener{
            pauseTimer()
        }
    }
    private fun updateTextUI() {
        val setDate = time_in_milliseconds.toString()
        val from = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val inputdate = from.parse(setDate)
        val now = Date()
        val days:Long = (inputdate.time - now.time)/86400000
        val hours: Long = (inputdate.time - now.time)%86400000/3600000
        val minutes:Long = (inputdate.time - now.time)%86400000%3600000/60000
        val sec:Long = (inputdate.time - now.time)%86400000%3600000%60000/1000
        val outputBoard = findViewById<TextView>(R.id.textView2)
        outputBoard.text = "$days days $hours hours $minutes minutes $sec sec"
    }
    private fun startTimer(time_in_seconds:Long){
        countdown_timer = object :CountDownTimer(time_in_seconds, 1000){
            override fun onFinish() {
                resetTimer()
                val newtimeragain:View = findViewById(R.id.floatingActionButton2)
                newtimeragain.visibility = View.VISIBLE
                val pausingEnds:Button = findViewById(R.id.button)
                pausingEnds.visibility=View.INVISIBLE
            }

            override fun onTick(p0:Long){
                time_in_milliseconds = p0
                updateTextUI()
            }
        }

        countdown_timer.start()
        isRunning=true
        val newtimer:View = findViewById(R.id.floatingActionButton2)
        newtimer.visibility = View.INVISIBLE}
    private fun resetTimer() {
        time_in_milliseconds = Start_time
        updateTextUI()
        val reset_timer = findViewById<Button>(R.id.button2)
        reset_timer.visibility = View.INVISIBLE
    }
    private fun addnewTimer() {
        setContentView(R.layout.countdown_dialog)
        startTimer(time_in_milliseconds)
    }
    private fun pauseTimer() {
        val pausebutton:Button = findViewById(R.id.button)
        pausebutton.text = "Start"
        countdown_timer.cancel()
    }
    private fun convertLongtoTime(data:Long):String {
        val date = Date(data)
        val format = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return format.format(date)
    }
}