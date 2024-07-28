package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.recipes.*;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@SuppressWarnings("unused")
@ObjectHolder(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeSubscriber {
    public static final FlintWorkbenchRecipeSerializer flint_workbench = null;
    public static final DryingRackRecipeSerializer drying_rack = null;
    public static final TanningRackRecipeSerializer tanning_rack = null;
    public static final TreeStumpRecipeSerializer tree_stump = null;
    public static final MillstoneRecipeSerializer millstone = null;

    @SubscribeEvent
    public static void registerTileEntity(@Nonnull RegistryEvent.Register<IRecipeSerializer<?>> event) {
        IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();
        registry.register(new FlintWorkbenchRecipeSerializer().setRegistryName(Reference.MODID, "flint_workbench"));
        registry.register(new DryingRackRecipeSerializer(DryingRackRecipe::new).setRegistryName(Reference.MODID, "drying_rack"));
        registry.register(new TanningRackRecipeSerializer(TanningRackRecipe::new).setRegistryName(Reference.MODID, "tanning_rack"));
        registry.register(new TreeStumpRecipeSerializer(TreeStumpRecipe::new).setRegistryName(Reference.MODID, "tree_stump"));
        registry.register(new MillstoneRecipeSerializer(MillstoneRecipe::new).setRegistryName(Reference.MODID, "millstone"));
    }
}
