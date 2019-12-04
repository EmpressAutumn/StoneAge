package com.yanny.age.stone.subscribers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yanny.age.stone.blocks.FlintWorkbenchTileEntity;
import com.yanny.age.stone.config.Config;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.yanny.age.stone.Reference.MODID;
import static net.minecraft.block.Blocks.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {
    private static final Set<ResourceLocation> RECIPES_TO_REMOVE = Sets.newHashSet(
            new ResourceLocation("minecraft", "wooden_axe"),        // removed
            new ResourceLocation("minecraft", "wooden_pickaxe"),    // removed
            new ResourceLocation("minecraft", "wooden_hoe"),        // removed
            new ResourceLocation("minecraft", "wooden_shovel"),     // removed
            new ResourceLocation("minecraft", "wooden_sword"),      // removed
            new ResourceLocation("minecraft", "torch"),             // lit by using on fire or campfire

            // TODO disabled recipes - change them in future
            new ResourceLocation("minecraft", "crafting_table") //TODO disable based on config
    );
    private static final Set<ResourceLocation> RECIPES_TO_ADD = Sets.newHashSet(
            new ResourceLocation(MODID, "bow"),
            new ResourceLocation(MODID, "campfire"),
            new ResourceLocation(MODID, "furnace"),
            new ResourceLocation(MODID, "stone_axe"),
            new ResourceLocation(MODID, "stone_pickaxe"),
            new ResourceLocation(MODID, "stone_hoe"),
            new ResourceLocation(MODID, "stone_shovel"),
            new ResourceLocation(MODID, "stone_sword")
    );

    private static final Set<ResourceLocation> ADVANCEMENTS_TO_REMOVE = Sets.newHashSet(
            new ResourceLocation("minecraft", "recipes/tools/wooden_axe"),
            new ResourceLocation("minecraft", "recipes/tools/wooden_pickaxe"),
            new ResourceLocation("minecraft", "recipes/tools/wooden_hoe"),
            new ResourceLocation("minecraft", "recipes/tools/wooden_shovel"),
            new ResourceLocation("minecraft", "recipes/combat/wooden_sword"),
            new ResourceLocation("minecraft", "recipes/decorations/torch"),

            // TODO disabled advancements - change them in future
            new ResourceLocation("minecraft", "recipes/decorations/crafting_table")
    );

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void FMLServerStartingEvent(FMLServerStartingEvent event) {
        if (Config.removeVanillaRecipes) {
            RecipeManager recipeManager = event.getServer().getRecipeManager();
            Class recipeManagerClass = recipeManager.getClass();

            try {
                Field recipes = recipeManagerClass.getDeclaredFields()[2];
                recipes.setAccessible(true);
                Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipesMap = (Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>>) recipes.get(recipeManager);
                Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> map = Maps.newHashMap();
                recipesMap.forEach((iRecipeType, resourceLocationIRecipeMap) -> {
                    Map<ResourceLocation, IRecipe<?>> map1 = map.computeIfAbsent(iRecipeType, (recipeType) -> Maps.newHashMap());
                    resourceLocationIRecipeMap.forEach(map1::put);
                    RECIPES_TO_REMOVE.forEach(map1::remove);
                    RECIPES_TO_ADD.forEach(resourceLocation -> {
                        IRecipe<?> recipe = map1.remove(resourceLocation);

                        if (recipe != null) {
                            map1.put(new ResourceLocation("minecraft", resourceLocation.getPath()), recipe);
                        }
                    });
                });
                recipes.set(recipeManager, ImmutableMap.copyOf(map));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            AdvancementManager advancementManager = event.getServer().getAdvancementManager();
            Class advancementManagerClass = advancementManager.getClass();
            Field advancements = advancementManagerClass.getDeclaredFields()[2];
            advancements.setAccessible(true);

            try {
                AdvancementList advancementList = (AdvancementList) advancements.get(advancementManager);
                Class list = advancementList.getClass();
                Field listField = list.getDeclaredFields()[1];
                listField.setAccessible(true);
                Map<ResourceLocation, Advancement> map = (Map<ResourceLocation, Advancement>) listField.get(advancementList);
                ADVANCEMENTS_TO_REMOVE.forEach(map::remove);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (Config.changeMiningLevelForVanillaBlocks) {
            Set<Block> set = ImmutableSet.of(COAL_ORE, COBBLESTONE, ICE, MOSSY_COBBLESTONE, NETHERRACK, PACKED_ICE, BLUE_ICE, SANDSTONE, CHISELED_SANDSTONE,
                    CUT_SANDSTONE, CHISELED_RED_SANDSTONE, CUT_RED_SANDSTONE, RED_SANDSTONE, STONE, GRANITE, POLISHED_GRANITE, DIORITE, POLISHED_DIORITE, ANDESITE,
                    POLISHED_ANDESITE, STONE_SLAB, SMOOTH_STONE_SLAB, SANDSTONE_SLAB, PETRIFIED_OAK_SLAB, COBBLESTONE_SLAB, BRICK_SLAB, STONE_BRICK_SLAB,
                    NETHER_BRICK_SLAB, RED_SANDSTONE_SLAB, SMOOTH_RED_SANDSTONE, SMOOTH_SANDSTONE, SMOOTH_STONE, STONE_BUTTON, STONE_PRESSURE_PLATE,
                    POLISHED_GRANITE_SLAB, SMOOTH_RED_SANDSTONE_SLAB, MOSSY_STONE_BRICK_SLAB, POLISHED_DIORITE_SLAB, MOSSY_COBBLESTONE_SLAB, SMOOTH_SANDSTONE_SLAB,
                    GRANITE_SLAB, ANDESITE_SLAB, RED_NETHER_BRICK_SLAB, POLISHED_ANDESITE_SLAB, DIORITE_SLAB, SHULKER_BOX, BLACK_SHULKER_BOX, BLUE_SHULKER_BOX,
                    BROWN_SHULKER_BOX, CYAN_SHULKER_BOX, GRAY_SHULKER_BOX, GREEN_SHULKER_BOX, LIGHT_BLUE_SHULKER_BOX, LIGHT_GRAY_SHULKER_BOX, LIME_SHULKER_BOX,
                    MAGENTA_SHULKER_BOX, ORANGE_SHULKER_BOX, PINK_SHULKER_BOX, PURPLE_SHULKER_BOX, RED_SHULKER_BOX, WHITE_SHULKER_BOX, YELLOW_SHULKER_BOX);
            set.forEach(block -> setHarvestLevel(block, ToolSubscriber.Tiers.BONE_TIER.getHarvestLevel()));
        }
    }

    @SubscribeEvent
    public static void rightClickWithFlint(PlayerInteractEvent.RightClickBlock event) {
        TileEntity entity = event.getWorld().getTileEntity(event.getPos());

        if (entity instanceof FlintWorkbenchTileEntity) {
            ((FlintWorkbenchTileEntity) entity).blockClicked(event.getPlayer());
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void litTorch(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();

        if (event.getHand() == Hand.MAIN_HAND && player.getHeldItemMainhand().getItem().equals(ItemSubscriber.unlit_torch)) {
            World world = event.getWorld();
            BlockPos pos = event.getPos();
            BlockPos facePos = event.getPos().offset(event.getFace());
            BlockState blockState = world.getBlockState(pos);
            BlockState faceBlockState = world.getBlockState(facePos);

            if (blockState.getBlock().equals(CAMPFIRE) || faceBlockState.getBlock().equals(FIRE) || blockState.getBlock().equals(TORCH)) {
                player.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.TORCH, player.getHeldItemMainhand().getCount()));
                event.setUseItem(Event.Result.DENY);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void makeFireWithSticksAndDroughtGrass(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        ItemStack mainItem = player.getHeldItemMainhand();
        ItemStack offItem = player.getHeldItemOffhand();

        if (mainItem.getItem() == Items.STICK && offItem.getItem() == Items.STICK && event.getFace() != null) {
            World world = event.getWorld();
            BlockPos position = event.getPos().offset(event.getFace());
            BlockState blockState = world.getBlockState(position);
            List<ItemEntity> droughtGrassList = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(position),
                    itemEntity -> itemEntity.getItem().getItem().equals(ItemSubscriber.drought_grass));

            if (blockState.isAir(world, position) && !droughtGrassList.isEmpty()) {
                world.setBlockState(position, FIRE.getDefaultState(), 11);
                player.sendBreakAnimation(Hand.MAIN_HAND);
                player.sendBreakAnimation(Hand.OFF_HAND);

                if (mainItem.getCount() > 1) {
                    mainItem.setCount(mainItem.getCount() - 1);
                } else {
                    player.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
                }
                if (offItem.getCount() > 1) {
                    offItem.setCount(offItem.getCount() - 1);
                } else {
                    player.setHeldItem(Hand.OFF_HAND, ItemStack.EMPTY);
                }

                droughtGrassList.forEach(Entity::remove);
            }
        }
    }

    private static void setHarvestLevel(Block block, @SuppressWarnings("SameParameterValue") int harvestLevel) {
        Class clazz = Block.class;
        Field harvestLevelField = clazz.getDeclaredFields()[24];
        harvestLevelField.setAccessible(true);
        try {
            harvestLevelField.set(block, harvestLevel);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
