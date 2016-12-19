package producerConsumer;

import java.util.LinkedList;
import java.util.Random;

/** 
* @author xiepeng
* @time 2016年12月19日 下午5:28:27 
* @description 
*/
public class PVwithLock {
	public static void main(String args[]) throws InterruptedException{
		final Process process=new Process();
		Thread t1=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					process.produce();
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
					process.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		});
		t1.start();
		t2.start();
		//t1.join();
		//t2.join();
		
		Thread.sleep(10000);
		System.exit(0);
	}
}
	class Process{
		private LinkedList<Integer> list=new LinkedList<>();
		private final int LIMIT=10;
		private final Object lock=new Object();
		
		public void produce() throws InterruptedException{
			int value=0;
			while(true){
				synchronized(lock){
					while(list.size()==LIMIT){
						lock.wait();
					}
					list.add(value);
					
					System.out.println("producer added: "+value+"queue size is "+list.size());
					value++;
					lock.notify();
				}
			}
		}
		
		public void consume() throws InterruptedException{
			Random random=new Random();
			while(true){
				synchronized(lock){
					while(list.size()==0){
						lock.wait();
					}
					
					int value=list.removeFirst();
					System.out.println("removed value by consume is : "+value);
					System.out.println(" now list size is :"+list.size());
					lock.notify();
				}
				Thread.sleep(random.nextInt(1000)); //force producer fill the queue to LIMIT_SIZE
			}
		}
	}