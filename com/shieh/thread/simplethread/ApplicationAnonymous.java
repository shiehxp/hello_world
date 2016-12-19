package simplethread;
/** 
* @author xiepeng
* @time 2016年12月17日 下午5:23:34 
* @description  starting threads using the thread constructor with anonymous(匿名) classes
*/
	
public class ApplicationAnonymous {
	public static void main(String [] args){
		Thread thread=new Thread(new Runnable(){

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
			
		});
		thread.start();
	}
}
