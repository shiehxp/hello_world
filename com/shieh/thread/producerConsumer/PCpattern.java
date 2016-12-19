package producerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/** 
* @author xiepeng
* @time 2016年12月19日 下午4:31:27 
* @description 
*/
public class PCpattern {
	private static BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(10);
	
	public  static void main(String args[]){
		Thread t1=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		});
		
		Thread t2=new Thread(new Runnable(){
			public void run(){
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t2.start();
		t1.start();
	}
	
	private static void producer() throws InterruptedException{
		System.out.println("enter producer!");
		Random random=new Random();
		while(true){
			queue.put(random.nextInt(100));
		}
	}
	
	private static void consumer() throws InterruptedException{
		System.out.println("enter consumer!");
		Random random=new Random();
		while(true){
			Thread.sleep(100);
			Integer i=random.nextInt(10);
			if(i==0){
				Integer value=queue.take();
				System.out.println("taken value: "+value+"; queue size is: "+queue.size()+" "+i);
			}
		}
	}

}
