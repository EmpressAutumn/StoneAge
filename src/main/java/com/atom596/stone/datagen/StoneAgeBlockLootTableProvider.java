package com.atom596.stone.datagen;

import com.atom596.stone.block.StoneAgeBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class StoneAgeBlockLootTableProvider extends FabricBlockLootTableProvider {
    public StoneAgeBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(StoneAgeBlocks.THATCH_BLOCK);
        addDrop(StoneAgeBlocks.THATCH_STAIRS);
        addDrop(StoneAgeBlocks.THATCH_SLAB);
    }
}
