package com.sugar.cosmetics.view.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sugar.cosmetics.R
import com.sugar.cosmetics.activity.BaseActivity
import com.sugar.cosmetics.data.model.FetchDetail
import com.sugar.cosmetics.data.remote.api.Resource
import com.sugar.cosmetics.databinding.ActivityHomeBinding
import com.sugar.cosmetics.view.adapter.GridRVAdapter
import com.sugar.cosmetics.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class HomeScreenActivity : BaseActivity<ActivityHomeBinding>(){

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, HomeScreenActivity::class.java)
        }
    }

    private val homeViewModel: HomeViewModel by viewModels()
    @SuppressLint("NewApi")
    var displayDateFormat =  SimpleDateFormat("dd/MMM/YYYY", Locale.getDefault())
    var displayDate :String? = null
    var arrayListKeys = ArrayList<String>()
    var arrayListValues = ArrayList<Double>()

    override val layoutId: Int
        get() = R.layout.activity_home

    override fun init() {
        setListener()
    }

    private fun setListener() {

        date_picker.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // on below line we are getting
                // the instance of our calendar.
                val c: Calendar = Calendar.getInstance()

                // on below line we are getting
                // our day, month and year.
                val year: Int = c.get(Calendar.YEAR)
                val month: Int = c.get(Calendar.MONTH)
                val day: Int = c.get(Calendar.DAY_OF_MONTH)

                // on below line we are creating a variable for date picker dialog.
                val datePickerDialog = DatePickerDialog( // on below line we are passing context.
                    this@HomeScreenActivity,
                    { view, year, monthOfYear, dayOfMonth -> // on below line we are setting date to our text view.
                        var dateAndTime =  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        var inputFormat =  SimpleDateFormat("yyyy-M-d", Locale.getDefault())
                        val date: Date = inputFormat.parse(year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString())
                        var outputDate = dateAndTime.format(date)
                        println(outputDate)
                        setObserver(outputDate)
                        displayDate = displayDateFormat.format(dateAndTime.parse(outputDate))
                        date_picker.setText(dayOfMonth.toString()+ "-" + (monthOfYear + 1) + "-" + year.toString())
                    },  // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day
                )
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show()
            }
        })

    }

    private fun setObserver(date : String) {
        var url = "https://openexchangerates.org/api/historical/${date}.json?app_id=b8170f2960a546378a5ceb06a7bb6f59"
        homeViewModel.fetchData(url)

        homeViewModel.homeLiveData.observe(this, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {
                    initLoader()
                }
                Resource.Status.SUCCESS -> {
                    finishLoader()
                    it.data.let {
                        var iter =it?.rates?.entries?.iterator()

                        while (iter!!.hasNext()){
                            val key = iter.next()
                            arrayListKeys.add(key.key)
                            arrayListValues.add(key.value)
                        }
                        tv_title.text = resources.getString(R.string.date,displayDate)
                        /*arrayListKeys.addAll(it?.rates?.keys)
                        arrayListValues.addAll(it.rates)*/

                        val courseAdapter = GridRVAdapter(arrayListKeys, arrayListValues, this@HomeScreenActivity)
                        gridView.adapter = courseAdapter
                    }

                }
                Resource.Status.ERROR -> {
                    finishLoader()
                }
            }
        })

    }




}