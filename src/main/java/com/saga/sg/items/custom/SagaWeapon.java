package com.saga.sg.items.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class SagaWeapon extends SwordItem
{

    public SagaWeapon(Tier tier, int attackMod, float attackSpeed, Properties pProperties)
    {
        super(tier, attackMod, attackSpeed, pProperties);
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     */
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
    }

}
