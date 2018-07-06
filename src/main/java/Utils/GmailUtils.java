package Utils;

import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

class EmailAuthenticator extends javax.mail.Authenticator {
    private String login;
    private String password;

    public EmailAuthenticator(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(login, password);
    }
}

public class GmailUtils {
    String IMAP_AUTH_EMAIL = "";
    String IMAP_AUTH_PWD = "";
    String IMAP_Server = "imap.gmail.com";
    String IMAP_Port = "993";

    Store store = null;

    public GmailUtils(String email, String pass) {
        IMAP_AUTH_EMAIL = email;
        IMAP_AUTH_PWD = pass;
        Properties properties = new Properties();
        properties.put("mail.debug", "false");
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.imap.port", IMAP_Port);

        Authenticator auth = new EmailAuthenticator(IMAP_AUTH_EMAIL,
                IMAP_AUTH_PWD);
        Session session = Session.getDefaultInstance(properties, auth);
        session.setDebug(false);
        try {
            store = session.getStore();
            store.connect(IMAP_Server, IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);
        } catch (NoSuchProviderException e) {
            System.err.println(e.getMessage());
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Message> getEmailsBySubject(String subject) {
        try {
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            if (inbox.getMessageCount() == 0)
                return null;

            Message[] messages = inbox.getMessages();
            ArrayList<Message> results = new ArrayList<>();
            for (Message msg : messages)
                if (msg.getSubject().toLowerCase().contains(subject.toLowerCase()))
                    results.add(msg);
            return results;
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Message getLastMessage() {
        try {
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            int count = inbox.getMessageCount();
            if (count == 0)
                return null;
            return inbox.getMessage(count);
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String getMessageText(Message message) {
        try {
            Multipart mp = (Multipart) message.getContent();

            String messageText = "";
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart bp = mp.getBodyPart(i);
                if (bp.getFileName() == null)
                    messageText = String.format("%s\n%s", messageText, bp.getContent());
            }
            return messageText;
        } catch (NoSuchProviderException e) {
            System.err.println(e.getMessage());
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return "";
    }
}
