package com.chernov.customer.service.Service;


import com.chernov.customer.service.Dto.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImplementation implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    @Autowired
    public EmailServiceImplementation(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmailAlert(EmailDetails emailDetails) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(emailSender);
            message.setTo(emailDetails.getRecipient());
            message.setText(emailDetails.getMessageBody());
            message.setSubject(emailDetails.getSubject());

            mailSender.send(message);
            System.out.println("Email sent successfully");
        }catch (MailException e){
            throw new RuntimeException(e);
        }
    }
}
