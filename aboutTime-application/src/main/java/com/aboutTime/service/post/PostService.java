package com.aboutTime.service.post;

import com.aboutTime.common.exception.ResourceNotFoundException;
import com.aboutTime.domain.user.UserRepository;
import com.aboutTime.dto.post.PostDto;
import com.aboutTime.dto.post.PostSaveRequestDto;
import com.aboutTime.entity.post.PostRepository;
import com.aboutTime.entity.post.weather.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final WeatherService weatherService;

    /**
     * 특정 유저가 작성한 모든 Post 조회
     */
    public List<PostDto> getAllPostByAuthorId(Long authorId) {
        userRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("해당하는 유저가 없습니다."));

        return postRepository.findAllByAuthorId((authorId)).stream()
                .map(PostDto::specificForm)
                .toList();
    }

    /**
     * postId로 단건 조회
     */
    public PostDto getOnePostById(Long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 post가 없습니다."));

        return PostDto.specificForm(post);
    }

    /**
     * DB에 있는 모든 Post 조회
     */
    public List<PostDto> getAllPost() {
        return postRepository.findAll().stream()
                .map(PostDto::simpleForm)
                .toList();
    }

    /**
     * 새로운 Post 생성
     */
    @Transactional
    public void save(PostSaveRequestDto postRequestDto, Long authorId) {
        var user = userRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("해당하는 유저가 없습니다."));

        var post = postRequestDto.toEntity(user);
        var weather = weatherService.getWeatherByCity(user.getUserCity());

        var weatherConditionCode = Integer.parseInt(weather.getConditionCode());
        int hour = LocalTime.now().getHour();
        var weatherIconUrl = weatherService.getWeatherIconUrlByCode(WeatherData.fromConditionCode(weatherConditionCode, hour));

        post.updateWeather(weather.getTemperature(), weatherIconUrl);

        postRepository.save(post);

        Objects.requireNonNull(postRequestDto.getPostImages()).stream()
                .map(postImageDto -> postImageDto.toEntity(post))
                .forEach(post::addImage);
    }

    /**
     * Post 삭제
     */
    @Transactional
    public void delete(Long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 post가 없습니다."));
        postRepository.delete(post);
    }
}
