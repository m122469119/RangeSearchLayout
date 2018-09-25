package com.mix.rangesearchlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mix.rangesearchlayout.DataInfo;
import com.mix.rangesearchlayout.RangeSearchLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RangeSearchLayout mLLSalary;

    RangeSearchLayout mLLWorkAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLLSalary = findViewById(R.id.ll_salary);

        ArrayList<DataInfo> salarys = new ArrayList<>();
        salarys.add(new DataInfo("0", "1K"));
        salarys.add(new DataInfo("2000", "2K"));
        salarys.add(new DataInfo("3000", "3K"));
        salarys.add(new DataInfo("4000", "4K"));
        salarys.add(new DataInfo("5000", "5K"));
        salarys.add(new DataInfo("6000", "6K"));
        salarys.add(new DataInfo("7000", "7K"));
        salarys.add(new DataInfo("8000", "8K"));
        salarys.add(new DataInfo("9000", "9K"));
        salarys.add(new DataInfo("10000", "10K"));
        salarys.add(new DataInfo("11000", "11K"));
        salarys.add(new DataInfo("12000", "12K"));
        salarys.add(new DataInfo("13000", "13K"));
        salarys.add(new DataInfo("14000", "14K"));
        salarys.add(new DataInfo("15000", "15K"));
        salarys.add(new DataInfo("16000", "16K"));
        salarys.add(new DataInfo("17000", "17K"));
        salarys.add(new DataInfo("18000", "18K"));
        salarys.add(new DataInfo("19000", "19K"));
        salarys.add(new DataInfo("20000", "20K"));
        salarys.add(new DataInfo("20000", "20K"));
        salarys.add(new DataInfo("30000", "30K"));
        salarys.add(new DataInfo("30000", "30K"));
        salarys.add(new DataInfo("40000", "40K"));
        salarys.add(new DataInfo("40000", "40K"));
        salarys.add(new DataInfo("50000", "50K"));
        salarys.add(new DataInfo("50000", "50K"));

        mLLSalary.setRangeDatas(salarys);
        mLLSalary.setMinMaxValue("4000", "40000");

        mLLWorkAge = findViewById(R.id.ll_work_age);


        ArrayList<DataInfo> workAges = new ArrayList<>();
        workAges.add(new DataInfo("0", "0年"));
        workAges.add(new DataInfo("1", "1年"));
        workAges.add(new DataInfo("2", "2年"));
        workAges.add(new DataInfo("3", "3年"));
        workAges.add(new DataInfo("4", "4年"));
        workAges.add(new DataInfo("5", "5年"));
        workAges.add(new DataInfo("6", "6年"));
        workAges.add(new DataInfo("7", "10年"));
        workAges.add(new DataInfo("7", "10年"));
        workAges.add(new DataInfo("8", "15年"));
        workAges.add(new DataInfo("8", "15年"));
        mLLWorkAge.setRangeDatas(workAges);
        mLLWorkAge.setMinMaxValue("1", "7");


    }
}
