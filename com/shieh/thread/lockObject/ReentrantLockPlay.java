package lockObject;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @author xiepeng
* @time 2016年12月19日 下午5:46:25 
* @description 
*/
public class ReentrantLockPlay {
	public static void main(String args[]) throws InterruptedException{
		final Runner runner=new Runner();
		Thread t1=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					runner.firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		});
		
		Thread t2=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					runner.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
		runner.finished();
	}

}
	class Runner{
		private int count=0;
		private Lock lock=new ReentrantLock();
		private Condition condition=lock.newCondition();
		
		private void increment(){
			for(int i=0;i<10000;i++){
				count++;
			}
		}
		public void firstThread() throws InterruptedException{
			lock.lock();
			System.out.println("waiting...");
			condition.await();
			System.out.println("woken up");
			try{
				increment();
			}finally{
				lock.unlock();
			}
		}
		@SuppressWarnings("resource")
		public void secondThread() throws InterruptedException{
			Thread.sleep(1000);
			lock.lock();
			System.out.println("press the return key.");
			new Scanner(System.in).nextLine();
			System.out.println("got return key");
			condition.signal();
			try{
				increment();
			}finally{
				lock.unlock();
			}
		}
		public void finished(){
			System.out.println("count is: "+count);
		}
		
	}