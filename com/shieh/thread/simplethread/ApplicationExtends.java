package simplethread;
/** 
* @author xiepeng
* @time 2016年12月17日 下午5:28:29 
* @description  starting threads with extends 
*/
public class ApplicationExtends {
	public static void main(String args[]){
		Runner runner=new Runner();
		runner.start();
		
		Runner runner1=new Runner();
		runner1.start();
	}

}
	class Runner extends Thread{
		@Override
		public void run(){
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
