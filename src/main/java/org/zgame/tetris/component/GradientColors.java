package org.zgame.tetris.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SBT-Nikiforov-MO on 03.08.2015.
 */
public class GradientColors {

    private static final Logger log = LoggerFactory.getLogger(GradientColors.class);

    private static final Map<Integer, Color> COLOR_MAP_LIGHT;
    private static final Map<Integer, Color> COLOR_MAP_DARK;
    public static final int GRADIENT_COLORS_COUNT;

    static {
        Map<Integer, Color> lightMap = new HashMap<>();
        Map<Integer, Color> darkMap = new HashMap<>();

        lightMap.put(1, new Color(0, 0, 205));
        darkMap.put(1, new Color(0, 0, 139));

        lightMap.put(2, new Color(0, 205, 100));
        darkMap.put(2, new Color(0, 139, 0));

        lightMap.put(3, new Color(205, 0, 0));
        darkMap.put(3, new Color(139, 0, 0));

        lightMap.put(4, new Color(218, 112, 214));
        darkMap.put(4, new Color(199, 21, 133));

        lightMap.put(5, new Color(255, 255, 0));
        darkMap.put(5, new Color(255, 165, 0));

        lightMap.put(6, new Color(42, 42, 42));
        darkMap.put(6, new Color(26, 26, 26));

        lightMap.put(7, new Color(255, 165, 0));
        darkMap.put(7, new Color(255, 120, 0));

        lightMap.put(8, new Color(0, 255, 0));
        darkMap.put(8, new Color(50, 205, 50));

        COLOR_MAP_LIGHT = Collections.unmodifiableMap(lightMap);
        COLOR_MAP_DARK = Collections.unmodifiableMap(darkMap);

        GRADIENT_COLORS_COUNT = COLOR_MAP_DARK.size();
    }

    public static Color getLightColorByNum(int num) {
        Color result = COLOR_MAP_LIGHT.get(num);
        if (result == null) {
            log.error("Color is NULL!!! num: {}", num);
        }
        return result;
    }

    public static Color getDarkColorByNum(int num) {
        Color result = COLOR_MAP_DARK.get(num);
        if (result == null) {
            log.error("Color is NULL!!! num: {}", num);
        }
        return result;
    }
}
