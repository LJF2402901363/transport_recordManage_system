package com.qst.dms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 陌意随影
 TODO :验证输入是否是合法格式工具类类
 *2019年12月21日  上午10:08:33
 */
public class VerificationUtil {
  /**
   * 验证传入的content是否符合regex的格式要求
 * @param content 需要被判定的内容
 * @param regex 判断的正则表达式
 * @return 满足正则表达式则返回true，否则返回false
 */
public static boolean isLegalFormat(String content,String regex){
	 Pattern p = Pattern.compile(regex);
	 Matcher m = p.matcher(content);
	return m.matches();
	  
  }
}
