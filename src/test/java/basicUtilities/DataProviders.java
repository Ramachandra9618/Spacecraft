package basicUtilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DataProviders {
    //DataProvider 1
//    @DataProvider(name = "testData")
    public String[][] getData() throws IOException {

        String path = "src/test/resources/module.csv";

        ExcelUtils xlUtil = new ExcelUtils(path); // here creating object for utility

        int totalRows = xlUtil.getRowCount("Sheet1");
        int totalCols = xlUtil.getCellCount("Sheet1", 1);

        String[][] loginData = new String[totalRows][totalCols]; // created for two dimension array which can store

        for (int i = 1; i <= totalRows; i++)  // 1 // read the data from x1 storing in two deminsional array
        {
            for (int j = 0; j < totalCols; j++) {
                loginData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);
                xlUtil.getCellData("sheet1", i,j);
            }
        }
        return loginData;// returning two dimension array
    }

    public void readData() {
        String csvFile =  "src/test/resources/module.csv";


        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] row;
            int targetRow = 2;  // Example: 3rd row (index starts from 0)
            int targetColumn = 1; // Example: 2nd column (index starts from 0)
            int currentRow = 0;

            while ((row = reader.readNext()) != null) {
                // Check if we are at the target row
                if (currentRow == targetRow) {
                    // Output the specific cell data
                    System.out.println("Data from the target cell: " + row[targetColumn]);
                    break; // We found the target cell, exit the loop
                }
                currentRow++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
        public static void main(String[] args) {
        DataProviders dataProviders = new DataProviders();

        try {
            dataProviders.readData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}



