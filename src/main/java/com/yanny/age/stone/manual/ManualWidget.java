package com.yanny.age.stone.manual;

import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class ManualWidget extends Widget implements IManual {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new Gson();

    private final Map<Integer, PageWidget> pages = new HashMap<>();
    private final Map<String, Integer> links = new HashMap<>();
    private final Map<String, JsonElement> constants = new HashMap<>();

    private int currentPage = 0;

    public ManualWidget(ResourceLocation resource, int width, int height) {
        JsonObject object;
        IResourceManager manager = Minecraft.getInstance().getResourceManager();

        this.width = width - Utils.MARGIN * 2;
        this.height = height - Utils.MARGIN * 2;

        try (InputStreamReader inputstream = new InputStreamReader(manager.getResource(resource).getInputStream()); Reader reader = new BufferedReader(inputstream)) {
            object = JSONUtils.fromJson(GSON, reader, JsonObject.class);
        } catch (IllegalArgumentException | IOException | JsonParseException jsonparseexception) {
            LOGGER.error("Couldn't parse data file {} - {}", resource, jsonparseexception);
            return;
        }

        if (object != null) {
            int page = 0;

            JsonArray array = Utils.getArray(object, "content");

            if (array == null) {
                return;
            }

            loadConstants(constants, object);

            for (JsonElement element : array) {
                if (!element.isJsonObject()) {
                    LOGGER.warn("Element {} is not an object", element.toString());
                }

                pages.put(page, new PageWidget(this, element.getAsJsonObject(), page));
                page++;
            }
        } else {
            LOGGER.error("Couldn't parse data file {}", resource);
        }
    }

    private void loadConstants(Map<String, JsonElement> constants, JsonObject object) {
        JsonObject items = Utils.getObject(object, "constants");

        if (items != null) {
            for (Map.Entry<String, JsonElement> item : items.entrySet()) {
                JsonElement element = item.getValue();

                if (element.isJsonPrimitive()) {
                    constants.put(item.getKey(), element);
                } else {
                    LOGGER.warn("Invalid element type in constants: {}", item.getKey());
                }
            }
        }
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void drawBackgroundLayer(Screen screen, int mx, int my) {
        pages.get(currentPage).drawBackgroundLayer(screen, mx, my);
    }

    @Override
    public void render(Screen screen, int mx, int my) {
        pages.get(currentPage).render(screen, mx, my);
    }

    @Override
    public void setPos(int x, int y) {
        super.setPos(x, y);
        pages.forEach((index, page) -> page.setPos(x + Utils.MARGIN, y + Utils.MARGIN));
    }

    @Override
    public boolean mouseClicked(int mx, int my, int key) {
        return pages.get(currentPage).mouseClicked(mx, my, key);
    }

    public void addLink(String key, int page) {
        links.put(key, page);
    }

    public void changePage(String key) {
        Integer page = links.get(key);

        if (page == null) {
            LOGGER.warn("Page key '{}' does not exists", page);
            return;
        }

        setCurrentPage(links.get(key));
    }

    @Override
    public JsonElement getConstant(String key) {
        return constants.get(key);
    }
}
