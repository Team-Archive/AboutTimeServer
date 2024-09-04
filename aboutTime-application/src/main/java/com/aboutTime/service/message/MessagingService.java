package com.aboutTime.service.message;

import com.aboutTime.dto.user.BaseUserDto;
import org.springframework.stereotype.Service;

public interface MessagingService {
    void sendUserRegisterMessage(BaseUserDto user, String registerType);
}
