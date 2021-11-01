package com.example.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.myapplication.databinding.CountdownDialogBinding

class AlertDialog : DialogFragment(R.layout.countdown_dialog) {
    private var _binding: CountdownDialogBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CountdownDialogBinding.bind(view)

        binding.apply {
            eventDate.setOnClickListener {
                val datepickerFragment = datepicker()
                val supportFragmentManager = requireActivity().supportFragmentManager
                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { requestKey, bundle ->
                    if (requestKey == "REQUEST_KEY") {
                        val dateOutput = bundle.getString("SELECTED_DATE")
                        eventDate.setText(dateOutput).toString()
                    }
                }
                datepickerFragment.show(supportFragmentManager, "datepickerfragment")

                eventTime.setOnClickListener {
                    val timepickerFragment = TimePicker()
                    val supportfragmentManager = requireActivity().supportFragmentManager
                    supportfragmentManager.setFragmentResultListener(
                        "TRANSFER_KEY",
                        viewLifecycleOwner
                    ) { requestKey, bundle ->
                        if (requestKey == "TRANSFER_KEY") {
                            val Timeoutput = bundle.getString("Data")
                            eventTime.setText(Timeoutput)
                        }
                    }

                    timepickerFragment.show(supportfragmentManager, "timePickerFragment")
                }
                button4.setOnClickListener {
                    val intent = Intent()
                    val date = eventDate.text.toString()
                    val time = eventTime.text.toString()
                    intent.putExtra("date",date.toLong() )
                    intent.putExtra("time", time.toLong())
                    startActivity(intent)
                }

            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




