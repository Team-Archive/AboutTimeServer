package com.aboutTime.service.user;


import com.aboutTime.common.exception.ResourceNotFoundException;
import com.aboutTime.common.exception.user.DuplicateFieldValueException;
import com.aboutTime.domain.user.BaseUser;
import com.aboutTime.domain.user.UserRepository;
import com.aboutTime.dto.user.BaseUserDto;
import com.aboutTime.dto.user.SpecificUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BaseUserDto findUserById(Long userId) {
        return userRepository.findById(userId)
                .map(BaseUserDto::from)
                .orElseThrow(() -> new ResourceNotFoundException("아이디에 해당하는 유저가 존재하지 않습니다."));
    }

    public BaseUserDto findUserByEmail(String email) {
        return userRepository.findByUserMail(email)
                .map(BaseUserDto::from)
                .orElseThrow(() -> new ResourceNotFoundException("가입되지 않은 Email 입니다."));
    }

    public SpecificUserDto findSpecificUserById(Long userId) {
        BaseUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("아이디에 해당하는 유저가 존재하지 않습니다."));
        return SpecificUserDto.from(user);
    }

    public boolean existsEmail(String email) {
        return userRepository.findByUserMail(email).isPresent();
    }

    public boolean existsNickname(String nickname) {
        return userRepository.findByUserNickname(nickname).isPresent();
    }

    @Transactional
    public void deleteUser(Long userId) {
        log.info("User({})이 탈퇴했습니다", userId);
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUserNickname(Long userId, String nickname) {
        if (existsNickname(nickname)) {
            throw new DuplicateFieldValueException("nickname", nickname);
        }
        userRepository.updateNickName(userId, nickname);
    }
}
