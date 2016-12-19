package lockObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** 
* @author xiepeng
* @time 2016年12月17日 下午6:02:33 
* @description 复杂的多线程  锁对象
*/
public class Worker {
	private Random random=new Random();
	
	private final Object lock1=new Object();
	private final Object lock2=new Object();
	
	private List<Integer> list1=new ArrayList<>();
	private List<Integer> list2=new ArrayList<>();
	
	public void stageOne(){
		synchronized(lock1){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//do your work here
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
	
	public void process(){
		for(int i=0;i<100;i++){
			stageOne();
			stageTwo();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void main(){
		System.out.println("starting ...");
		long start=System.currentTimeMillis();
		Thread t1=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				process();
			}
		});
		
		Thread t2=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				process();
			}
		});
		
		t1.start();
		t2.start();
		
		try{
			t1.join();
			t2.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		long end=System.currentTimeMillis();
		System.out.println("time taken "+(end -start));
		System.out.println("list1: "+list1.size()+ ";list2: "+list2.size());
		t1.stop();
		t2.stop();
	}

}
