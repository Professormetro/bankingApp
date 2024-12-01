package com.chernov.customer.service.Service;

import com.chernov.customer.service.Dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
