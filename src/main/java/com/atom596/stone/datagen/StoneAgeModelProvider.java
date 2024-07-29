package com.atom596.stone.datagen;

import com.atom596.stone.item.StoneAgeItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class StoneAgeModelProvider extends FabricModelProvider {
    public StoneAgeModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(StoneAgeItems.RAW_HIDE, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.UNLIT_TORCH, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.BONE_ARROW_HEAD, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.DRIED_GRASS, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.LEATHER_STRIP, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.FISH_BONE, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.FLOUR, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.RAW_BREAD, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.MAMMOTH_TUSK, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.RHINO_TUSK, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.SABER_TEETH, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.BACKPACK, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.STONE_TABLET, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.GRASS_MESH, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.COBWEB_MESH, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.GRASS_LEAD, Models.GENERATED);

        itemModelGenerator.register(StoneAgeItems.RAW_FAT, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.COOKED_FAT, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.RAW_VENISON, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.COOKED_VENISON, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.RAW_FOWL, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.COOKED_FOWL, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.RAW_AUROCH, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.COOKED_AUROCH, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.RAW_MOUFLON, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.COOKED_MOUFLON, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.RAW_BOAR, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.COOKED_BOAR, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.RAW_MAMMOTH, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.COOKED_MAMMOTH, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.RAW_RHINO, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.COOKED_RHINO, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.RAW_TIGER, Models.GENERATED);
        itemModelGenerator.register(StoneAgeItems.COOKED_TIGER, Models.GENERATED);
    }
}
