package com.qst.dms.factory;
import com.qst.dms.gather.IDataAnalyse;
import com.qst.dms.util.ConstantsConfig;

/**
 * @author 陌意随影
 TODO :
 *2019年12月20日  上午1:08:27
 */
public class DataAnalyseFactory {
	/**
	 * 通过业务逻辑的类名返回它的实例对象
	 * @param serviceName 类名
	 * @return  返回对应的业务逻辑实现类
	 */
	public static  IDataAnalyse  getDataAnalyseImpl(String serviceName){
		try {
			switch (serviceName) {
			case "LogRecAnalyse":
				return (IDataAnalyse) Class.forName(ConstantsConfig.LOGRECANALYSE).newInstance();
			case "TransportAnalyse":
				return (IDataAnalyse) Class.forName(ConstantsConfig.TRANSPORTANALYSE).newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
