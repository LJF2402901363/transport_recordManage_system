package com.qst.dms.factory;
import com.qst.dms.service.BaseService;
import com.qst.dms.util.ConstantsConfig;

/**
 * @author 陌意随影
 TODO :用于实例化服务类的工厂
 *2019年12月20日  上午12:40:26
 */
public class ServiceFactory {
	/**
	 * 通过业务逻辑的类名返回它的实例对象
	 * @param serviceName 类名
	 * @return  返回对应的业务逻辑实现类
	 */
	public static BaseService<?> getServiceImpl(String serviceName){
		try {
			switch (serviceName) {
			case "LogRecService":
				return (BaseService<?>) Class.forName(ConstantsConfig.LOGRECSERVICE).newInstance();
			case "TransportService":
				return (BaseService<?>) Class.forName(ConstantsConfig.TRANSPORTSERVICE).newInstance();
			case "UserService":
				return (BaseService<?>) Class.forName(ConstantsConfig.USERSERVICE).newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
