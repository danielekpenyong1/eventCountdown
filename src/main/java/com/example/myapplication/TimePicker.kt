package com.example.myapplication

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import java.text.SimpleDateFormat
import java.util.*

class TimePicker : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    val cal = Calendar.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
         val hour = cal.get(Calendar.HOUR)
        val minute = cal.get(Calendar.MINUTE)
        val dialog = TimePickerDialog(activity as Context,this,hour,minute,true)
        return dialog
    }


    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        val mypattern = "HH:mm"
        val selectedTime = SimpleDateFormat("$mypattern").format(cal.time)
        val game = "25-04-2000"
        val selectedTimebundle = Bundle()
        selectedTimebundle.putString("Data", selectedTime)
        setFragmentResult("TRANSFER_KEY", selectedTimebundle)
    }


}
