package threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** 
* @author xiepeng
* @time 2016年12月19日 下午3:24:50 
* @description 	使用线程池实现多线程
*/
public class WorkerThreadPool {
	public static void main(String args[]){
		ExecutorService executor=Executors.newFixedThreadPool(2);
		System.out.println("starting");
		long start=System.currentTimeMillis();
		Worker worker=new Worker();
		for(int i=0;i<2;i++){
			executor.submit(worker);
		}
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end=System.currentTimeMillis();
		System.out.println("time taken: "+(end-start) );
		System.out.println("list1: "+worker.list1.size()+"list2: "+worker.list2.size());
	}
	
}
	class Worker implements Runnable{
		private Random random=new Random();
		private final Object lock1=new Object();
		private final Object lock2=new Object();
		
		public List<Integer> list1=new ArrayList<Integer>();
		public List<Integer> list2=new ArrayList<Integer>();

		public void process(){
			for(int i=0;i<1000;i++){
				stageOne();
				stageTwo();
			}
		}
		public void stageOne(){
			synchronized(lock1){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list1.add(random.nextInt(100));
			}
		}
		
		public void stageTwo(){
			synchronized(lock2){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list2.add(random.nextInt(100));
			}
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			process();
		}
		
	}