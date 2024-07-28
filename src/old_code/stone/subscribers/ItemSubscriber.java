package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.items.BackpackItem;
import com.yanny.age.stone.items.MeshItem;
import com.yanny.age.stone.items.StoneTabletItem;
import com.yanny.ages.api.group.ModItemGroup;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@SuppressWarnings({"unused", "WeakerAccess"})
@ObjectHolder(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemSubscriber {
    public static final Item raw_hide = null;
    public static final Item antler = null;
    public static final Item unlit_torch = null;
    public static final Item bone_arrow_head = null;
    public static final Item dried_grass = null;
    public static final Item leather_strip = null;
    public static final Item fish_bone = null;
    public static final Item flour = null;
    public static final Item raw_bread = null;
    public static final Item mammoth_tusk = null;
    public static final Item rhino_tusk = null;
    public static final Item saber_teeth = null;
    public static final Item backpack = null;
    public static final Item stone_tablet = null;
    public static final Item grass_mesh = null;
    public static final Item cobweb_mesh = null;
    public static final Item grass_lead = null;

    @SubscribeEvent
    public static void registerItems(@Nonnull RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "raw_hide"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "antler"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "unlit_torch"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "bone_arrow_head"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "dried_grass"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "leather_strip"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "fish_bone"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "flour"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "raw_bread"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "mammoth_tusk"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "rhino_tusk"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "saber_teeth"));
        registry.register(new BackpackItem().setRegistryName(Reference.MODID, "backpack"));
        registry.register(new StoneTabletItem().setRegistryName(Reference.MODID, "stone_tablet"));
        registry.register(new MeshItem(new Item.Properties().group(ModItemGroup.AGES).maxDamage(15)).setRegistryName(Reference.MODID, "grass_mesh"));
        registry.register(new MeshItem(new Item.Properties().group(ModItemGroup.AGES).maxDamage(30)).setRegistryName(Reference.MODID, "cobweb_mesh"));
        registry.register(new Item(new Item.Properties().group(ModItemGroup.AGES).maxStackSize(64)).setRegistryName(Reference.MODID, "grass_lead"));
    }
}
