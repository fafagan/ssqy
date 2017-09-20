package com.medicine.ssqy.ssqy.task.common.controller;


import com.medicine.ssqy.ssqy.task.common.model.Task;

public class TaskMsg {
	
    public Task mTask;
	public UpdatePart mUpdatePart;
    public boolean exists;
    
    
	private TaskMsg next;

    private static final Object sPoolSync = new Object();
    private static TaskMsg sPool;
    private static int sPoolSize = 0;

    private static final int MAX_POOL_SIZE = 20;

    private TaskMsg(){
    }
    public static TaskMsg obtain() {
        synchronized (sPoolSync) {
            if (sPoolSize!=0||sPool != null) {
            	TaskMsg m = sPool;
                sPool = m.next;
                m.next = null;
                sPoolSize--;
                return m;
            }
        }
        return new TaskMsg();
    }
    
    public void recycle() {
        mTask=null;
        mUpdatePart=UpdatePart.SIZE;
        exists=false;
        System.gc();
        synchronized (sPoolSync) {
            if (sPoolSize < MAX_POOL_SIZE) {
                this.next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }
}
