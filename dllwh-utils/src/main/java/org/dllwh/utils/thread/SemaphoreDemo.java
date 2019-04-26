package org.dllwh.utils.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * 
 * 		<p>
 *       Semaphore,又称作为信号量，通过使用计数器来控制对共享资源的访问。如果计数器大于零，则允许访问。如果为零，则拒绝访问。可以理解为，
 *       多个车抢停车场的多个车位。当进入车位时，调用acquire()方法占用资源。当离开时，调用release()方法释放资源。
 *       </p>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月26日 下午9:51:47
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class SemaphoreDemo {
	public static void main(String[] args) {
		int threadNums = 5;
		BlockingQueue<String> parks = new LinkedBlockingQueue<>(threadNums);
		parks.offer("车位一");
		parks.offer("车位二");
		parks.offer("车位三");
		parks.offer("车位四");
		parks.offer("车位五");
		ExecutorService executorService = Executors.newCachedThreadPool();

		Semaphore semaphore = new Semaphore(threadNums);
		for (int i = 1; i <= 100; i++) {
			final int no = i;

			Thread thread = new Thread(() -> {
				try {
					semaphore.acquire();
					String park = parks.take();
					System.out.println("车辆【" + no + "】获取到: " + park);
					Thread.sleep((long) Math.random() * 6000);
					// 线程释放掉许可，通俗来将就是将semaphore内部的数字加1
					semaphore.release();
					parks.offer(park); // 归还车位
					System.out.println("车辆【" + no + "】离开 " + park);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			executorService.execute(thread);
		}
	}
}