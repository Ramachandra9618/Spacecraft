package testcases;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {




    public static void sendEmail(String recipient, String subject, String messageBody) throws EmailException, IOException {
        Properties prop = new Properties();

        FileInputStream files = null;
        try {
            files = new FileInputStream("configurations/config.properties");
        } catch (Exception e) {
            System.out.println(e);
        }
        prop.load(files);
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(prop.getProperty("gmail"), prop.getProperty("gmail.app.password")));
        email.setSSLOnConnect(true);
        email.setFrom(prop.getProperty("gmail"));
        email.setSubject(subject);
        email.setMsg(messageBody);
        email.addTo(recipient);
        email.send();

    }

    public static void main(String[] args) throws EmailException, IOException {
        String recipient = "nitishk.hlc@homelane.com";
        String subject = "Test Email from Selenium";
        String messageBody = "This is a test email sent from a Selenium automation script.";

        sendEmail(recipient, subject, messageBody);
    }
}

