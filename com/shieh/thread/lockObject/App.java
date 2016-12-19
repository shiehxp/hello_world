package lockObject;
/** 
* @author xiepeng
* @time 2016年12月17日 下午6:02:26 
* @description 模拟一下锁对象和锁方法
*/
public class App {
	public static void main(String args[]){
		System.out.println("synchronized objects:");
		Worker worker=new Worker();
		worker.main();
		
		System.out.println("synchronized methods:");
		WorkerMethodsSynchronized worker1=new WorkerMethodsSynchronized();
		worker1.main();
		
	}
}
