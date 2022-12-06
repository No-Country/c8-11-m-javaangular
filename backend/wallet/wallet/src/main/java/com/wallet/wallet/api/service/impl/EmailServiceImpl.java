package com.wallet.wallet.api.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.wallet.wallet.api.service.IEmailService;
import com.wallet.wallet.domain.enums.EMessageCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {

    private final MessageSource messenger;
    private final SendGrid sendGrid;

    private final String ENDPOINT = "mail/send";

    @Value("${api.template.welcome.id}")
    private String TEMPLATE_ID;

    @Value("${app.email}")
    private String APP_EMAIL;

    @Value("${app.subject.email}")
    private String SUBJECT;

    @Value("${app.type.email}")
    private String TYPE;

    @Value("${app.content.message.email}")
    private String CONTENT_MESSAGE;

    @Override
    public void sendEmail(String toEmail, String username) {
        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint(ENDPOINT);
            request.setBody(setTemplate(toEmail, TEMPLATE_ID, username).build());

            sendGrid.api(request);
        } catch (Exception e) {
            throw new RuntimeException(messenger.getMessage(EMessageCode.ERROR_SENDING_EMAIL.name(),
                    new Object[] { toEmail }, Locale.getDefault()));
        }
    }

    private Mail setTemplate(String toEmail, String templateId, String username) {
        Mail mail = new Mail(new Email(APP_EMAIL),
                SUBJECT,
                new Email(toEmail),
                new Content(TYPE, CONTENT_MESSAGE));

        Personalization personalization = new Personalization();
                personalization.addDynamicTemplateData("username", username);

        mail.setTemplateId(TEMPLATE_ID);

        return mail;
    }

}
