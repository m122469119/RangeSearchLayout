package com.mix.searcher.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mix.searcher.DataInfo;
import com.mix.searcher.SeamlessRangeSeekBarLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeamlessRangeSeekBarLayout seamlessRangeSeekBarLayout = findViewById(R.id.ll_salary);
        ArrayList<DataInfo> salarys = new ArrayList<>();
        salarys.add(new DataInfo("0", "0K"));
        salarys.add(new DataInfo("1", "1K"));
        salarys.add(new DataInfo("2", "2K"));
        salarys.add(new DataInfo("3", "3K"));
        salarys.add(new DataInfo("4", "4K"));
        salarys.add(new DataInfo("5", "5K"));
        salarys.add(new DataInfo("6", "6K"));
        salarys.add(new DataInfo("7", "7K"));
        salarys.add(new DataInfo("8", "8K"));
        salarys.add(new DataInfo("9", "9K"));
        salarys.add(new DataInfo("10", "10K"));
        salarys.add(new DataInfo("11", "11K"));
        salarys.add(new DataInfo("12", "12K"));
        salarys.add(new DataInfo("13", "13K"));
        salarys.add(new DataInfo("14", "14K"));
        salarys.add(new DataInfo("15", "15K"));
        salarys.add(new DataInfo("16", "16K"));
        salarys.add(new DataInfo("17", "17K"));
        salarys.add(new DataInfo("18", "18K"));
        salarys.add(new DataInfo("19", "19K"));
        salarys.add(new DataInfo("20", "20K"));
        salarys.add(new DataInfo("30", "30K"));
        salarys.add(new DataInfo("40", "40K"));
        salarys.add(new DataInfo("50", "50K"));
        seamlessRangeSeekBarLayout.setRangeDatas(salarys);

        seamlessRangeSeekBarLayout.setMinMaxValue("10", "30");


    }
}
