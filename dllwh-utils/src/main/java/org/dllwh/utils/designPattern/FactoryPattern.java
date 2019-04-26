package org.dllwh.utils.designPattern;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 工厂模式
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月26日 下午9:45:53
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class FactoryPattern {
	// 抽象的接口
	abstract interface Car {
		public abstract void run();

		public abstract void stop();
	}

	// 具体实现类
	static class Benz implements Car {
		public void run() {
			System.out.println("Benz开始启动了。。。。。");
		}

		public void stop() {
			System.out.println("Benz停车了。。。。。");
		}
	}

	// 具体实现类
	static class Ford implements Car {
		public void run() {
			System.out.println("Ford开始启动了。。。");
		}

		public void stop() {
			System.out.println("Ford停车了。。。。");
		}
	}

	// 工厂类
	static class Factory {
		public static Car getCarInstance(String type) {
			Car c = null;
			if ("Benz".equals(type)) {
				c = new Benz();
			}
			if ("Ford".equals(type)) {
				c = new Ford();
			}
			return c;
		}
	}

	public static void main(String[] args) {
		Car c = Factory.getCarInstance("Benz");
		if (c != null) {
			c.run();
			c.stop();
		} else {
			System.out.println("造不了这种汽车。。。");
		}
	}
}