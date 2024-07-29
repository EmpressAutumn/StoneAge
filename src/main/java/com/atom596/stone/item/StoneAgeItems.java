package com.atom596.stone.item;

import com.atom596.stone.StoneAge;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class StoneAgeItems {
    public static final Item RAW_HIDE = new Item(new Item.Settings());
    public static final Item UNLIT_TORCH = new Item(new Item.Settings());
    public static final Item BONE_ARROW_HEAD = new Item(new Item.Settings());
    public static final Item DRIED_GRASS = new Item(new Item.Settings());
    public static final Item LEATHER_STRIP = new Item(new Item.Settings());
    public static final Item FISH_BONE = new Item(new Item.Settings());
    public static final Item FLOUR = new Item(new Item.Settings());
    public static final Item RAW_BREAD = new Item(new Item.Settings());
    public static final Item MAMMOTH_TUSK = new Item(new Item.Settings());
    public static final Item RHINO_TUSK = new Item(new Item.Settings());
    public static final Item SABER_TEETH = new Item(new Item.Settings());  // Rename later?
    public static final Item BACKPACK = new Item(new Item.Settings().maxCount(1));  // Not implemented
    public static final Item STONE_TABLET = new Item(new Item.Settings().maxCount(1));  // Not implemented
    public static final Item GRASS_MESH = new Item(new Item.Settings().maxCount(16));  // Not implemented
    public static final Item COBWEB_MESH = new Item(new Item.Settings().maxCount(32));  // Not implemented
    public static final Item GRASS_LEAD = new Item(new Item.Settings());  // Not implemented

    // Food Items
    public static final Item RAW_FAT = new Item(new Item.Settings().food(StoneAgeFoodComponents.RAW_FAT));
    public static final Item COOKED_FAT = new Item(new Item.Settings().food(StoneAgeFoodComponents.COOKED_FAT));
    public static final Item RAW_VENISON = new Item(new Item.Settings().food(StoneAgeFoodComponents.RAW_VENISON));
    public static final Item COOKED_VENISON = new Item(new Item.Settings().food(StoneAgeFoodComponents.COOKED_VENISON));
    public static final Item RAW_FOWL = new Item(new Item.Settings().food(StoneAgeFoodComponents.RAW_FOWL));
    public static final Item COOKED_FOWL = new Item(new Item.Settings().food(StoneAgeFoodComponents.COOKED_FOWL));
    public static final Item RAW_AUROCH = new Item(new Item.Settings().food(StoneAgeFoodComponents.RAW_AUROCH));
    public static final Item COOKED_AUROCH = new Item(new Item.Settings().food(StoneAgeFoodComponents.COOKED_AUROCH));
    public static final Item RAW_MOUFLON = new Item(new Item.Settings().food(StoneAgeFoodComponents.RAW_MOUFLON));
    public static final Item COOKED_MOUFLON = new Item(new Item.Settings().food(StoneAgeFoodComponents.COOKED_MOUFLON));
    public static final Item RAW_BOAR = new Item(new Item.Settings().food(StoneAgeFoodComponents.RAW_BOAR));
    public static final Item COOKED_BOAR = new Item(new Item.Settings().food(StoneAgeFoodComponents.COOKED_BOAR));
    public static final Item RAW_MAMMOTH = new Item(new Item.Settings().food(StoneAgeFoodComponents.RAW_MAMMOTH));
    public static final Item COOKED_MAMMOTH = new Item(new Item.Settings().food(StoneAgeFoodComponents.COOKED_MAMMOTH));
    public static final Item RAW_RHINO = new Item(new Item.Settings().food(StoneAgeFoodComponents.RAW_RHINO));
    public static final Item COOKED_RHINO = new Item(new Item.Settings().food(StoneAgeFoodComponents.COOKED_RHINO));
    public static final Item RAW_TIGER = new Item(new Item.Settings().food(StoneAgeFoodComponents.RAW_TIGER));
    public static final Item COOKED_TIGER = new Item(new Item.Settings().food(StoneAgeFoodComponents.COOKED_TIGER));

    public static void register() {
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "raw_hide"), RAW_HIDE);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "unlit_torch"), UNLIT_TORCH);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "bone_arrow_head"), BONE_ARROW_HEAD);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "dried_grass"), DRIED_GRASS);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "leather_strip"), LEATHER_STRIP);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "fish_bone"), FISH_BONE);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "flour"), FLOUR);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "raw_bread"), RAW_BREAD);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "mammoth_tusk"), MAMMOTH_TUSK);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "rhino_tusk"), RHINO_TUSK);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "saber_teeth"), SABER_TEETH);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "backpack"), BACKPACK);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "stone_tablet"), STONE_TABLET);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "grass_mesh"), GRASS_MESH);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "cobweb_mesh"), COBWEB_MESH);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "grass_lead"), GRASS_LEAD);

        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "fat"), RAW_FAT);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "cooked_fat"), COOKED_FAT);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "venison"), RAW_VENISON);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "cooked_venison"), COOKED_VENISON);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "fowl_meat"), RAW_FOWL);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "cooked_fowl_meat"), COOKED_FOWL);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "auroch_meat"), RAW_AUROCH);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "cooked_auroch_meat"), COOKED_AUROCH);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "mouflon_meat"), RAW_MOUFLON);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "cooked_mouflon_meat"), COOKED_MOUFLON);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "boar_meat"), RAW_BOAR);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "cooked_boar_meat"), COOKED_BOAR);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "mammoth_meat"), RAW_MAMMOTH);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "cooked_mammoth_meat"), COOKED_MAMMOTH);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "rhino_meat"), RAW_RHINO);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "cooked_rhino_meat"), COOKED_RHINO);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "tiger_meat"), RAW_TIGER);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "cooked_tiger_meat"), COOKED_TIGER);
    }
}
