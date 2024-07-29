package com.atom596.stone.item;

import com.atom596.stone.StoneAge;
import com.atom596.stone.block.StoneAgeBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
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
            })
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, Identifier.of(StoneAge.MOD_ID, "stone_age_group"), STONE_AGE_GROUP);
    }
}
