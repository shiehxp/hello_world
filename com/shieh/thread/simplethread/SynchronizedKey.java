package simplethread;

import java.util.logging.Level;
import java.util.logging.Logger;

/** 
* @author xiepeng
* @time 2016年12月17日 下午5:45:51 
* @description synchronized 同一时间只让一个线程执行
*/
public class SynchronizedKey {
	
	private int count=1;
	public static void main(String args[]){
		SynchronizedKey s=new SynchronizedKey();
		s.doSometing();
	}
	
	public synchronized void increment(String threadName)throws InterruptedException{
		count++;
		//Thread.sleep(1000);
		System.out.println("thread is progress" + threadName+"and count is "+ count);
	}
	
	public void doSometing(){
		Thread thread=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<10;i++){
					try {
						increment(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						Logger.getLogger(SynchronizedKey.class.getName()).log(Level.SEVERE, "haha", e);
					}
				}
			}
		});
		thread.start();
		Thread thread1=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<10;i++){
					try {
						increment(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						Logger.getLogger(SynchronizedKey.class.getName()).log(Level.SEVERE, "haha", e);
					}
				}
			}
		});
		thread1.start();
		/**
         * Join Threads: Finish until thread finishes execution, then progress
         * the code Reminder: your method is also a thread so without joining
         * threads System.out.println("Count is: " + count); will work
         * immediately. Join does not terminate Thread 2, just progress of the
         * code stops until Threads terminate.
         */
		try {
			thread.join();
			thread1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("count is "+count);
	}

}

	