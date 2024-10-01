package basicUtilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import testcases.BaseClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotCapture extends BaseClass {
    public static String captureScreen(String testName) {
        if (driver == null) {
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + testName + "_" + timeStamp + ".png";
        try {
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(targetFilePath));
            return targetFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
