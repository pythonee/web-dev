/**
 * taobao.com 2008 copyright
 */
package com.taobao.api;

/**
 * @version 2008-2-29
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 * 
 *         <table>
 *         <thead>
 *         <tr>
 *         <td>错误码</td>
 *         <td>错误信息</td>
 *         <td>说明</td>
 *         </tr>
 *         </thead> <tbody>
 *         <tr>
 *         <td>3</td>
 *         <td>Upload fail</td>
 *         <td>上传出错</td>
 *         </tr>
 *         <tr>
 *         <td>9</td>
 *         <td>[Http action] not allowed</td>
 *         <td>该方法不允许使用此Http动作</td>
 *         </tr>
 *         <tr>
 *         <td>10</td>
 *         <td>Service currently unavailable</td>
 *         <td>服务不可用</td>
 *         </tr>
 *         <tr>
 *         <td>11</td>
 *         <td>Insufficient ISV permissions</td>
 *         <td>第三方程序权限不够</td>
 *         </tr>
 *         <tr>
 *         <td>12</td>
 *         <td>Insufficient user permissions</td>
 *         <td>用户权限不够</td>
 *         </tr>
 *         <tr>
 *         <td>21</td>
 *         <td>Missing Method</td>
 *         <td>方法丢失</td>
 *         </tr>
 *         <tr>
 *         <td>22</td>
 *         <td>Invalid Method</td>
 *         <td>方法无效</td>
 *         </tr>
 *         <tr>
 *         <td>23</td>
 *         <td>Invalid Format</td>
 *         <td>响应格式无效</td>
 *         </tr>
 *         <tr>
 *         <td>24</td>
 *         <td>Missing signature</td>
 *         <td>签名丢失</td>
 *         </tr>
 *         <tr>
 *         <td>25</td>
 *         <td>Invalid signature</td>
 *         <td>签名无效</td>
 *         </tr>
 *         <tr>
 *         <td>26</td>
 *         <td>Missing session</td>
 *         <td>会话期识别码丢失</td>
 *         </tr>
 *         <tr>
 *         <td>27</td>
 *         <td>Invalid session</td>
 *         <td>会话期识别码无效</td>
 *         </tr>
 *         <tr>
 *         <td>28</td>
 *         <td>Missing API Key</td>
 *         <td>api_key丢失</td>
 *         </tr>
 *         <tr>
 *         <td>29</td>
 *         <td>Invalid API Key</td>
 *         <td>api_key无效</td>
 *         </tr>
 *         <tr>
 *         <td>30</td>
 *         <td>Missing timestamp</td>
 *         <td>时间戳丢失</td>
 *         </tr>
 *         <tr>
 *         <td>31</td>
 *         <td>Invalid timestamp</td>
 *         <td>时间戳无效</td>
 *         </tr>
 *         <tr>
 *         <td>32</td>
 *         <td>Missing version</td>
 *         <td>版本丢失</td>
 *         </tr>
 *         <tr>
 *         <td>33</td>
 *         <td>Invalid version</td>
 *         <td>版本错误</td>
 *         </tr>
 *         <tr>
 *         <td>40</td>
 *         <td>Missing required arguments</td>
 *         <td>参数丢失，指除method, api_key, sign,timestamp，session,format,ver外的其他参数丢失
 *         </td>
 *         </tr>
 *         <tr>
 *         <td colspan="3">TBQL错误</td>
 *         </tr>
 *         <tr>
 *         <td>100</td>
 *         <td>Error while parsing TBQL statement</td>
 *         <td>TBQL执行错误</td>
 *         </tr>
 *         <tr>
 *         <td>101</td>
 *         <td>The field you requested does not exist</td>
 *         <td>请求的字段不存在</td>
 *         </tr>
 *         <tr>
 *         <td>102</td>
 *         <td>The table you requested does not exist</td>
 *         <td>请求的表不存在</td>
 *         </tr>
 *         <tr>
 *         <td>103</td>
 *         <td>Your statement is not indexable</td>
 *         <td>语句不能被索引</td>
 *         </tr>
 *         <tr>
 *         <td>104</td>
 *         <td>The function you called down not exist</td>
 *         <td>调用的函数不存在</td>
 *         </tr>
 *         <tr>
 *         <td>105</td>
 *         <td>Wrong number of arguments passed into the function</td>
 *         <td>函数参数个数不正确</td>
 *         </tr>
 *         <tr>
 *         <td>106</td>
 *         <td>Wrong type of arguments passed into the function</td>
 *         <td>函数参数类型不正确</td>
 *         </tr>
 *         <tr>
 *         <td colspan="3">用户错误</td>
 *         </tr>
 *         <tr>
 *         <td>200</td>
 *         <td>People not found</td>
 *         <td>用户不存在</td>
 *         </tr>
 *         <tr>
 *         <td colspan="3">商品错误</td>
 *         </tr>
 *         <tr>
 *         <td>300</td>
 *         <td>Item not found</td>
 *         <td>商品不存在</td>
 *         </tr>
 *         <tr>
 *         <td colspan="3">交易错误</td>
 *         </tr>
 *         <tr>
 *         <td>400</td>
 *         <td>trade not found</td>
 *         <td>交易不存在</td>
 *         </tr>
 *         <tr>
 *         <td colspan="3">店铺错误</td>
 *         </tr>
 *         <tr>
 *         <td>500</td>
 *         <td>shop not found</td>
 *         <td>店铺不存在</td>
 *         </tr>
 *         <tr>
 *         <td colspan="3">评价错误</td>
 *         </tr>
 *         <tr>
 *         <td>600</td>
 *         <td>rate not found</td>
 *         <td>评价不存在</td>
 *         </tr>
 *         </tbody>
 *         </table>
 * 
 */
@Deprecated
public enum ErrorCode {
	UPLOAD_FAIL(3, "Upload fail"), CALL_LIMITED_SESSION(5,
			"Session Call limited"), CALL_LIMITED_PARTNER(6,
			"Partner Call limited"), HTTP_ACTION_NOT_ALLOWED(9,
			"Http action not allowed"), SERVICE_CURRENTLY_UNAVAILABLE(10,
			"Service currently unavailable"), INSUFFICIENT_ISV_PERMISSIONS(11,
			"Insufficient isv permissions"), INSUFFICIENT_USER_PERMISSIONS(12,
			"Insufficient user permissions"), INSUFFICIENT_PARTNER_PERMISSIONS(
			13, "Insufficient partner permissions"), MISSING_METHOD(21,
			"Missing method"), INVALID_METHOD(22, "Invalid method"), INVALID_FORMAT(
			23, "Invalid format"), MISSING_SIGNATURE(24, "Missing signature"), INVALID_SIGNATURE(
			25, "Invalid signature"), MISSING_SESSION(26, "Missing session"), INVALID_SESSION(
			27, "Invalid session"), MISSING_API_KEY(28, "Missing api key"), INVALID_API_KEY(
			29, "Invalid api Key"), MISSING_TIMESTAMP(30, "Missing timestamp"), INVALID_TIMESTAMP(
			31, "Invalid timestamp"), MISSING_VERSION(32, "Missing version"), INVALID_VERSION(
			33, "Invalid version"), MISSING_REQUIRED_ARGUMENTS(40,
			"Missing required arguments"), ERROR_STATEMENT_IS_NOT_INDEXABLE(
			501, "Your statement is not indexable"), ERROR_PARSING_TBQL(503,
			"Error while parsing TBQL statement"), ERROR_USER_SERVICE_UNVAILABLE(
			550, "User service unvailable"), ERROR_ITEM_SERVICE_UNVAILABLE(551,
			"Item service unvailable"), ERROR_ITEM_IMAGE_SERVICE_UNVAILABLE(
			552, "Item image service unvailable"), ERROR_TRADE_SERVICE_UNVAILABLE(
			560, "Trade service unvailable"), ERROR_USER_NOT_EXIST(601,
			"User not exist");

	private int code;

	private String msg;

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return code + ":" + msg;
	}

	private ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
