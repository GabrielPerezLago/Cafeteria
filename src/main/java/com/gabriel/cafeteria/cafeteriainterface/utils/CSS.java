package com.gabriel.cafeteria.cafeteriainterface.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSS {
    private static CSS instance;

    private List<String> css = Arrays.asList(
            "/com/gabriel/cafeteria/cafeteriainterface/css/index.css",
            "/com/gabriel/cafeteria/cafeteriainterface/css/colorize.css",
            "/com/gabriel/cafeteria/cafeteriainterface/css/text.css",
            "/com/gabriel/cafeteria/cafeteriainterface/css/border_radius.css",
            "/com/gabriel/cafeteria/cafeteriainterface/css/card.css"
    );

    private CSS() {}

    public static CSS getInstance() {
        if (instance == null) {
            instance = new CSS();
        }
        return instance;
    }

    public List<String> GET() {
        return css;
    }



}
