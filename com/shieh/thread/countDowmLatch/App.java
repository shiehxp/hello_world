package countDowmLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
* @author xiepeng
* @time 2016年12月19日 下午3:44:57 
* @description 闭锁，让线程等待在其他线程执行一系列操作完成之后
*/
public class App {
	public static void main(String args[]){
		CountDownLatch latch=new CountDownLatch(3);
		ExecutorService executor=Executors.newFixedThreadPool(3);
		long start =System.currentTimeMillis();
		for(int i=0;i<3;i++){
			executor.submit(new Processor(latch));
		}
		long end=System.currentTimeMillis();
		executor.shutdown();
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("task completed.");
		System.out.println("time taken :"+(end-start));
	}

}
	class Processor implements Runnable{
		private CountDownLatch latch;
		
		public Processor(CountDownLatch latch){
			this.latch=latch;
		}
		public void run(){
			System.out.println("started.");
			long start=System.currentTimeMillis();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long end=System.currentTimeMillis();
			System.out.println("run time sleep:"+(end-start));
			latch.countDown();
		}
	}