package com.mix.searcher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Des:       SeamlessRangeSeekBarView
 * Create by: m122469119
 * On:        2018/8/7 11:22
 * Email:     122469119@qq.com
 */
public class SearcherRangeSeekBarView extends RelativeLayout {
    private LinearLayout mSlideLeft;
    private LinearLayout mSlideRight;

    private TextView mTvSalaryLower;
    private TextView mTvSalaryUpper;

    private View mViewProgress;

    private ViewDragHelper mViewDragHelper;

    private float mUnitLong;

    private int mCurrentPositionLeft;
    private int mCurrentPotionRight;

    private String mCurrentSalaryLeft;
    private String mCurrentSalaryRight;

    private boolean isFirst = true;

    private int mLeftLower;
    private int mLeftUpper;
    private int mTopLower;
    private int mTopUpper;

    private Paint mPaint;
    //    private Path mPath;
    //    private Path dstPath;
    //
    //    private PathMeasure mPathMeasure;

    //   private int mPathLeft;
    // private int mPathRight;

    private SalaryProgressListener salaryProgressListener;

    private List<String> mDatas = new ArrayList<>();


    public SearcherRangeSeekBarView(Context context) {
        this(context, null);
    }

    public SearcherRangeSeekBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearcherRangeSeekBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //        mPath = new Path();
        //        dstPath = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.progress_select));
        mPaint.setStrokeWidth(dpTpPx(3f));
        mPaint.setStyle(Paint.Style.FILL);

        initViewDrag();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mSlideLeft = findViewById(R.id.slide_left);
        mSlideRight = findViewById(R.id.slide_right);
        mTvSalaryLower = findViewById(R.id.tv_salary_left);
        mTvSalaryUpper = findViewById(R.id.tv_salary_right);
        mViewProgress = findViewById(R.id.view_option_progress);
    }


    private void initViewDrag() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mSlideLeft || child == mSlideRight;
            }

            /**
             * 对移动的边界进行控制
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int leftBound;
                int rightBound;

                if (child == mSlideLeft) {
                    leftBound = 0;
                    rightBound = mCurrentPotionRight;
                } else {
                    leftBound = mCurrentPositionLeft;
                    rightBound = mViewProgress.getWidth();
                }

                int newLeft = Math.min(Math.max(left, leftBound), rightBound);
                return newLeft;
            }

            /**
             * 设置上下不能滑动
             */
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return 0;
            }

            /**
             * 返回拖拽的范围，不对拖拽进行真正的限制，仅仅决定了动画执行速度
             * 需要子view中有点击事件时添加
             */
            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }


            @Override
            public int getViewVerticalDragRange(View child) {
                return super.getViewVerticalDragRange(child);
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);

                if (capturedChild == mSlideLeft) {
                    bringChildToFront(mSlideLeft);
                }

                if (capturedChild == mSlideRight) {
                    bringChildToFront(mSlideRight);
                }
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                if (changedView == mSlideLeft) {

                    //此处其实其实为向下取整,例如 10/3 = 3.33333 10/3.33333 =3;
                    int valuesLeft = (int) (mSlideLeft.getLeft() / mUnitLong);

                    mCurrentSalaryLeft = mRangeDatas.get(valuesLeft).getName();

                    mLeftLower = mSlideLeft.getLeft();

                    mTvSalaryLower.setText(mCurrentSalaryLeft);
                }

                if (changedView == mSlideRight) {

                    //此处其实为向下取整,例如 10/3 = 3.33333 10/3.33333 =3.0000003;

                    int valuesRight = (int) (mSlideRight.getLeft() / mUnitLong);

                    mCurrentSalaryRight = mRangeDatas.get(valuesRight).getName();

                    mLeftUpper = mSlideRight.getLeft();

                    mTvSalaryUpper.setText(mCurrentSalaryRight);
                }

                postInvalidate();
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);

                if (releasedChild == mSlideLeft) {
                    mCurrentPositionLeft = mSlideLeft.getLeft();
                }

                if (releasedChild == mSlideRight) {
                    mCurrentPotionRight = mSlideRight.getLeft();
                }

            }

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);

                switch (state) {
                    //不拖拽状态
                    case ViewDragHelper.STATE_IDLE:
                        mTvSalaryLower.setBackgroundResource(R.drawable.searcher_btn_pop_unselect);
                        mTvSalaryUpper.setBackgroundResource(R.drawable.searcher_btn_pop_unselect);
                        break;
                    //拖拽中
                    case ViewDragHelper.STATE_DRAGGING:
                        if (mViewDragHelper.getCapturedView() == mSlideLeft) {
                            mTvSalaryLower.setBackgroundResource(R.drawable.searcher_btn_pop_select);
                        } else {
                            mTvSalaryUpper.setBackgroundResource(R.drawable.searcher_btn_pop_select);
                        }
                        break;
                    //view设置中
                    case ViewDragHelper.STATE_SETTLING:
                        break;
                }
            }
        });
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (mRangeDatas != null) {
            mUnitLong = ((float) mViewProgress.getMeasuredWidth()) / (mRangeDatas.size() - 1);
        }

    }


    int lastX;
    int lastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();
        int dealtX = 0;
        int dealtY = 0;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 保证子View能够接收到Action_move事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                dealtX += Math.abs(x - lastX);
                dealtY += Math.abs(y - lastY);
                // 这里是够拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截
                if (dealtX >= dealtY) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否该拦截当前事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    /**
     * 处理事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }


    private int mMinValue;
    private int mMaxValue;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (isFirst) {
            mLeftLower = mSlideLeft.getLeft();
            mTopLower = mSlideLeft.getTop();
            mLeftUpper = mSlideRight.getLeft();
            mTopUpper = mSlideRight.getTop();

            if (mMinValue != 0) {

                //向上取整,防止精度丢失
                mLeftLower = (int) Math.ceil(mMinValue * mUnitLong);

                mCurrentPositionLeft = (int) Math.ceil(mMinValue * mUnitLong);

            } else {
                mCurrentPositionLeft = mSlideLeft.getLeft();
            }

            if (mMaxValue != 0) {

                //向上取整,防止精度丢失
                mLeftUpper = (int) Math.ceil(mMaxValue * mUnitLong);

                mCurrentPotionRight = (int) Math.ceil(mMaxValue * mUnitLong);

            } else {
                mCurrentPotionRight = mSlideRight.getLeft();
            }

            isFirst = false;
        }

        //设置view的位置
        mSlideLeft.layout(mLeftLower, mTopLower, mLeftLower + mSlideLeft.getMeasuredWidth(), mTopLower + mSlideLeft.getMeasuredHeight());
        mSlideRight.layout(mLeftUpper, mTopUpper, mLeftUpper + mSlideRight.getMeasuredWidth(), mTopUpper + mSlideRight.getMeasuredHeight());

        if (salaryProgressListener != null) {
            salaryProgressListener.salaryProgress(mCurrentSalaryLeft, mCurrentSalaryRight);
        }

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        // mPath.reset();
        //        dstPath.reset();
        //        mPath.moveTo(mViewProgress.getLeft(), mViewProgress.getTop() + ((float) mViewProgress.getMeasuredHeight()) / 2);
        //        mPath.lineTo(mViewProgress.getRight(), mViewProgress.getTop() + ((float) mViewProgress.getMeasuredHeight()) / 2);
        //
        //        mPathMeasure = new PathMeasure(mPath, false);
        //        mPathMeasure.getSegment(mSlideLeft.getLeft(), mSlideRight.getLeft(), dstPath, true);
        //
        //        canvas.drawPath(dstPath, mPaint);

        canvas.drawLine(
                mSlideLeft.getLeft() + mSlideLeft.getWidth() / 2,
                mViewProgress.getTop() + ((float) mViewProgress.getMeasuredHeight()) / 2,
                mSlideRight.getLeft() + mSlideRight.getWidth() / 2,
                mViewProgress.getTop() + ((float) mViewProgress.getMeasuredHeight()) / 2, mPaint)
        ;

    }


    public List<String> getDatas() {
        return mDatas;
    }

    public void setDatas(List<String> datas) {
        mDatas = datas;
    }

    public void setViewProgress(View viewProgress) {
        mViewProgress = viewProgress;
    }

    public View getViewProgress() {
        return mViewProgress;
    }


    public int dpTpPx(float value) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, dm) + 0.5);
    }

    /**
     * 设置当钱薪资范围的监听
     */
    public void setRangeProgressListener(SalaryProgressListener salaryProgressListener) {
        this.salaryProgressListener = salaryProgressListener;
    }

    public interface SalaryProgressListener {
        void salaryProgress(String salaryLeft, String salaryRight);
    }


    private ArrayList<DataInfo> mRangeDatas;

    public void setRangeDatas(ArrayList<DataInfo> rangeDatas) {
        mCurrentSalaryLeft = rangeDatas.get(0).getName();
        mCurrentSalaryRight = rangeDatas.get(rangeDatas.size() - 1).getName();

        mTvSalaryLower.setText(String.format("%s", mCurrentSalaryLeft));
        mTvSalaryUpper.setText(String.format("%s", mCurrentSalaryRight));

        mRangeDatas = rangeDatas;
    }


    public void setMinValue(int minValue) {
        mMinValue = minValue;

        mCurrentSalaryLeft = mRangeDatas.get(minValue).getName();

        mTvSalaryLower.setText(String.format("%s", mCurrentSalaryLeft));

    }


    public void setMaxValue(int maxValue) {
        mMaxValue = maxValue;

        mCurrentSalaryRight = mRangeDatas.get(maxValue).getName();

        mTvSalaryUpper.setText(String.format("%s", mCurrentSalaryRight));

    }


    public void setFirst(boolean first) {
        isFirst = true;
    }
}
