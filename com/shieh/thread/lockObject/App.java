package lockObject;
/** 
* @author xiepeng
* @time 2016��12��17�� ����6:02:26 
* @description ģ��һ���������������
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
