package org.dllwh.utils.designPattern;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 适配器模式是作为两个不兼容的接口之间的桥梁。 这种类型的设计模式属于结构型模式，它结合了两个独立接口的功能。
 *       这种模式涉及到一个单一的类，该类负责加入独立的或不兼容的接口功能。
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月7日 下午8:43:58
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class AdapterMode {

	/**
	 * @方法描述:一个手机充电器需要的电压是20V，但是正常的电压是220V，这时候就需要一个变压器，将220V的电压转换成20V的电压，这样，
	 * 变压器就将20V的电压和手机联系起来了
	 * @param args
	 */
	public static void main(String[] args) {
		Phone phone = new Phone();
		VoltageAdapter adapter = new VoltageAdapter();
		phone.setAdapter(adapter);
		phone.charge();
	}

	// 手机类
	static class Phone {
		public static final int	V	= 220;	// 正常电压220v，是一个常量

		private VoltageAdapter	adapter;

		// 充电
		public void charge() {
			adapter.changeVoltage();
		}

		public void setAdapter(VoltageAdapter adapter) {
			this.adapter = adapter;
		}
	}

	// 变压器
	static class VoltageAdapter {
		// 改变电压的功能
		public void changeVoltage() {
			System.out.println("正在充电...");
			System.out.println("原始电压：" + Phone.V + "V");
			System.out.println("经过变压器转换之后的电压:" + (Phone.V - 200) + "V");
		}
	}
}