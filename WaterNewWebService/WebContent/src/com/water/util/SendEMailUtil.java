package com.water.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEMailUtil {

	public String userName;
	public String password;
	public String domain;
	public String port;
	public String protocol;
	public String host;
	public String auth;
	public String from;
	public String socketFactoryPort;

	ResourceBundle rb = ResourceBundle.getBundle("resources/mail");

	public SendEMailUtil() {

		this.userName = rb.getString("mail.userName");
		this.password = rb.getString("mail.userpassword");
		this.port = rb.getString("mail.port");
		this.from = rb.getString("mail.from");
		this.host = rb.getString("mail.host");
		this.protocol = rb.getString("mail.protocol");
		this.auth = rb.getString("mail.auth");
		this.socketFactoryPort = rb.getString("mail.socketFactory.port");
		System.out.println(" From : " + from + ", host : " + host
				+ ", userName : " + userName + ", password : " + password);

	}

	public void sendMail(String to, String subject, String body,
			String attachment, byte[] data) {

		try {
			//Properties props = new Properties();

			/*props.setProperty("mail.transport.protocol", protocol);
			props.setProperty("mail.host", host);
			props.setProperty("mail.smtp.from", from);
			props.put("mail.smtp.auth", auth);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.socketFactory.port", socketFactoryPort);
			props.put("mail.smtp.starttls.enable", "true");*/
			
			final String username = "tn.watercon@gmail.com";
			final String password = "tnwatercon";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");`
			
			

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(userName,
									password);
						}
					});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			BodyPart part1 = new MimeBodyPart();
			part1.setText(body);
			Multipart multiPart = new MimeMultipart();
			if (attachment != null && !"".equals(attachment)) {

				File file = new File(attachment);
				if (file.exists()) {
					BodyPart part2 = new MimeBodyPart();
					DataSource attachment1 = new FileDataSource(file);
					part2.setDataHandler(new DataHandler(attachment1));
					part2.setFileName(file.getName());

					multiPart.addBodyPart(part2);
					System.out
							.println("Message with attachment sent successfully....");
				} else {
					System.out.println("Attachement is missing ...");
				}
			}

			part1.setDataHandler(new DataHandler(new HTMLDataSource(body)));
			multiPart.addBodyPart(part1);
			message.setContent(multiPart);

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class HTMLDataSource implements DataSource {
		private String html;

		public HTMLDataSource(String htmlString) {
			html = htmlString;
		}

		// Return html string in an InputStream.
		// A new stream must be returned each time.
		public InputStream getInputStream() throws IOException {
			if (html == null)
				throw new IOException("Null HTML");
			return new ByteArrayInputStream(html.getBytes());
		}

		public OutputStream getOutputStream() throws IOException {
			throw new IOException("This DataHandler cannot write HTML");
		}

		public String getContentType() {
			return "text/html";
		}

		public String getName() {
			return "JAF text/html dataSource to send e-mail only";
		}
	}

}
