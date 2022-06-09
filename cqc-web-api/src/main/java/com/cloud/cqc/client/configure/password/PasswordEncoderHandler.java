package com.cloud.cqc.client.configure.password;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import com.cloud.cqc.framework.core.utils.RandomUtils;

/**
 * 密码处理器
 * 
 * @author joy.zhou
 * @date 2017年9月28日
 * @version 1.0
 */
public class PasswordEncoderHandler {

	private MessageDigestPasswordEncoder passwordEncoder;

	public PasswordEncoderHandler(MessageDigestPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * 生成加密密码
	 * 
	 * @param password
	 *            原密码
	 * @return
	 */
	public SaltPassword encodePassword(String password) {
		String salt = RandomUtils.randomNumbers(6);
		String rawPassword = passwordEncoder.encodePassword(password, salt);
		return new SaltPassword(salt, rawPassword);
	}

	public static class SaltPassword {

		private String salt;
		private String password;

		public SaltPassword(String salt, String password) {
			this.salt = salt;
			this.password = password;
		}

		public String getSalt() {
			return salt;
		}

		public void setSalt(String salt) {
			this.salt = salt;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}

}
