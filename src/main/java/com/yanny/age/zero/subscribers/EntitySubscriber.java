package com.yanny.age.zero.subscribers;

import com.yanny.age.zero.Reference;
import com.yanny.age.zero.entities.DeerEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("unused")
@ObjectHolder(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitySubscriber {
    @SuppressWarnings("unchecked")
    public static final EntityType<DeerEntity> deer = (EntityType<DeerEntity>) EntityType.Builder.create(DeerEntity::new, EntityClassification.CREATURE)
            .size(0.9f, 1.5f).build("").setRegistryName(Reference.MODID,"deer");
    public static final Item deer_spawn_egg = null;

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        registry.register(deer);

        for (Biome biome : ForgeRegistries.BIOMES) {
            switch (biome.getCategory()) {
                case FOREST:
                case PLAINS:
                case TAIGA:
                case EXTREME_HILLS:
                case SAVANNA:
                    biome.getSpawns(deer.getClassification()).add(new Biome.SpawnListEntry(deer, 10, 4, 8));
            }
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new SpawnEggItem(deer, 0x00ffff, 0xff00ff,
                new Item.Properties().group(ItemGroup.MISC)).setRegistryName(Reference.MODID, "deer_spawn_egg"));
    }
}
