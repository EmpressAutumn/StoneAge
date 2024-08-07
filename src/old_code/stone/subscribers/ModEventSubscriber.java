package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.ExampleMod;
import com.yanny.age.stone.compatibility.top.TopCompatibility;
import com.yanny.age.stone.config.ConfigHelper;
import com.yanny.age.stone.config.ConfigHolder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

    @SubscribeEvent
    public static void init(@Nonnull FMLCommonSetupEvent event) {
        ExampleMod.proxy.init();
    }

    @SubscribeEvent
    public static void registerTOP(@Nonnull InterModEnqueueEvent event) {
        TopCompatibility.register();
    }

    @SubscribeEvent
    public static void onModConfigEvent(@Nonnull ModConfig.ModConfigEvent event) {
        final ModConfig config = event.getConfig();

        if (!config.getModId().equals(Reference.MODID)) {
            return;
        }

        // Rebake the configs when they change
        if (config.getSpec() == ConfigHolder.CLIENT_SPEC) {
            ConfigHelper.bakeClient();
        } else if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
            ConfigHelper.bakeServer();
        }
    }
}
