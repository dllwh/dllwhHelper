package org.dllwh.common.base;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 返回结果封装
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午10:42:15
 * @版本: V1.0
 * @since: JDK 1.8
 */
public final class ResultResponse {
	/** 结果状态码 */
	private Integer resultCode;
	/** 提示信息 */
	private String errorMsg;
	/** 具体的内容 */
	private Object data;

	private ResultResponse() {
		this.resultCode = 200;
		this.errorMsg = "success";
	}

	private ResultResponse(Integer errorCode) {
		this.resultCode = errorCode;
		this.errorMsg = "error";
	}

	private ResultResponse(Object data) {
		this.resultCode = 200;
		this.errorMsg = "success";
		this.data = data;
	}

	private ResultResponse(Integer errorCode, Object data) {
		this.resultCode = errorCode;
		this.errorMsg = "success";
		this.data = data;
	}

	private ResultResponse(Integer errorCode, String errorMsg, Object data) {
		this.resultCode = errorCode;
		this.errorMsg = errorMsg;
		this.data = data;
	}

	/**
	 * @方法描述:成功时候的调用
	 * @param data
	 * @return
	 */
	public static ResultResponse success() {
		return new ResultResponse();
	}

	/**
	 * @方法描述:成功时候的调用
	 * @param data
	 * @return
	 */
	public static ResultResponse success(Object data) {
		return new ResultResponse(data);
	}

	/**
	 * @方法描述:失败时候的调用
	 * @param data
	 * @return
	 */
	public static ResultResponse error(Integer errorCode) {
		return new ResultResponse(errorCode);
	}

	/**
	 * @方法描述:失败时候的调用
	 * @param data
	 * @return
	 */
	public static ResultResponse error(Integer errorCode, Object data) {
		return new ResultResponse(errorCode, data);
	}

	/**
	 * @方法描述:失败时候的调用
	 * @param data
	 * @return
	 */
	public static ResultResponse error(Integer errorCode, String errorMsg, Object data) {
		return new ResultResponse(errorCode, errorMsg, data);
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
