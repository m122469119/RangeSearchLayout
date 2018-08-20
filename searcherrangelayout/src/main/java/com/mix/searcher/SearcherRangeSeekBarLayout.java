package com.mix.searcher;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Des:       SeamlessRangeSeekBarLayout
 * Create by: m122469119
 * On:        2018\08\07 15:53
 * Email:     122469119@qq.com
 */
public class SearcherRangeSeekBarLayout extends FrameLayout {

    private SearcherRangeSeekBarView mSeamlessRangeSeekBarView;
    private TextView mTvOptionUtil;
    private TextView mTvOptionName;
    private String mOptionUnit;
    private String mOptionName;

    public SearcherRangeSeekBarLayout(Context context) {
        this(context, null);
    }

    public SearcherRangeSeekBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        View view = View.inflate(context, R.layout.searcher_range_seek_bar_layout, this);

        mTvOptionName = view.findViewById(R.id.tv_option_name);

        mTvOptionUtil = view.findViewById(R.id.tv_option_util);

        mSeamlessRangeSeekBarView = view.findViewById(R.id.seamless_range_seekbar_view);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SearcherRangeSeekBarLayout);

        mOptionName = array.getString(R.styleable.SearcherRangeSeekBarLayout_srs_option_name);
        mTvOptionName.setText(mOptionName);

        mOptionUnit = array.getString(R.styleable.SearcherRangeSeekBarLayout_srs_option_unit);

        mSeamlessRangeSeekBarView.setRangeProgressListener(new SearcherRangeSeekBarView.SalaryProgressListener() {
            @Override
            public void salaryProgress(String salaryLeft, String salaryRight) {
                if (mRangeDatas.get(0).getName().equals(salaryLeft) && mRangeDatas.get(mRangeDatas.size() - 1).getName().equals(salaryRight)) {
                    mTvOptionUtil.setText("（不限）");
                } else {
                    mTvOptionUtil.setText(String.format("（%s - %s）", salaryLeft, salaryRight));

                }
            }
        });

        array.recycle();


    }

    ArrayList<DataInfo> mRangeDatas;

    public void setRangeDatas(ArrayList<DataInfo> rangeDatas) {
        mRangeDatas = rangeDatas;
        mSeamlessRangeSeekBarView.setRangeDatas(rangeDatas);
    }


    public void setMinMaxValue(String start, String end) {
        for (int i = 0; i < mRangeDatas.size(); i++) {
            if (mRangeDatas.get(i).getId().equals(start)) {
                mSeamlessRangeSeekBarView.setMinValue(i);
            }

            if (mRangeDatas.get(i).getId().equals(end)) {
                mSeamlessRangeSeekBarView.setMaxValue(i);
            }
        }

        mSeamlessRangeSeekBarView.setFirst(true);

        mSeamlessRangeSeekBarView.postInvalidate();

    }

}
