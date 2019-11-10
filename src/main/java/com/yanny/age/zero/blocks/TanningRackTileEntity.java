package com.yanny.age.zero.blocks;

import com.yanny.age.zero.config.Config;
import com.yanny.age.zero.recipes.TanningRackRecipe;
import com.yanny.age.zero.subscribers.TilesSubscriber;
import com.yanny.age.zero.subscribers.ToolSubscriber;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class TanningRackTileEntity extends TileEntity implements IInventoryInterface {
    public static final int ITEMS = 1;
    private static final Random random = new Random(System.currentTimeMillis());

    private final NonNullList<ItemStack> stacks = NonNullList.withSize(ITEMS * 2, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> sidedInventoryHandler = LazyOptional.of(() -> createSidedInventoryHandler(stacks));
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);
    private final IItemHandlerModifiable tmpItemHandler = new ItemStackHandler(1);
    private final RecipeWrapper tmpItemHandlerWrapper = new RecipeWrapper(tmpItemHandler);

    public TanningRackTileEntity() {
        //noinspection ConstantConditions
        super(TilesSubscriber.tanning_rack);
    }

    @Nonnull
    @Override
    public IInventory getInventory() {
        return inventoryWrapper;
    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        ItemStackUtils.deserializeStacks(invTag, stacks);
        super.read(tag);
    }

    @Override
    @Nonnull
    public CompoundNBT write(CompoundNBT tag) {
        tag.put("inv", ItemStackUtils.serializeStacks(stacks));
        return super.write(tag);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(getPos(), getType().hashCode(), getUpdateTag());
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        read(pkt.getNbtCompound());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side != null) {
                return sidedInventoryHandler.cast();
            } else {
                return nonSidedInventoryHandler.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void remove() {
        sidedInventoryHandler.invalidate();
        nonSidedInventoryHandler.invalidate();
        super.remove();
    }

    boolean blockActivated(PlayerEntity player, Hand handIn) {
        assert world != null;
        ItemStack itemStack = player.getHeldItem(handIn);

        if (!player.isSneaking() && !world.isRemote && handIn == Hand.MAIN_HAND) {
            TanningRackRecipe recipe = getRecipe(itemStack);
            int pos = 0;

            if (stacks.get(pos).isEmpty() && stacks.get(pos + ITEMS).isEmpty() && recipe != null) {
                stacks.set(pos, new ItemStack(itemStack.getItem(), 1));

                if (itemStack.getCount() > 1) {
                    itemStack.setCount(itemStack.getCount() - 1);
                } else {
                    player.setHeldItem(handIn, ItemStack.EMPTY);
                }

                world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 3);
                return true;
            }

            //noinspection ConstantConditions
            if (itemStack.getItem().equals(ToolSubscriber.flint_knife) && !stacks.get(pos).isEmpty()) {
                itemStack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(EquipmentSlotType.MAINHAND));

                if (random.nextDouble() < Config.tanningRackFinishChance) {
                    TanningRackRecipe rackRecipe = getRecipe(stacks.get(pos));

                    if (rackRecipe != null) {
                        stacks.set(pos + ITEMS, rackRecipe.getCraftingResult(null));
                    }

                    stacks.set(pos, ItemStack.EMPTY);
                    world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 3);
                }

                return true;
            }

            if (itemStack.isEmpty() && !stacks.get(pos + ITEMS).isEmpty()) {
                NonNullList<ItemStack> itemStacks = NonNullList.create();
                itemStacks.add(stacks.get(pos + ITEMS).copy());
                stacks.set(pos + ITEMS, ItemStack.EMPTY);
                stacks.set(pos, ItemStack.EMPTY);
                InventoryHelper.dropItems(world, getPos(), itemStacks);

                world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 3);
                world.playSound(null, getPos(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return true;
            }
        }
        return false;
    }

    @Nullable
    private TanningRackRecipe getRecipe(ItemStack item) {
        assert world != null;
        tmpItemHandler.setStackInSlot(0, item);
        return world.getRecipeManager().getRecipe(TanningRackRecipe.tanning_rack, tmpItemHandlerWrapper, world).orElse(null);
    }

    private IItemHandlerModifiable createNonSidedInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {
            @Override
            protected void onContentsChanged(int slot) {
                assert world != null;
                markDirty();
                world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

    private IItemHandlerModifiable createSidedInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {
            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (slot >= ITEMS) {
                    return super.extractItem(slot, amount, simulate);
                } else {
                    return ItemStack.EMPTY;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (slot < ITEMS) {
                    if (getStackInSlot(slot).isEmpty() && world != null) {
                        TanningRackRecipe recipe = getRecipe(stack);
                        if (recipe != null) {
                            return super.insertItem(slot, stack, simulate);
                        }
                    }

                    return stack;
                } else {
                    return stack;
                }
            }

            @Override
            protected void onContentsChanged(int slot) {
                assert world != null;
                markDirty();
                world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }
}