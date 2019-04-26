package org.dllwh.utils.designPattern;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 设计模式---模板模式
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月15日 下午10:19:05
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class TemplatePattern {
	static abstract class GameTemplate {
		protected void play() {
			this.initialize();
			this.startPlay();
			this.endPlay();
		}

		// 初始化游戏
		abstract void initialize();

		// 开始游戏
		abstract void startPlay();

		// 结束游戏
		abstract void endPlay();
	}

	static class Football extends GameTemplate {
		public void initialize() {
			System.out.println("初始化游戏");
		}

		public void startPlay() {
			System.out.println("开始游戏");
		}

		public void endPlay() {
			System.out.println("结束游戏");

		}
	}

	public static void main(String[] args) {
		GameTemplate eggsWithTomato = new Football();
		eggsWithTomato.play();
	}
}