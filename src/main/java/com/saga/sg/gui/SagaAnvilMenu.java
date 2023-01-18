package com.saga.sg.gui;

import com.saga.sg.blocks.SagaBlocks;
import com.saga.sg.blocks.entity.custom.SagaAnvilEntity;
import com.saga.sg.gui.slot.SagaResultSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class SagaAnvilMenu extends AbstractContainerMenu
{
    private final SagaAnvilEntity blockEntity;
    private final Level level;

    public SagaAnvilMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData)
    {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public SagaAnvilMenu(int pContainerId, Inventory inv, BlockEntity entity)
    {
        super(SagaMenuTypes.SAGA_ANVIL_MENU.get(), pContainerId);
        checkContainerSize(inv, 25);
        blockEntity = ((SagaAnvilEntity) entity);
        this.level = inv.player.level;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 43, 38));
            this.addSlot(new SlotItemHandler(handler, 1, 43, 56));
            this.addSlot(new SlotItemHandler(handler, 2, 43, 74));
            this.addSlot(new SlotItemHandler(handler, 3, 43, 92));
            this.addSlot(new SlotItemHandler(handler, 4, 61, 38));
            this.addSlot(new SlotItemHandler(handler, 5, 61, 56));
            this.addSlot(new SlotItemHandler(handler, 6, 61, 74));
            this.addSlot(new SlotItemHandler(handler, 7, 61, 92));
            this.addSlot(new SlotItemHandler(handler, 8, 79, 38));
            this.addSlot(new SlotItemHandler(handler, 9, 79, 56));
            this.addSlot(new SlotItemHandler(handler, 10, 79, 74));
            this.addSlot(new SlotItemHandler(handler, 11, 79, 92));
            this.addSlot(new SlotItemHandler(handler, 12, 98, 38));
            this.addSlot(new SlotItemHandler(handler, 13, 98, 56));
            this.addSlot(new SlotItemHandler(handler, 14, 98, 74));
            this.addSlot(new SlotItemHandler(handler, 15, 98, 92));
            this.addSlot(new SlotItemHandler(handler, 16, 115, 38));
            this.addSlot(new SlotItemHandler(handler, 17, 115, 56));
            this.addSlot(new SlotItemHandler(handler, 18, 115, 74));
            this.addSlot(new SlotItemHandler(handler, 19, 115, 92));
            this.addSlot(new SlotItemHandler(handler, 20, 134, 38));
            this.addSlot(new SlotItemHandler(handler, 21, 134, 56));
            this.addSlot(new SlotItemHandler(handler, 22, 134, 72));
            this.addSlot(new SlotItemHandler(handler, 23, 134, 96));

            this.addSlot(new SagaResultSlot(handler, 24, 214, 66));

        });
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 25;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, SagaBlocks.SAGA_ANVIL.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}