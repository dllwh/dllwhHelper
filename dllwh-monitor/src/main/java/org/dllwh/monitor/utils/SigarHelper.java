package org.dllwh.monitor.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统信息收集API Sigar
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 10:32:58 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public final class SigarHelper {

	private static Sigar sigar;
	
	/**
	 * @方法描述: 获取sigar实体
	 * @return
	 */
	public static Sigar getInstance() {
		if (null == sigar) {
			sigar = new Sigar();
		}
		return sigar;
	}

	public static void main(String[] args) {
		try {
			// System信息，从jvm获取
			property();
			System.out.println("----------------------------------");
			// cpu信息
			cpu();
			System.out.println("----------------------------------");
			// 内存信息
			memory();
			System.out.println("----------------------------------");
			// 操作系统信息
			os();
			System.out.println("----------------------------------");
			// 用户信息
			who();
			System.out.println("----------------------------------");
			// 文件系统信息
			file();
			System.out.println("----------------------------------");
			// 网络信息
			net();
			System.out.println("----------------------------------");
			// 以太网信息
			ethernet();
			System.out.println("----------------------------------");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @方法描述: 获取当前（操作系统）信息，从jvm获取
	 * @throws UnknownHostException
	 */
	private static void property() throws UnknownHostException {
		Runtime r = Runtime.getRuntime();
		// 系统配置属性
		Properties props = System.getProperties();
		// java对ip封装的对象
		InetAddress addr = InetAddress.getLocalHost();
		Map<String, String> map = System.getenv();
		System.out.println("用户名:    " + map.get("USERNAME"));
		System.out.println("计算机名:    " + map.get("COMPUTERNAME"));
		System.out.println("计算机域名:    " + map.get("USERDOMAIN"));
		System.out.println("本地ip地址:    " + addr.getHostAddress());
		System.out.println("本地主机名(当前操作系统的名称):    " + addr.getHostName());
		System.out.println("JVM可以使用的总内存:    " + r.totalMemory());
		System.out.println("JVM可以使用的剩余内存:    " + r.freeMemory());
		System.out.println("JVM可以使用的处理器个数:    " + r.availableProcessors());
		System.out.println("Java的运行环境版本：    " + props.getProperty("java.version"));
		System.out.println("Java的运行环境供应商：    " + props.getProperty("java.vendor"));
		System.out.println("Java供应商的URL：    " + props.getProperty("java.vendor.url"));
		System.out.println("Java的安装路径：    " + props.getProperty("java.home"));
		System.out.println("Java的虚拟机规范版本：    " + props.getProperty("java.vm.specification.version"));
		System.out.println("Java的虚拟机规范供应商：    " + props.getProperty("java.vm.specification.vendor"));
		System.out.println("Java的虚拟机规范名称：    " + props.getProperty("java.vm.specification.name"));
		System.out.println("Java的虚拟机实现版本：    " + props.getProperty("java.vm.version"));
		System.out.println("Java的虚拟机实现供应商：    " + props.getProperty("java.vm.vendor"));
		System.out.println("Java的虚拟机实现名称：    " + props.getProperty("java.vm.name"));
		System.out.println("Java运行时环境规范版本：    " + props.getProperty("java.specification.version"));
		System.out.println("Java运行时环境规范供应商：    " + props.getProperty("java.specification.vender"));
		System.out.println("Java运行时环境规范名称：    " + props.getProperty("java.specification.name"));
		System.out.println("Java的类格式版本号：    " + props.getProperty("java.class.version"));
		System.out.println("Java的类路径：    " + props.getProperty("java.class.path"));
		System.out.println("加载库时搜索的路径列表：    " + props.getProperty("java.library.path"));
		System.out.println("默认的临时文件路径：    " + props.getProperty("java.io.tmpdir"));
		System.out.println("一个或多个扩展目录的路径：    " + props.getProperty("java.ext.dirs"));
		System.out.println("操作系统的名称：    " + props.getProperty("os.name"));
		System.out.println("操作系统的构架：    " + props.getProperty("os.arch"));
		System.out.println("操作系统的版本：    " + props.getProperty("os.version"));
		System.out.println("文件分隔符：    " + props.getProperty("file.separator"));
		System.out.println("路径分隔符：    " + props.getProperty("path.separator"));
		System.out.println("行分隔符：    " + props.getProperty("line.separator"));
		System.out.println("用户的账户名称：    " + props.getProperty("user.name"));
		System.out.println("用户的主目录：    " + props.getProperty("user.home"));
		System.out.println("用户的当前工作目录：    " + props.getProperty("user.dir"));
	}

	/**
	 * @方法描述: 获取内存信息
	 * @throws SigarException
	 */
	private static void memory() throws SigarException {
		// 物理内存信息
		Mem mem = sigar.getMem();
		System.out.println("内存总量:    " + mem.getTotal() / 1024L + "K av");
		System.out.println("当前内存使用量:    " + mem.getUsed() / 1024L + "K used");
		System.out.println("当前内存剩余量:    " + mem.getFree() / 1024L + "K free");
		// 系统页面文件交换区信息
		Swap swap = sigar.getSwap();
		System.out.println("交换区总量:    " + swap.getTotal() / 1024L + "K av");
		System.out.println("当前交换区使用量:    " + swap.getUsed() / 1024L + "K used");
		System.out.println("当前交换区剩余量:    " + swap.getFree() / 1024L + "K free");
	}

	/**
	 * @方法描述: 获取CPU数量（单位：个）
	 * @return
	 * @throws SigarException
	 */
	public static int getCpuCount() throws SigarException {
		return sigar.getCpuInfoList().length;
	}

	/**
	 * @方法描述: 获取cpu的信息
	 * @throws SigarException
	 */
	private static void cpu() throws SigarException {
		CpuInfo[] infos = sigar.getCpuInfoList();
		CpuPerc[] cpuList = sigar.getCpuPercList();
		
		for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
			CpuInfo info = infos[i];
			System.out.println("第" + (i + 1) + "块CPU信息");
			System.out.println("CPU的总量MHz:    " + info.getMhz());
			System.out.println("CPU生产商:    " + info.getVendor());
			System.out.println("CPU类别:    " + info.getModel());
			System.out.println("CPU缓存数量:    " + info.getCacheSize());
			System.out.println("CPU逻辑个数:    " + info.getTotalCores());
			if ((info.getTotalCores() != info.getTotalSockets()) || (info.getCoresPerSocket() > info.getTotalCores())) {
				System.out.println("CPU物理个数:    " + info.getTotalSockets());
				System.out.println("每个CPU核数:    " + info.getCoresPerSocket());
			}
			// 当前CPU的使用率
			printCpuPerc(cpuList[i]);
		}
	}

	/**
	 * @方法描述: 获取当前CPU的用户使用率、系统使用率、当前等待率、当前空闲率、总的使用率
	 * @param cpu
	 */
	private static void printCpuPerc(CpuPerc cpu) {
		System.out.println("CPU用户使用率:    " + CpuPerc.format(cpu.getUser()));
		System.out.println("CPU系统使用率:    " + CpuPerc.format(cpu.getSys()));
		System.out.println("CPU当前等待率:    " + CpuPerc.format(cpu.getWait()));
		System.out.println("CPU当前错误率:    " + CpuPerc.format(cpu.getNice()));
		System.out.println("CPU当前空闲率:    " + CpuPerc.format(cpu.getIdle()));
		System.out.println("CPU总的使用率:    " + CpuPerc.format(cpu.getCombined()));
		System.out.println("CPU总的中断时间:    " + CpuPerc.format(cpu.getIrq()));
	}

	/**
	 * @方法描述: 获取操作系统信息代码
	 */
	private static void os() {
		// 取当前操作系统的信息
		OperatingSystem OS = OperatingSystem.getInstance();
		// 操作系统内核类型如： 386、486、586等x86
		System.out.println("操作系统:    " + OS.getArch());
		System.out.println("操作系统CpuEndian():    " + OS.getCpuEndian());//
		System.out.println("操作系统DataModel():    " + OS.getDataModel());//
		System.out.println("操作系统的描述:    " + OS.getDescription());
		// 操作系统类型
		System.out.println("OS.getName(): " + OS.getName());
		System.out.println("OS.getPatchLevel(): " + OS.getPatchLevel());
		System.out.println("操作系统的卖主:    " + OS.getVendor());
		System.out.println("操作系统的卖主名:    " + OS.getVendorCodeName());
		System.out.println("操作系统名称:    " + OS.getVendorName());
		System.out.println("操作系统卖主类型:    " + OS.getVendorVersion());
		System.out.println("操作系统的版本号:    " + OS.getVersion());
	}

	/**
	 * @方法描述: 获取当前系统进程表中的用户信息
	 * @throws SigarException
	 */
	private static void who() throws SigarException {

		Who who[] = sigar.getWhoList();
		if (who != null && who.length > 0) {
			for (int i = 0; i < who.length; i++) {
				Who _who = who[i];
				System.out.println("用户控制台:    " + _who.getDevice());
				System.out.println("用户host:    " + _who.getHost());
				System.out.println("获取的时间getTime():    " + _who.getTime());
				System.out.println("当前系统进程表中的用户名:    " + _who.getUser());
			}
		}
	}

	/**
	 * @方法描述: 获取磁盘信息
	 * @throws Exception
	 */
	private static void file() throws Exception {
		// 通过sigar.getFileSystemList()来获得FileSystem列表对象，然后对其进行编历
		FileSystem fslist[] = sigar.getFileSystemList();
		try {
			for (int i = 0; i < fslist.length; i++) {
				System.out.println("分区的盘符名称" + i);
				FileSystem fs = fslist[i];
				System.out.println("分区的盘符名称:    " + fs.getDevName());
				System.out.println("分区的盘符路径:    " + fs.getDirName());
				System.out.println("盘符标志:    " + fs.getFlags());//
				// 文件系统类型，比如 FAT32、NTFS
				System.out.println("盘符类型:    " + fs.getSysTypeName());
				// 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
				System.out.println("盘符类型名:    " + fs.getTypeName());
				// 文件系统类型
				System.out.println("盘符文件系统类型:    " + fs.getType());
				FileSystemUsage usage = null;
				usage = sigar.getFileSystemUsage(fs.getDirName());
				switch (fs.getType()) {
				case 0: // TYPE_UNKNOWN ：未知
					break;
				case 1: // TYPE_NONE
					break;
				case 2: // TYPE_LOCAL_DISK : 本地硬盘
					// 文件系统总大小
					System.out.println(fs.getDevName() + "总大小:    " + usage.getTotal() + "KB");
					// 文件系统剩余大小
					System.out.println(fs.getDevName() + "剩余大小:    " + usage.getFree() + "KB");
					// 文件系统可用大小
					System.out.println(fs.getDevName() + "可用大小:    " + usage.getAvail() + "KB");
					// 文件系统已经使用量
					System.out.println(fs.getDevName() + "已经使用量:    " + usage.getUsed() + "KB");
					double usePercent = usage.getUsePercent() * 100D;
					// 文件系统资源的利用率
					System.out.println(fs.getDevName() + "资源的利用率:    " + usePercent + "%");
					break;
				case 3:// TYPE_NETWORK ：网络
					break;
				case 4:// TYPE_RAM_DISK ：闪存
					break;
				case 5:// TYPE_CDROM ：光驱
					break;
				case 6:// TYPE_SWAP ：页面交换
					break;
				}
				System.out.println(fs.getDevName() + "读出：    " + usage.getDiskReads());
				System.out.println(fs.getDevName() + "写入：    " + usage.getDiskWrites());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
	}

	/**
	 * @方法描述: 获取网络流量等信息
	 * @throws Exception
	 */
	private static void net() throws Exception {
		String ifNames[] = sigar.getNetInterfaceList();
		for (int i = 0; i < ifNames.length; i++) {
			String name = ifNames[i];
			NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
			System.out.println("网络设备名:    " + name);// 网络设备名
			System.out.println("IP地址:    " + ifconfig.getAddress());// IP地址
			System.out.println("子网掩码:    " + ifconfig.getNetmask());// 子网掩码
			if ((ifconfig.getFlags() & 1L) <= 0L) {
				System.out.println("!IFF_UP...skipping getNetInterfaceStat");
				continue;
			}
			NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
			System.out.println(name + "接收的总包裹数x:    " + ifstat.getRxPackets());// 接收的总包裹数
			System.out.println(name + "发送的总包裹数:    " + ifstat.getTxPackets());// 发送的总包裹数
			System.out.println(name + "接收到的总字节数:    " + ifstat.getRxBytes());// 接收到的总字节数
			System.out.println(name + "发送的总字节数:    " + ifstat.getTxBytes());// 发送的总字节数
			System.out.println(name + "接收到的错误包数:    " + ifstat.getRxErrors());// 接收到的错误包数
			System.out.println(name + "发送数据包时的错误数:    " + ifstat.getTxErrors());// 发送数据包时的错误数
			System.out.println(name + "接收时丢弃的包数:    " + ifstat.getRxDropped());// 接收时丢弃的包数
			System.out.println(name + "发送时丢弃的包数:    " + ifstat.getTxDropped());// 发送时丢弃的包数
		}
	}

	/**
	 * @方法描述: 获取以太网信息
	 * @throws SigarException
	 */
	private static void ethernet() throws SigarException {
		String[] ifaces = sigar.getNetInterfaceList();
		for (int i = 0; i < ifaces.length; i++) {
			NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
			if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
					|| NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
				continue;
			}
			System.out.println(cfg.getName() + "IP地址:    " + cfg.getAddress());// IP地址
			System.out.println(cfg.getName() + "网关广播地址:    " + cfg.getBroadcast());// 网关广播地址
			System.out.println(cfg.getName() + "网卡MAC地址:    " + cfg.getHwaddr());// 网卡MAC地址
			System.out.println(cfg.getName() + "子网掩码:    " + cfg.getNetmask());// 子网掩码
			System.out.println(cfg.getName() + "网卡描述信息:    " + cfg.getDescription());// 网卡描述信息
			System.out.println(cfg.getName() + "网卡类型:    " + cfg.getType());//
		}
	}
}
