package com.wallet.wallet.api.service;

import com.sendgrid.Response;

public interface IEmailService {
    
    Response sendEmail(String toEmail);
}
