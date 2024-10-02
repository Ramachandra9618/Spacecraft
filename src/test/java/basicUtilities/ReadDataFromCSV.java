package basicUtilities;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadDataFromCSV {
    String path;
    String sheetName;
    public ReadDataFromCSV(String path, String sheetName){
        this.path = path;
        this.sheetName=sheetName;
    }
    public String getData(int targetRow, int targetColumn){
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            String[] row;
            int currentRow = 0;

            while ((row = reader.readNext()) != null) {
                if (currentRow == targetRow) {
//                    System.out.println(row[targetColumn]);
                    return row[targetColumn];
                }
                currentRow++;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        ReadDataFromCSV readDataFromCSV = new ReadDataFromCSV("src/test/resources/module.csv", "sheet1");
      String r=  readDataFromCSV.getData(1,1);
        System.out.println(r);
    }



    public Object[][] readCSV(String path) throws IOException {
        List<String[]> dataList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] row;
            boolean headerSkipped = false;
            while ((row = reader.readNext()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                dataList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] dataArray = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i] = dataList.get(i);
        }
        return dataArray;
    }
}
