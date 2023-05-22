package com.example.springbootlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLearningApplication {
	//		LocalDate date1 = LocalDate.of(2023,5,4);
//		LocalDateTime date2 = LocalDateTime.now();
//		LocalDateTime date3 = LocalDateTime.of(2002, Month.APRIL, 1, 10, 25);
//
//		long a = ChronoUnit.YEARS.between(date3, date2);
//		System.out.println(a);
//		Calendar c = Calendar.getInstance();
//		c.add(Calendar.WEEK_OF_YEAR, 1);
//		System.out.println(c.getTime());

//		BigDecimal num1 = new BigDecimal("99999999999999999999999999999999999");
//		BigDecimal num2 = new BigDecimal("66666666666666666666666666666666666");
//
//		System.out.println(num1.add(num2));
//		System.out.println(num1.add(num2.negate()));
//		System.out.println(num1.multiply(num2));
//		System.out.println(num1.divide(num2));
//		System.out.println(num1.min(num2));

//		String input = "abc";
//		MessageDigest md = MessageDigest.getInstance("SHA-256");
//		byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
//		StringBuilder hexString = new StringBuilder();
//		for (byte b : hash) {
//			String hex = Integer.toHexString(0xff & b);
//			if (hex.length() == 1) hexString.append('0');
//			hexString.append(hex);
//		}
//		String sha256 = hexString.toString();
//		System.out.println("SHA-256 hash of \"" + input + "\" is: " + sha256);

//		String salt = "tipcEncypt903106";
//		BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
//
//			basicTextEncryptor.setPassword(salt);

//		/**
//		 * 明文加密
//		 * @param plaintext
//		 * @return
//		 */
//		public static String encode(String plaintext){
//			System.out.println("明文字符串：" + plaintext);
//			String ciphertext = basicTextEncryptor.encrypt(plaintext);
//			System.out.println("加密后字符串：" + ciphertext);
//			return ciphertext;
//		}

	//		/**
//		 * 解密
//		 * @param ciphertext
//		 * @return
//		 */
//		String ciphertext = "8RGIZMLOMHNwE459UU0YKA==";
//		public static String decode(String ciphertext){
//			System.out.println("加密字符串：" + );
//			ciphertext = "ENC(" + ciphertext + ")";
//			if (PropertyValueEncryptionUtils.isEncryptedValue(ciphertext)){
//				String plaintext = PropertyValueEncryptionUtils.decrypt(ciphertext,basicTextEncryptor);
//				System.out.println("解密后的字符串：" + plaintext);
//			}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearningApplication.class, args);
	}

}
