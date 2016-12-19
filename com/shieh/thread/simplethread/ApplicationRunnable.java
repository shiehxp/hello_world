package simplethread;
/** 
* @author xiepeng
* @time 2016年12月17日 下午5:31:14 
* @description  starting threads using Runnable
*/
public class ApplicationRunnable {
	public static void main(String args[]){
		Thread t1=new Thread(new RunnerRunnable());
		Thread t2=new Thread(new RunnerRunnable());
		
		t1.start();
		t2.start();
	}

}

class RunnerRunnable implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			System.out.println("hello :"+i+" thread "+Thread.currentThread().getName());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
