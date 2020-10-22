package com.qst.dms.factory;

import com.qst.dms.dao.DAO;
import com.qst.dms.util.ConstantsConfig;

/**
 * @author 陌意随影
 TODO :通过工厂模式产生对应的dao实现类
 *2019年12月19日  下午11:18:47
 */
public class DaoFactory {
  /**
   * 通过一个dao的实现类返回对应的实现类
 * @return 返回一个dao实例
 */
public static DAO<?> getDaoImpl(String daoName){
	try {
	switch ( daoName) {
	case "ImplTransportDao":
			return (DAO<?>) Class.forName(ConstantsConfig.IMPLTRANSPORTDAO).newInstance();
case "ImplMatchedTransportDao":
		return (DAO<?>) Class.forName(ConstantsConfig.IMPLMATCHEDTRANSPORTDAO).newInstance();
case "ImplMatchedLogRecDao":
		return (DAO<?>) Class.forName(ConstantsConfig.IMPLMATCHEDLOGRECDAO).newInstance();
case "ImplLogRecDao":
		return (DAO<?>) Class.forName(ConstantsConfig.IMPLLOGRECDAO).newInstance();
case "ImplUserDao":
		return (DAO<?>) Class.forName(ConstantsConfig.IMPLUSERDAO).newInstance();
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;  
  }
}
