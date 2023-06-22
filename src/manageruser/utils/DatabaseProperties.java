/**
 * Copyright (C) 2020 Luvina Software
 * DatabaseProperties.java Mar 15, 2020 author Lê Minh 
 */
package manageruser.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Lớp này dùng để: Đọc file database.properties và lấy ra các key-value tương ứng
 * @author: Lê Minh
 */
public class DatabaseProperties {
	// lưu các cặp <key, value> trong file properties
	private static Map<String, String> mapDBProperties = new HashMap<String, String>();
	// Bắt đầu khối stactic
	static {
		// Bắt đầu khối try
		try {
			// tạo đối tượng kiểu Properties
			Properties properties = new Properties();
			// đọc file properties
			properties.load(new InputStreamReader(
					DatabaseProperties.class.getClassLoader().getResourceAsStream(Constant.PROPERTIES_DATABASE_PATH),
					"UTF-8"));
			// lưu các giá trị key trong file properties
			Enumeration<?> enumeration = properties.propertyNames();
			// true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
			while (enumeration.hasMoreElements()) {
				// key = key tiếp theo
				String key = (String) enumeration.nextElement();
				// lấy value tương ứng với key
				String value = properties.getProperty(key);
				// thêm vào list
				mapDBProperties.put(key, value);
				// Kết thúc while
			}
			// Kết thúc khối try
		} catch (IOException e) {
			// Ghi log lỗi
			System.out.println("[DatabaseProperties]" + e.getMessage());
			// Kết thúc catch
		}
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để: Lấy value tương ứng với key trong file properties
	 * @param key : tên key trong file properties
	 * @return: Trả về value tương ứng với key
	 */
	public static String getString(String key) throws IOException{
		// Lấy giá trị của key tương ứng
		String value = mapDBProperties.get(key);
		// Trả về giá trị value
		return value;
		// kết thúc phương thức
	}
	// Kết thúc class
}
