//Author: Joanna Walkiewicz, zad2

package com.example.zad_2.ui.page1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zad_2.databinding.FragmentFirstBinding;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FirstFragment extends Fragment {

    GraphView graphView;
    LineGraphSeries<DataPoint> series;

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
    private FragmentFirstBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FirstViewModel homeViewModel = new ViewModelProvider(this).get(FirstViewModel.class);

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        graphView = binding.graph;
        series = new LineGraphSeries<>(getDataPoint());
        graphView.addSeries(series);
        graphView.getViewport().setScrollable(true);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
                @Override
                public String formatLabel(double value, boolean isValueX){
                    if(isValueX){
                        return sdf.format(new Date((long)value));
                    }else{
                    return super.formatLabel(value,isValueX);
        }}
        });
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public DataPoint[] getDataPoint() {
        DataPoint[] dp = new DataPoint[]{
                new DataPoint(changeLocalDateToDate(LocalDate.of( 2022 , 2 , 11 )).getTime(),25.1),
                new DataPoint(changeLocalDateToDate(LocalDate.of( 2022 , 2 , 12 )).getTime(),26),
                new DataPoint(changeLocalDateToDate(LocalDate.of( 2022 , 2 , 13 )).getTime(),26.3),
                new DataPoint(changeLocalDateToDate(LocalDate.of( 2022 , 2 , 15 )).getTime(),26.5),
                new DataPoint(changeLocalDateToDate(LocalDate.of( 2022 , 3 , 1 )).getTime(),25.5),
                new DataPoint(changeLocalDateToDate(LocalDate.of( 2022 , 3 , 20 )).getTime(),28.5)
        };
        return dp;
    }
    private Date changeLocalDateToDate(LocalDate localDate) {
        return java.util.Date.from(localDate.atStartOfDay( ZoneId.of( "Poland" )).toInstant());
    }
}