/**
 * 
 */
package com.jmuscles.datasource.demo;

import com.jmuscles.datasource.jasypt.JasyptUtil;

/**
 * @author manish goel
 *
 */
public class JasyptOperatorDefaultImplDemo {
	public static void main(String[] args) {
		System.out.println(JasyptUtil.encryptProperties("PBEWithMD5AndDES", "my-secret", "password"));
	}

}
