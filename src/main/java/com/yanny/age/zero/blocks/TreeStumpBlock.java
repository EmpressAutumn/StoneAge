package com.yanny.age.zero.blocks;

import com.yanny.age.zero.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TreeStumpBlock extends Block {
    public static final Tag<Item> AXES = new ItemTags.Wrapper(new ResourceLocation(Reference.MODID, "axes"));
    private static final VoxelShape SHAPE = VoxelShapes.combine(Block.makeCuboidShape(0, 0, 0, 16, 1, 16),
            Block.makeCuboidShape(2, 1, 2, 14, 12, 14), IBooleanFunction.OR);

    public TreeStumpBlock() {
        super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TreeStumpTileEntity();
    }

    @Nonnull
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (!worldIn.isRemote) {
            TreeStumpTileEntity tileEntity = (TreeStumpTileEntity) worldIn.getTileEntity(pos);

            if (tileEntity != null) {
                tileEntity.onBlockRightClicked(player);
                return;
            }
        }

        super.onBlockClicked(state, worldIn, pos, player);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if ((tileentity instanceof TreeStumpTileEntity)) {
            if (!worldIn.isRemote && (handIn == Hand.MAIN_HAND)) {
                return ((TreeStumpTileEntity) tileentity).blockActivated(player);
            }

            return true; // do not show ghost item
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @SuppressWarnings("deprecation")
    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean hasCustomBreakingProgress(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getPlayerRelativeBlockHardness(BlockState state, PlayerEntity player, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos) {
        if (player.getHeldItemMainhand().getItem().isIn(AXES)) {
            return 0.0f;
        }
        return super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
    }
}
