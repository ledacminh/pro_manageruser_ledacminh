/**
 * Copyright (C) 2020 Luvina Software
 * MessageErrorProperties.java Mar 15, 2020 author Lê Minh 
 */
package manageruser.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Lớp này dùng để: Lấy ra các giá trị tương ứng với key trong file  messageError.Properties 
 * @author: Lê Minh
 */
public class MessageErrorProperties {
	// lưu các cặp <key, value> trong file properties
	private static Map<String, String> mapMSGERProperties = new HashMap<String, String>();
	// Bắt đầu khối stactic
	static {
		// Bắt đầu khối try
		try {
			// tạo đối tượng kiểu Properties
			Properties properties = new Properties();

			// đọc file properties
			properties.load(new InputStreamReader(MessageErrorProperties.class.getClassLoader()
					.getResourceAsStream(Constant.PROPERTIES_MESSAGE_ERROR_PATH), "UTF-8"));
			// lưu các giá trị key trong file properties
			Enumeration<?> enumeration = properties.propertyNames();
			// true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
			// Bắt đầu vòng while
			while (enumeration.hasMoreElements()) {
				// key = key tiếp theo
				String key = (String) enumeration.nextElement();
				// lấy value tương ứng với key
				String value = properties.getProperty(key);
				// thêm vào list
				mapMSGERProperties.put(key, value);
				// Kết thúc vòng while
			}
			// Kết thúc khối try
		} catch (IOException e) {
			// Ghi log lỗi
			System.out.println("[MessageErrorProperties]" + e.getMessage());
			// Kết thúc khối catch
		}
		// Kết thúc khối stactic
	}

	/**
	 * Phương thức dùng để: Lấy value tương ứng với key trong file properties
	 * @param key : tên key trong file properties
	 * @return: Trả về value tương ứng với key
	 */
	public static String getString(String key) {
		// Khai báo biến value để lấy giá trị tương ứng với key.
		String value = "";
		// Bắt đầu khối try
		try {
			// Lấy giá trị tương ứng với key
			value = mapMSGERProperties.get(key);
			// Kết thúc try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[MessageErrorProperties].[getString]");
			// Kết thúc catch
		}
		// Trả về kết quả
		return value;
		// Kết thúc phương thức
	}
	// Kết thúc class
}
