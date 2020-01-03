package com.yanny.age.stone.manual;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.gui.screen.Screen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class PageWidget extends Widget implements IPage {
    private static final Logger LOGGER = LogManager.getLogger();
    private final List<Widget> widgets = new ArrayList<>();
    private final IManual manual;
    private final int page;

    public PageWidget(ManualWidget manual, JsonArray array, int page) {
        this.width = manual.width;
        this.height = manual.height;
        this.manual = manual;
        this.page = page;

        for (JsonElement element : array) {
            if (!element.isJsonObject()) {
                LOGGER.warn("Element {} is not an object", element.toString());
                continue;
            }

            JsonObject object = element.getAsJsonObject();
            String type = Utils.getString(manual, object, "type", null, false);

            if (type == null) {
                continue;
            }

            widgets.add(WidgetFactory.getWidget(type, object, this, manual));
        }
    }

    @Override
    public void drawBackgroundLayer(Screen screen, int mx, int my) {
        for (Widget widget : widgets) {
            if (widget.visible) {
                widget.drawBackgroundLayer(screen, mx, my);
            }
        }
    }

    @Override
    public void render(Screen screen, int mx, int my) {
        for (Widget widget : widgets) {
            if (widget.visible) {
                widget.render(screen, mx, my);
            }
        }
    }

    @Override
    public boolean mouseClicked(int mx, int my, int key) {
        for (Widget widget : widgets) {
            if (widget.inBounds(mx, my) && widget.mouseClicked(mx, my, key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void setPos(int x, int y) {
        super.setPos(x, y);
        int pos = y;

        Utils.resizeVLayout(this, widgets);

        for (Widget widget : widgets) {
            widget.setPos(x, pos);
            pos += widget.height;
        }
    }

    public void addLink(String key) {
        manual.addLink(key, page);
    }
}
