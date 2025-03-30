package com.aboutTime.entity.post.weather;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum WeatherData {
    HAZE(new int[]{711, 721, 731, 761, 762}),
    CLEARNESS(new int[]{800}),
    DRIZZLING(new int[]{300, 301, 302, 310, 311, 312, 313, 314, 321}),
    SNOW(new int[]{600, 601, 611, 612, 613, 615, 616, 620, 621, 622}),
    FOG(new int[]{701, 741}),
    CLOUDY(new int[]{801, 803, 804}),
    PARTLY_CLOUDY(new int[]{802}),
    BLIZZARD(new int[]{602}),
    WIND(new int[]{751, 771, 781}),
    ICE_RAIN(new int[]{511}),
    THUNDERSTORM(new int[]{200, 201, 202, 210, 211, 212, 221, 230, 231, 232}),
    RAIN(new int[]{500, 501, 520, 521, 522, 531}),
    DOWNPOUR(new int[]{502, 503, 504});

    private final int[] conditionCodes;

    private static final Map<Integer, WeatherData> codeToIconMap = new HashMap<>();

    static {
        for (WeatherData icon : values()) {
            for (int code : icon.conditionCodes) {
                codeToIconMap.put(code, icon);
            }
        }
    }

    WeatherData(int[] conditionCodes) {
        this.conditionCodes = conditionCodes;
    }

    public static String fromConditionCode(int code, int hour) {
        WeatherData icon = codeToIconMap.get(code);

        if (icon == null) {
            throw new IllegalArgumentException("Unknown weather condition code: " + code);
        }

        String iconName = icon.name();

        // 낮과 밤의 구분이 필요한 아이콘
        if (icon == CLEARNESS && (hour >= 18 || hour < 6)) {
            iconName = "CLEARNESS_NIGHT";
        } else if (icon == DRIZZLING && (hour >= 18 || hour < 6)) {
            iconName = "DRIZZLING_NIGHT";
        } else if (icon == PARTLY_CLOUDY && (hour >= 18 || hour < 6)) {
            iconName = "PARTLY_CLOUDY_NIGHT";
        }

        return iconName;
    }

}
