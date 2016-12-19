package countDowmLatch;

import java.util.concurrent.CountDownLatch;

/** 
* @author xiepeng
* @time 2016年12月19日 下午4:09:23 
* @description 
*/
public class Driver {
	public static void main(String args[]) throws InterruptedException{
		CountDownLatch start=new CountDownLatch(1);
		CountDownLatch end=new CountDownLatch(10);
		for(int i=0;i<10;++i){
			new Thread(new Worker(start,end)).start();
		}
		System.out.println("do something else");
		start.countDown();			// let all threads proceed
		System.out.println("do something else");
		end.await();  	// wait for all to finish
	}
}
	class Worker implements Runnable{
		private final CountDownLatch startsignal;
		private final CountDownLatch endsignal;
		
		public Worker(CountDownLatch startsignal,CountDownLatch endsignal){
			this.startsignal=startsignal;
			this.endsignal=endsignal;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				startsignal.await();
				doWork();
				endsignal.countDown();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		void doWork(){
			System.out.println("haha");
		}
		
	}