package org.dllwh.utils.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: CountDownLatch用于确保任务在启动之前等待其他线程
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月26日 下午9:36:52
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class CountDownLatchDemo {
	static class Worker extends Thread {
		private int				delay;
		private CountDownLatch	latch;

		public Worker(int delay, CountDownLatch latch, String name) {
			super(name);
			this.delay = delay;
			this.latch = latch;
		}

		public void run() {
			try {
				Thread.sleep(delay);
				latch.countDown();
				System.out.println(Thread.currentThread().getName() + " finished");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) throws InterruptedException {
		// 创建CountDownLatch的对象时，我们指定它应该等待的线程数
		CountDownLatch latch = new CountDownLatch(4);
		Worker first = new Worker(1000, latch, "worker-1");
		Worker second = new Worker(2000, latch, "worker-2");
		Worker third = new Worker(3000, latch, "worker-3");
		Worker fourth = new Worker(4000, latch, "worker-4");
		first.start();
		second.start();
		third.start();
		fourth.start();
		// 主任务等待其余线程
		latch.await();
		// 主线程启动
		System.out.println(Thread.currentThread().getName() + " has finished");
	}
}