package producerConsumer;

import java.util.Scanner;

/** 
* @author xiepeng
* @time 2016年12月19日 下午4:56:42 
* @description 
*/
public class Processor {
	public void produce() throws InterruptedException{
		synchronized (this){
			System.out.println("produce thread running...");
			wait(); //this.wait() is fine
			System.out.println("Resumed.");
		}
	}
	public void consume() throws InterruptedException{
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		Thread.sleep(1000);
		synchronized (this){
			System.out.println("waiting for return key.");
			scanner.nextLine();
			System.out.println("return key pressed.");
			notify();
			Thread.sleep(5000);
			System.out.println("consumption done.");
		}
	}
}
