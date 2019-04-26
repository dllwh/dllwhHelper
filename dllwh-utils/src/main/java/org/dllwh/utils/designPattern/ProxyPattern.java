package org.dllwh.utils.designPattern;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 设计模式---代理模式
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月26日 下午9:48:44
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class ProxyPattern {

	/**
	 * @类描述: 代理接口
	 */
	interface StaticProxyInterface {
		// 需要代理的是结婚这件事
		void marry();
	}

	class WeddingCompany implements StaticProxyInterface {
		private StaticProxyInterface proxyInterface;

		public WeddingCompany(StaticProxyInterface proxyInterface) {
			this.proxyInterface = proxyInterface;
		}

		public void marry() {
			System.out.println("我们是婚庆公司代理结婚");
			proxyInterface.marry();
		}
	}

	class NormalHome implements StaticProxyInterface {
		public void marry() {
			System.out.println("我们结婚啦～");
		}
	}

	/**
	 * @方法描述:静态代理
	 */
	public void StaticProxy() {
		StaticProxyInterface proxyInterface = new WeddingCompany(new NormalHome());
		proxyInterface.marry();
	}

	public static void main(String[] args) {
		new ProxyPattern().StaticProxy();
	}
}