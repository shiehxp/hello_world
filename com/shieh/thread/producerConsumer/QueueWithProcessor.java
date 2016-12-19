package producerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @author xiepeng
* @time 2016年12月19日 下午5:05:53 
* @description 
*/
public class QueueWithProcessor {
	public static void main(String args[]) throws InterruptedException{
		final BlockingQueue<Integer> blockingQueue=new BlockingQueue<>(10);
		final Random random=new Random();
		Thread t1=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					while(true){
						blockingQueue.put(random.nextInt(10));
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}		
		});
		
		Thread t2=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try{
					while(true){
						blockingQueue.take();
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}		
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

}
	class BlockingQueue<T>{
		private Queue<T> queue=new LinkedList<>();
		private int capacity;
		private Lock lock=new ReentrantLock();
		
		private Condition notFull=lock.newCondition();
		private Condition notEmpty=lock.newCondition();
		
		public BlockingQueue(int capacity){
			this.capacity=capacity;
		}
		
		public void put(T element) throws InterruptedException{
			lock.lock();
			try{
				while(queue.size()==capacity){
					System.out.println("queue is full cannot put");
					notFull.await();
				}
				queue.add(element);
				System.out.println("added to the queue "+element);
				notEmpty.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public T take() throws InterruptedException{
			lock.lock();
			try{
				while(queue.isEmpty()){
					System.out.println("queue is empty,cannot take");
					notEmpty.await();
				}
				T item=queue.remove();
				System.out.println("removed to the queue "+item);
				notFull.signal();
				return item;
			}finally{
				lock.unlock();
			}
		}
	}