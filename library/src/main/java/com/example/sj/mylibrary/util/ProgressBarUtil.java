package com.example.sj.mylibrary.util;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class ProgressBarUtil extends ProgressBar {
	private Timer timer;
	private OnTimeOutListener timeOutListener;
	private int timeOutTime = -1;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (timeOutListener != null && getVisibility() == View.VISIBLE) {
				setVisibility(View.GONE);
				timeOutListener.progressTimeOut(ProgressBarUtil.this);
			}

		}
	};

	public void SetTimeOutTime(int time) {
		this.timeOutTime = time;
		initTimer();
	}

	public void initTimer() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0);
			}
		}, timeOutTime);
	}

	public void setOnTimeOutListener(OnTimeOutListener listener) {
		timeOutListener = listener;
	}

	public interface OnTimeOutListener {
		public void progressTimeOut(ProgressBar bar);
	}

	public ProgressBarUtil(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ProgressBarUtil(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ProgressBarUtil(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
}
