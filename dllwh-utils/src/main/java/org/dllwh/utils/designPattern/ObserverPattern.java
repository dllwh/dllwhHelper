package org.dllwh.utils.designPattern;

import java.util.ArrayList;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 设计模式---观察者模式的demo
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月15日 下午9:16:34
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class ObserverPattern {
	/**
	 * @类描述: 第一步：定义观察者接口
	 */
	public interface Listener {
		void acceptMsg();
	}

	/**
	 * @类描述: 第二步：实现主题的类
	 */
	public static class Subject {
		private ArrayList<Listener> listeners;

		public Subject() {
			listeners = new ArrayList<Listener>();
		}

		/**
		 * @方法描述:实现 添加一个观察者的操作
		 */
		public void addListener(Listener listener) {
			listeners.add(listener);
		}

		/**
		 * @方法描述:实现删除一个观察者的操作
		 */
		public void deleteListener(Listener listener) {
			int index = listeners.indexOf(listener);
			if (index < 0) {
				listeners.remove(index);
			}
		}

		/**
		 * @方法描述:实现通知的机制 ，通知每一个观察者
		 */
		public void notifyListener() {
			for (int i = 0; i < listeners.size(); i++) {
				Listener listener = listeners.get(i);
				listener.acceptMsg();
			}
		}
	}

	/**
	 * @方法描述:第三步：3.1 实现红色交通灯的观察者类
	 */
	public static class RedLightListener implements Listener {
		@Override
		public void acceptMsg() {
			System.out.println("红灯亮了，禁止通行");
		}
	}

	/**
	 * @方法描述:第三步：3.2 实现黄色交通灯的观察者类
	 */
	public static class YellowLightListener implements Listener {
		@Override
		public void acceptMsg() {
			System.out.println("黄灯亮了，请稍等");
		}
	}

	/**
	 * @方法描述:第三步：3.3 实现绿色交通灯的观察者类
	 */
	public static class GreenLightListener implements Listener {
		@Override
		public void acceptMsg() {
			System.out.println("绿灯亮了，请通过");
		}
	}

	/**
	 * @方法描述:第四步：测试
	 */
	public static void main(String[] args) {
		Subject subject = new Subject();

		// 创建三个观察者实例
		YellowLightListener yellow = new YellowLightListener();
		GreenLightListener green = new GreenLightListener();
		RedLightListener red = new RedLightListener();

		// 向主题注册三个观察者，监听变化
		subject.addListener(green);
		subject.addListener(red);
		subject.addListener(yellow);

		subject.notifyListener();
	}
}