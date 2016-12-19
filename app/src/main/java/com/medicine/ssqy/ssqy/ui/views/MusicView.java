package com.medicine.ssqy.ssqy.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/20.
 */
public class MusicView extends View {
    private int mColorLine;
    private Paint mPaint = new Paint();
    private int mHeightView = 0, mWidthView = 0;
    private int mHoriSpacing = 16;
    private int mLineCount = 0;
    private int mLineWidth = 4;
    private float mViewCenterY,mViewHalfHeight;
    public List<MyPointer> mMyPointers=new ArrayList<>();
    private Random random=new Random();
    private  boolean mIsPlaying=false;
    public MusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mColorLine = 0xff732322;
        mPaint.setColor(mColorLine);
        mPaint.setStrokeWidth(mLineWidth);
    }
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mMyPointers.size()==0) {
            initDatas();
        }
   
    }
    
    private void initDatas() {
        mHeightView = this.getHeight();
        mWidthView = this.getWidth();
        mLineCount = (int) (mWidthView * 1.0f / (mLineWidth + mHoriSpacing));
        mViewCenterY=mHeightView/2.0f;
        mViewHalfHeight=mHeightView/2.0f;
        for (int i = 0; i < mLineCount; i++) {
            MyPointer myPointer=new MyPointer();
            myPointer.x=(mLineWidth + mHoriSpacing)*i;
            myPointer.heightBegin= (random.nextInt(mHeightView/4*3)+mHeightView*1.0f/4)/2.0f;
            myPointer.isGrow=i%2==0;
            mMyPointers.add(myPointer);
        }
       
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLines(canvas);
    }
    
    private void drawLines(Canvas canvas) {
        for (int i = 0; i < mMyPointers.size(); i++) {
            MyPointer myPointer=mMyPointers.get(i);
            canvas.drawLine(myPointer.x,mViewCenterY-myPointer.heightBegin,myPointer.x,mViewCenterY+myPointer.heightBegin,mPaint);
            if (myPointer.isGrow){
                
                myPointer.heightBegin+=3;
                if (myPointer.heightBegin>=mViewHalfHeight){
                    myPointer.heightBegin=mViewHalfHeight;
                    myPointer.isGrow=false;
                }
            }else {
                myPointer.heightBegin-=3;
                if (myPointer.heightBegin<=mViewHalfHeight/3){
                    myPointer.heightBegin=mViewHalfHeight/3;
                    myPointer.isGrow=true;
                }
            }
        }
        if (mIsPlaying){
            invalidate();
        }
       
    }
    
    public void play(){
        mIsPlaying=true;
        invalidate();
    }
    
    public void stop(){
        mIsPlaying=false;
    }
    
    private class MyPointer {
        public int x;
        public float heightBegin;
        public boolean isGrow=false;
    }
}
