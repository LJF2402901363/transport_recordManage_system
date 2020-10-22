
package com.qst.dms.entity;

/**
 * @author 陌意随影
 TODO :用户实体类
 *2019年11月28日  下午8:50:29
 */
public class User {
private int id;
private String username;
private String password;
private int sex;
private String hobby;
private String address;
private String degree;
/** 性别为男*/
public static final int MALE=1;
/** 性别为女*/
public static final int FEMALE=0;
@SuppressWarnings("javadoc")
public User(String name, String password, int sex, String hobby, String address, String degree) {
	this.username = name;
	this.password = password;
	this.sex = sex;
	this.hobby = hobby;
	this.address = address;
	this.degree = degree;
}
@SuppressWarnings("javadoc")
public User(int id, String name, String password, int sex, String hobby, String address, String degree) {
	super();
	this.setId(id);
	this.username = name;
	this.password = password;
	this.sex = sex;
	this.hobby = hobby;
	this.address = address;
	this.degree = degree;
}
@SuppressWarnings("javadoc")
public User() {
}
/**
 * @return 返回用户名
 */
public String getUserName() {
	return username;
}
/**
 * @param name 设置用户名
 */
public void setUserName(String name) {
	this.username = name;
}
/**
 * @return 获取密码
 */
public String getPassword() {
	return password;
}
/**
 * 设置密码
 * @param password
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * 获取性别
 *  MALE=1
 *  FEMALE=0
 * @return 返回性别的代号
 */
public int getSex() {
	return sex;
}
/**
 *  设置性别
 *  MALE=1
 *  FEMALE=0
 * @param sex
 */
public void setSex(int sex) {
	this.sex = sex;
}
/**
 * @return  返回爱好
 */
public String getHobby() {
	return hobby;
}
/**
 * 设置爱好
 * @param hobby
 */
public void setHobby(String hobby) {
	this.hobby = hobby;
}
/**
 * @return 返回地址
 */
public String getAddress() {
	return address;
}
/**
 * 设置地址
 * @param address
 */
public void setAddress(String address) {
	this.address = address;
}
/**
 * @return 返回用户的学历
 */
public String getDegree() {
	return degree;
}
/**
 * @param degree 设置学历
 */
public void setDegree(String degree) {
	this.degree = degree;
}
/**
 * @return 获取id
 */
public int getId() {
	return id;
}
/**
 * 设置id
 * @param id
 */
public void setId(int id) {
	this.id = id;
}
@Override
public String toString() {
	return "User [id=" + id + ", name=" + username + ", password=" + password + ", sex=" + sex + ", hobby=" + hobby
			+ ", address=" + address + ", degree=" + degree + "]";
}

}
