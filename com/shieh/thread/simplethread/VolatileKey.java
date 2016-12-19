package simplethread;

import java.util.Scanner;

/** 
* @author xiepeng
* @time 2016��12��17�� ����5:38:13 
* @description volatile �ؼ��֣���֤���εı������κ��̶߳�ȡ��ʱ�򶼻��������޸ĵ�ֵ
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
