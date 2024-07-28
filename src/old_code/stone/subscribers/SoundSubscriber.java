package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@ObjectHolder(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoundSubscriber {
    public static final SoundEvent mammoth_ambient = null;
    public static final SoundEvent mammoth_hit = null;
    public static final SoundEvent mammoth_death = null;
    public static final SoundEvent saber_tooth_tiger_ambient = null;
    public static final SoundEvent saber_tooth_tiger_hit = null;
    public static final SoundEvent saber_tooth_tiger_death = null;
    public static final SoundEvent woolly_rhino_ambient = null;
    public static final SoundEvent woolly_rhino_hit = null;
    public static final SoundEvent woolly_rhino_death = null;

    @SubscribeEvent
    public static void registerSounds(@Nonnull RegistryEvent.Register<SoundEvent> event) {
        IForgeRegistry<SoundEvent> registry = event.getRegistry();
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "mammoth_ambient")).setRegistryName(Reference.MODID, "mammoth_ambient"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "mammoth_hit")).setRegistryName(Reference.MODID, "mammoth_hit"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "mammoth_death")).setRegistryName(Reference.MODID, "mammoth_death"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "saber_tooth_tiger_ambient")).setRegistryName(Reference.MODID, "saber_tooth_tiger_ambient"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "saber_tooth_tiger_hit")).setRegistryName(Reference.MODID, "saber_tooth_tiger_hit"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "saber_tooth_tiger_death")).setRegistryName(Reference.MODID, "saber_tooth_tiger_death"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "woolly_rhino_ambient")).setRegistryName(Reference.MODID, "woolly_rhino_ambient"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "woolly_rhino_hit")).setRegistryName(Reference.MODID, "woolly_rhino_hit"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "woolly_rhino_death")).setRegistryName(Reference.MODID, "woolly_rhino_death"));
    }
}
