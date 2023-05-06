//Author: Joanna Walkiewcz, s20161
package com.example.zad4.ui.page1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zad4.databinding.FragmentFirstBinding
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class FirstFragment : Fragment() {
    var graphView: GraphView? = null
    var series: LineGraphSeries<DataPoint>? = null
    var sdf = SimpleDateFormat("dd.MM")
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeViewModel = ViewModelProvider(this).get(FirstViewModel::class.java)

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        val root: View = binding.getRoot()
        graphView = binding.graph!!
        series = LineGraphSeries(dataPoint)
        graphView!!.addSeries(series)
        graphView!!.getViewport().setScrollable(true)
        graphView!!.getGridLabelRenderer().setLabelFormatter(object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                return if (isValueX) {
                    sdf.format(Date(value.toLong()))
                } else {
                    super.formatLabel(value, isValueX)
                }
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    val dataPoint: Array<DataPoint>
        get() = arrayOf<DataPoint>(
            DataPoint(changeLocalDateToDate(LocalDate.of(2022, 2, 11)), 25.1),
            DataPoint(changeLocalDateToDate(LocalDate.of(2022, 2, 12)), 26.0),
            DataPoint(changeLocalDateToDate(LocalDate.of(2022, 2, 13)), 26.3),
            DataPoint(changeLocalDateToDate(LocalDate.of(2022, 2, 15)), 26.5),
            DataPoint(changeLocalDateToDate(LocalDate.of(2022, 3, 1)), 25.5),
            DataPoint(changeLocalDateToDate(LocalDate.of(2022, 3, 20)), 28.5)
        )

    private fun changeLocalDateToDate(localDate: LocalDate): Date {
        return Date.from(localDate.atStartOfDay(ZoneId.of("Poland")).toInstant())
    }
}