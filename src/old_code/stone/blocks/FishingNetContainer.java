package com.yanny.age.stone.blocks;

import com.yanny.age.stone.ExampleMod;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import com.yanny.age.stone.subscribers.ContainerSubscriber;
import com.yanny.age.stone.utils.ContainerUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

import java.util.Objects;

import static com.yanny.age.stone.blocks.FishingNetTileEntity.*;

public class FishingNetContainer extends Container {
    private final FishingNetTileEntity tile;
    private final PlayerEntity player;

    public FishingNetContainer(int windowId, @Nonnull PlayerInventory inv, @Nonnull PacketBuffer extraData) {
        this(windowId, extraData.readBlockPos(), Objects.requireNonNull(ExampleMod.proxy.getClientWorld()), inv, Objects.requireNonNull(ExampleMod.proxy.getClientPlayer()));
    }

    FishingNetContainer(int id, @Nonnull BlockPos pos, @Nonnull World world, @Nonnull PlayerInventory inventory, @Nonnull PlayerEntity player) {
        super(ContainerSubscriber.fishing_net, id);
        tile = (FishingNetTileEntity) world.getTileEntity(pos);
        this.player = player;

        if (tile == null) {
            throw new IllegalStateException("TileEntity does not exists!");
        }

        tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0, 26, 35));

            for (int y = 0; y < FishingNetTileEntity.INVENTORY_HEIGHT; y++) {
                for (int x = 0; x < FishingNetTileEntity.INVENTORY_WIDTH; x++) {
                    addSlot(new SlotItemHandler(h, 1 + x + y * FishingNetTileEntity.INVENTORY_WIDTH, 62 + x * 18, 17 + y * 18));
                }
            }
        });

        ContainerUtils.layoutPlayerInventorySlots(((slot, x, y) -> addSlot(new Slot(inventory, slot, x, y))), 8, 84);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        if (tile == null || tile.getWorld() == null) {
            throw new IllegalStateException("Null pointer");
        }

        return isWithinUsableDistance(IWorldPosCallable.of(tile.getWorld(), tile.getPos()), player, BlockSubscriber.fishing_net);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(@Nonnull PlayerEntity playerIn, int index) {
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            ItemStack itemstack = stack.copy();
            if (index < FishingNetTileEntity.ITEMS) {
                if (!mergeItemStack(stack, FishingNetTileEntity.ITEMS + 1, FishingNetTileEntity.ITEMS + 36, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(stack, itemstack);
            } else {
                if (!mergeItemStack(stack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }

            }

            tile.updateState();
            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return ItemStack.EMPTY;
    }
}
