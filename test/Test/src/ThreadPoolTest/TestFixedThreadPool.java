package ThreadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFixedThreadPool {
	public static void main(String[] args) {

        //����һ�������ù̶��߳������̳߳�

        ExecutorService pool = Executors.newFixedThreadPool(2);

        //����ʵ����Runnable�ӿڶ���Thread����ȻҲʵ����Runnable�ӿ�

        Thread t1 = new MyThread("t1");
        Thread t2 = new MyThread("t2");
        Thread t3 = new MyThread("t3");
        Thread t4 = new MyThread("t4");
        Thread t5 = new MyThread("t5");

        //���̷߳�����н���ִ��

        pool.execute(t1);

        pool.execute(t2);

        pool.execute(t3);

        pool.execute(t4);

        pool.execute(t5);

        //�ر��̳߳�

        pool.shutdown();

    }

}