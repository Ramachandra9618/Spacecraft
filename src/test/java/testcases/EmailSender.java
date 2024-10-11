package testcases;

import org.apache.commons.mail.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
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

    public static void sendEmailWithAttachment(String toEmail, String subject, String messageBody, File attachmentPath) {
        try {
            // Create the attachment
            EmailAttachment attachment = new EmailAttachment();
            System.out.println(String.valueOf(attachmentPath));
            attachment.setPath(String.valueOf(attachmentPath));
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("File attachment");
            attachment.setName("Attachment");
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthentication("ramachanndra2001@gmail.com", "dbcwgvisvdcuefvu");
            email.setSSLOnConnect(true);

            email.setFrom("ramachanndra2001@gmail.com");
            email.addTo(toEmail);
            email.setSubject(subject);
            email.setMsg(messageBody);
            email.attach(attachment);
            email.send();

            System.out.println("Email sent successfully with attachment!");

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmailWithAttachments(String toEmail, String subject, String messageBody, List<File> attachments) {
        try {
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthentication("ramachanndra2001@gmail.com", "dbcwgvisvdcuefvu");
            email.setSSLOnConnect(true);

            email.setFrom("ramachanndra2001@gmail.com");
            email.addTo(toEmail);
            email.setSubject(subject);
            email.setMsg(messageBody);

            // Attach multiple files
            for (File attachmentPath : attachments) {
                EmailAttachment attachment = new EmailAttachment();
                System.out.println(String.valueOf(attachmentPath));
                attachment.setPath(String.valueOf(attachmentPath));
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("File attachment");
                attachment.setName(attachmentPath.getName()); // Set the actual file name
                email.attach(attachment);
            }

            email.send();
            System.out.println("Email sent successfully with attachments!");

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static File getLatestFile(String folderPath, String fileExtension, String fileName) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(fileExtension));

        if (files == null || files.length == 0) {
            return null;
        }
        File latestFile = files[0];
        for (File file : files) {
            // Check if the current file is newer than the latestFile
            if (file.lastModified() > latestFile.lastModified() && file.getName().contains(fileName)) {
                latestFile = file;
            }
        }

        return latestFile; // Return the latest file
    }
}

