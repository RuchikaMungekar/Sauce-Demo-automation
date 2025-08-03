package dataProviderutility;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import utils.ExcelReader;

public class CheckoutDataProvider {

    @DataProvider(name = "CheckoutDetails")
    public Object[][] CheckoutData() {
        String path = "src/test/resources/TestData/Credentials.xlsx";
        List<Map<String, String>> checkData = ExcelReader.readExcelData(path, "CheckoutDetails");
        System.out.println("CheckData: " + checkData);

        Object[][] checkoutData = new Object[checkData.size()][checkData.get(0).size()];

        for (int i = 0; i < checkData.size(); i++) {
            Map<String, String> row = checkData.get(i);
            System.out.println("Row " + i + ": " + row);

            checkoutData[i][0] = row.get("firstName");
            checkoutData[i][1] = row.get("lastName");
            checkoutData[i][2] = row.get("Zipcode");

            System.out.println("Row " + i + " - firstName: " + checkoutData[i][0] + ", lastName: " + checkoutData[i][1] + ", PostalCode: " + checkoutData[i][2]);
        }

        return checkoutData;
    }
}
