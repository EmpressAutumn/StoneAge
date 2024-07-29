package com.atom596.stone.item;

import com.atom596.stone.StoneAge;
import com.atom596.stone.block.StoneAgeBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class StoneAgeItemGroup {
    private static final ItemGroup STONE_AGE_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.BONE))
            .displayName(Text.translatable("itemGroup.stone_age.stone_age_group"))
            .entries((context, entries) -> {
                entries.add(StoneAgeBlocks.THATCH_BLOCK);
                entries.add(StoneAgeBlocks.THATCH_STAIRS);
                entries.add(StoneAgeBlocks.THATCH_SLAB);
                entries.add(StoneAgeItems.RAW_HIDE);
                entries.add(StoneAgeItems.UNLIT_TORCH);
                entries.add(StoneAgeItems.BONE_ARROW_HEAD);
                entries.add(StoneAgeItems.DRIED_GRASS);
                entries.add(StoneAgeItems.LEATHER_STRIP);
                entries.add(StoneAgeItems.FISH_BONE);
                entries.add(StoneAgeItems.BACKPACK);
                entries.add(StoneAgeItems.STONE_TABLET);
                entries.add(StoneAgeItems.GRASS_MESH);
                entries.add(StoneAgeItems.COBWEB_MESH);
                entries.add(StoneAgeItems.GRASS_LEAD);
                
                // Items below this are sorted
                entries.add(StoneAgeItems.RAW_FAT);
                entries.add(StoneAgeItems.COOKED_FAT);
                entries.add(StoneAgeItems.RAW_VENISON);
                entries.add(StoneAgeItems.COOKED_VENISON);
                entries.add(StoneAgeItems.RAW_AUROCH);
                entries.add(StoneAgeItems.COOKED_AUROCH);
                entries.add(StoneAgeItems.RAW_BOAR);
                entries.add(StoneAgeItems.COOKED_BOAR);
                entries.add(StoneAgeItems.RAW_FOWL);
                entries.add(StoneAgeItems.COOKED_FOWL);
                entries.add(StoneAgeItems.RAW_MAMMOTH);
                entries.add(StoneAgeItems.COOKED_MAMMOTH);
                entries.add(StoneAgeItems.RAW_RHINO);
                entries.add(StoneAgeItems.COOKED_RHINO);
                entries.add(StoneAgeItems.RAW_TIGER);
                entries.add(StoneAgeItems.COOKED_TIGER);
                entries.add(StoneAgeItems.FLOUR);
                entries.add(StoneAgeItems.RAW_BREAD);
                entries.add(StoneAgeItems.MAMMOTH_TUSK);
                entries.add(StoneAgeItems.RHINO_TUSK);
                entries.add(StoneAgeItems.SABER_TEETH);
            })
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, Identifier.of(StoneAge.MOD_ID, "stone_age_group"), STONE_AGE_GROUP);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            content.addBefore(Items.BEEF, StoneAgeItems.RAW_FAT);
            content.addAfter(StoneAgeItems.RAW_FAT, StoneAgeItems.COOKED_FAT);
            content.addAfter(StoneAgeItems.COOKED_FAT, StoneAgeItems.RAW_VENISON);
            content.addAfter(StoneAgeItems.RAW_VENISON, StoneAgeItems.COOKED_VENISON);
            content.addAfter(StoneAgeItems.COOKED_VENISON, StoneAgeItems.RAW_AUROCH);
            content.addAfter(StoneAgeItems.RAW_AUROCH, StoneAgeItems.COOKED_AUROCH);
            content.addBefore(Items.PORKCHOP, StoneAgeItems.RAW_BOAR);
            content.addAfter(StoneAgeItems.RAW_BOAR, StoneAgeItems.COOKED_BOAR);
            content.addBefore(Items.CHICKEN, StoneAgeItems.RAW_FOWL);
            content.addAfter(StoneAgeItems.RAW_FOWL, StoneAgeItems.COOKED_FOWL);
            content.addAfter(Items.COOKED_RABBIT, StoneAgeItems.RAW_MAMMOTH);
            content.addAfter(StoneAgeItems.RAW_MAMMOTH, StoneAgeItems.COOKED_MAMMOTH);
            content.addAfter(StoneAgeItems.COOKED_MAMMOTH, StoneAgeItems.RAW_RHINO);
            content.addAfter(StoneAgeItems.RAW_RHINO, StoneAgeItems.COOKED_RHINO);
            content.addAfter(StoneAgeItems.COOKED_RHINO, StoneAgeItems.RAW_TIGER);
            content.addAfter(StoneAgeItems.RAW_TIGER, StoneAgeItems.COOKED_TIGER);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.WHEAT, StoneAgeItems.FLOUR);
            content.addAfter(StoneAgeItems.FLOUR, StoneAgeItems.RAW_BREAD);
            content.addAfter(Items.BONE, StoneAgeItems.MAMMOTH_TUSK);
            content.addAfter(StoneAgeItems.MAMMOTH_TUSK, StoneAgeItems.RHINO_TUSK);
            content.addAfter(StoneAgeItems.RHINO_TUSK, StoneAgeItems.SABER_TEETH);
        });
    }
}
