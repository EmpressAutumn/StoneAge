package com.yanny.age.stone.manual;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.GlStateManager;
import com.yanny.age.stone.ExampleMod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ItemWidget extends Widget {
    public static final String TYPE = "item";
    private static final int ITEM_WIDTH = 16;

    protected final ItemStack item;

    protected final float scale;
    protected final List<String> text;
    protected final int tmpWidth;
    protected final int tmpHeight;

    public ItemWidget(Widget parent, JsonObject object) {
        scale = Utils.getReal(object, "scale", 1.0, true).floatValue();
        tmpWidth = Utils.getInt(object, "width", DYNAMIC, true);
        tmpHeight = Utils.getInt(object, "height", DYNAMIC, true);

        String name = Utils.getString(object, "path", "minecraft:stone", false);
        item = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(name)));

        text = Lists.newArrayList();
        List<ITextComponent> list = item.getTooltip(ExampleMod.proxy.getClientPlayer(), ITooltipFlag.TooltipFlags.NORMAL);
        for(ITextComponent itextcomponent : list) {
            text.add(itextcomponent.getFormattedText());
        }
    }

    @Override
    public int getMinWidth(int height) {
        return Math.max(tmpWidth, Math.round(ITEM_WIDTH * scale));
    }

    @Override
    public int getMinHeight(int width) {
        return Math.max(tmpHeight, Math.round(ITEM_WIDTH * scale));
    }

    @Override
    public void drawBackgroundLayer(Screen screen, int mx, int my) {
        GlStateManager.pushTextureAttributes();
        GlStateManager.pushLightingAttributes();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(x, y, 0.0f);
        GlStateManager.scalef(scale, scale, 1.0f);
        GlStateManager.enableRescaleNormal();
        RenderHelper.enableGUIStandardItemLighting();
        mc.getItemRenderer().renderItemIntoGUI(item, 0, 0);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.popAttributes();
        GlStateManager.popAttributes();
    }

    @Override
    public void render(Screen screen, int mx, int my) {
        if (inBounds(mx, my)) {
            GuiUtils.drawHoveringText(text, mx, my, screen.width, screen.height, -1, mc.fontRenderer);
        }
    }
}
