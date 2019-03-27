package cn.neusoft.xuxiao.util;

public class StatusConstant {


	public static final Integer NO = 0;
	public static final Integer YES = 1;

	/** 用户状态 禁用 */
	public static final Integer USER_STATUS_JY = 0;

	/** 用户状态 正常 */
	public static final Integer USER_STATUS_ZX = 1;


	// 错误代码
	/**获取成功*/
	public static final Integer SUCCESS_CODE = 200;

	public static final Integer BUSINESS_EXCEPTION = 201;
	// 201 备用
	/**获取失败*/
	public static final Integer Fail_CODE = 202;
	/**参数异常*/
	public static final Integer ARGUMENTS_EXCEPTION = 203;

	/**对象不存在*/
	public static final Integer OBJECT_NOT_EXIST = 1002;
	/**没有权限 错误代码*/
	public static final Integer NOT_AGREE = 1001;
	/**字段不能为空*/
	public static final Integer FIELD_NOT_NULL= 1003;
	/**正在审核*/
	public static final Integer PENDING = 1004;
	/**未登录*/
	public static final Integer NOTLOGIN= 1005;
	/**没有数据*/
	public static final Integer NO_DATA = 1006;
	/**账户被冻结*/
	public static final Integer ACCOUNT_FROZEN = 1007;
	/**订单无效*/
	public static final Integer ORDER_INVALID = 1008;
	/**状态异常*/
	public static final Integer ORDER_STATUS_ABNORMITY = 1009;
	/**对象已经存在*/
	public static final Integer OBJECT_EXIST = 1010;
	/**用户不存在	 */
	public static final Integer NOT_EXIST=1011;
	/**用户未绑定	 */
	public static final Integer NOT_BINDING=1012;


	/** 用户不存在 */
	public static final Integer USER_DOES_NOT_EXIST = 1050;
	/** 用户名或者密码错误 */
	public static final Integer PASSWORD_ERROR = 1051;


	// REDIS KEYS
	/** 省级数据 */
	public static final String JUSTBON_ALL_CITY_P = "justbon_all_city_P";

	/** 地级市 */
	public static final String JUSTBON_ALL_CITY_C = "justbon_all_city_C";

	/** 区县级 */
	public static final String JUSTBON_ALL_CITY_Q = "justbon_all_city_Q";

	/**
	 * 答题总时间
	 */
	public static final int  ALL_EXAM_TIME = 120;

}
