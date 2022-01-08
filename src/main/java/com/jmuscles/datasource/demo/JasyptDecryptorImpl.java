/**
 * 
 */
package com.jmuscles.datasource.demo;

import java.util.Properties;
import java.util.Set;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.properties.EncryptableProperties;
import com.jmuscles.datasource.jasypt.JasyptDecryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author manish goel
 *
 */
@Component
public class JasyptDecryptorImpl implements JasyptDecryptor {

	private static Logger logger = LoggerFactory.getLogger(JasyptDecryptorImpl.class);

	@Override
	public Properties decrypt(Properties props) {

		Properties newProps = null;
		String jasyptAlgorithm = (String) props.remove("jasypt-algorithm");
		String jasyptPassword = (String) props.remove("jasypt-password");

		if (StringUtils.hasText(jasyptPassword)) {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			if (StringUtils.hasText(jasyptAlgorithm)) {
				encryptor.setAlgorithm(jasyptAlgorithm);
			}
			encryptor.setIvGenerator(new RandomIvGenerator());
			encryptor.setPassword(jasyptPassword);
			EncryptableProperties encryptableProperties = new EncryptableProperties(encryptor);
			encryptableProperties.putAll(props);

			newProps = new Properties();

			Set<Object> keys = encryptableProperties.keySet();
			for (Object key : keys) {
				try {
					Object value = encryptableProperties.get(key);
					if (value == null) {
						value = props.get(key);
					}
					newProps.put(key, value);
				} catch (RuntimeException e) {
					logger.error("Error while decrypting property : " + key);
					throw e;
				}
			}
		} else {
			logger.info("jasyptPassword is not present, properties can not be decrypted.");
			newProps = props;
		}

		return newProps;
	}

}
