package simplethread;

import java.util.Scanner;

/** 
* @author xiepeng
* @time 2016年12月17日 下午5:38:13 
* @description volatile 关键字，保证修饰的变量在任何线程读取的时候都会是最新修改的值
*/
public class VolatileKey {
	@SuppressWarnings("resource")
	public static void main(String args[]){
		Processor pro=new Processor();
		pro.start();
		//wait for the enter key
		System.out.println("enter something to stop the thread, volatile variable running will be forced to true");
		new Scanner(System.in).nextLine();
		pro.shutdown();
	}

}
	class Processor extends Thread{
		private volatile boolean running=true;
		
		public void run(){
			while(running){
				System.out.println("running");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		public void shutdown(){
			running=false;
		}
	}
