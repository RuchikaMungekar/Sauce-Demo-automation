package dataProviderutility;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import utils.ExcelReader;

public class LoginDataProvider {

	@DataProvider(name = "loginData")
	public Object[][] loginData() {
		String path = "src/test/resources/TestData/Credentials.xlsx";
		List<Map<String, String>> data = ExcelReader.readExcelData(path, "LoginData");
		System.out.println(data);
		Object[][] testData = new Object[data.size()][3];
		System.out.println();
		for (int i = 0; i < data.size(); i++) {
			System.out.println("Row map: " + data.get(i)); // Print full row map
			testData[i][0] = data.get(i).get("username");
			testData[i][1] = data.get(i).get("password");
			testData[i][2] = data.get(i).get("testName");
			System.out.println("Row " + i + ": Username = " + testData[i][0] + ", Password = " + testData[i][1]);
		}

		return testData;
	}
}
