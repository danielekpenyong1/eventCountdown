package com.example.myapplication

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*

class datepicker:DialogFragment(), DatePickerDialog.OnDateSetListener {
    private val calendar = Calendar.getInstance()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(),this,year,month,day)
    }
    override fun onDateSet(view: DatePicker?,year: Int, month: Int, dayOfMonth:Int ){
        calendar.set(Calendar.YEAR,year)
        calendar.set(Calendar.MONTH,month)
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
        val myPattern = "dd-MM-yyyy"
        val selectedDate = SimpleDateFormat(myPattern,Locale.ENGLISH).format(calendar.time)
        val selecteddateBundle = Bundle()
        selecteddateBundle.putString("SELECTED_DATE",selectedDate)

        setFragmentResult("REQUEST_KEY", selecteddateBundle)


            }
}