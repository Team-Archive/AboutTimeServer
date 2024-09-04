package com.aboutTime.service.user;

import com.aboutTime.common.exception.common.DuplicateResourceException;
import com.aboutTime.domain.user.BaseUser;
import com.aboutTime.domain.user.UserInfo;
import com.aboutTime.domain.user.UserRepository;
import com.aboutTime.dto.user.BaseUserDto;
import com.aboutTime.dto.user.OAuthRegisterRequestDto;
//import com.aboutTime.service.message.MessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserRegisterService {
    private final UserRepository userRepository;
    //private final MessagingService messagingService;      // 추후 메시지(slack) 필요시 수정
    private final Logger log = LoggerFactory.getLogger(UserRegisterService.class);

    public UserRegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
        //this.messagingService = messagingService;
    }

    /*

    @Transactional
    public UserInfo registerUser(PasswordRegisterRequestDto registerRequest) {
        try {
            BaseUser user = userRepository.save(registerRequest.toUserEntity());
            messagingService.sendUserRegisterMessage(BaseUserDto.from(user), PasswordUser.PASSWORD_TYPE);
            return user.convertToUserInfo();
        } catch (DataIntegrityViolationException e) {
            log.error("이메일 또는 닉네임이 중복되었습니다.", e);
            throw new DuplicateResourceException("이메일 또는 닉네임이 중복되었습니다. 중복을 다시 확인해주세요.");
        }
    }
    */

    @Transactional
    public UserInfo registerUser(OAuthRegisterRequestDto registerRequest) {
        try {
            BaseUser user = userRepository.save(registerRequest.toUserEntity());
            String oAuthProvider = registerRequest.getProvider().getRegistrationId();
            //messagingService.sendUserRegisterMessage(BaseUserDto.from(user), oAuthProvider);
            return user.convertToUserInfo();
        } catch (DataIntegrityViolationException e) {
            log.error("이메일 또는 닉네임이 중복되었습니다.", e);
            throw new DuplicateResourceException("이메일 또는 닉네임이 중복되었습니다. 중복을 다시 확인해주세요.");
        }
    }
}
