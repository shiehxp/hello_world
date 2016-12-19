package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** 
* @author xiepeng
* @time 2016年12月19日 下午3:24:34 
* @description   使用线程池
*/
public class App {
	public static void main(String args[]){
		ExecutorService executor=Executors.newFixedThreadPool(2);
		for(int i=0;i<5;i++){
			executor.submit(new Processor(i));
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("all task completed.");
	}
}

	class Processor implements Runnable{
		private int id;
		public Processor(int id){
			this.id=id;
		}
		public void run(){
			System.out.println("starting: "+id);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}