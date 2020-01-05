package com.yanny.age.stone.manual;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

class WidgetFactory {
    private static final Map<String, FactoryFunction<JsonObject, IManual, Widget>> FACTORY = new HashMap<>();

    static {
        FACTORY.put(TextWidget.TYPE, TextWidget::new);
        FACTORY.put(TitleWidget.TYPE, TitleWidget::new);
        FACTORY.put(ItemWidget.TYPE, ItemWidget::new);
        FACTORY.put(HorizontalLayoutWidget.TYPE, HorizontalLayoutWidget::new);
        FACTORY.put(VerticalLayoutWidget.TYPE, VerticalLayoutWidget::new);
        FACTORY.put(ImageWidget.TYPE, ImageWidget::new);
    }

    static Widget getWidget(String name, JsonObject object, IManual manual) {
        return FACTORY.get(name).apply(object, manual);
    }

    interface FactoryFunction<A, B, R> {
        R apply(A a, B b);
    }
}
