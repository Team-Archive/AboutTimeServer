package com.aboutTime.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class EmojiStatisticsResponse {

    private Map<String, Integer> status;

}
