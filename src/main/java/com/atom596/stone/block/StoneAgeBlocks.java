package com.atom596.stone.block;

import com.atom596.stone.StoneAge;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class StoneAgeBlocks {
    public static final Block THATCH_BLOCK = new Block(AbstractBlock.Settings.create().strength(1.0f).sounds(BlockSoundGroup.GRASS));
    public static final Block THATCH_STAIRS = new StairsBlock(THATCH_BLOCK.getDefaultState(), AbstractBlock.Settings.copyShallow(THATCH_BLOCK));
    public static final Block THATCH_SLAB = new SlabBlock(AbstractBlock.Settings.create().strength(1.0f).sounds(BlockSoundGroup.GRASS));

    public static void register() {
        Registry.register(Registries.BLOCK, Identifier.of(StoneAge.MOD_ID, "thatch_block"), THATCH_BLOCK);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "thatch_block"), new BlockItem(THATCH_BLOCK, new Item.Settings()));
        Registry.register(Registries.BLOCK, Identifier.of(StoneAge.MOD_ID, "thatch_stairs"), THATCH_STAIRS);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "thatch_stairs"), new BlockItem(THATCH_STAIRS, new Item.Settings()));
        Registry.register(Registries.BLOCK, Identifier.of(StoneAge.MOD_ID, "thatch_slab"), THATCH_SLAB);
        Registry.register(Registries.ITEM, Identifier.of(StoneAge.MOD_ID, "thatch_slab"), new BlockItem(THATCH_SLAB, new Item.Settings()));
    }
}
