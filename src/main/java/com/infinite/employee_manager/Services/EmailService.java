package com.infinite.employee_manager.Services;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to,String subject, String text) throws MessagingException, UnsupportedEncodingException{
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper email = new MimeMessageHelper(message,false,"UTF-8");


        email.setFrom(new InternetAddress("notofficial906@gmail.com","Employee management system support"));
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text,false);

        mailSender.send(message);
    }
}
