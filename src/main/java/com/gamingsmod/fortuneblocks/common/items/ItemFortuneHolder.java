package com.gamingsmod.fortuneblocks.common.items;

import com.gamingsmod.fortuneblocks.common.names.ModItemNames;
import com.gamingsmod.fortuneblocks.common.override.ToolExp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemFortuneHolder extends ModItem
{
    public ItemFortuneHolder() {
        super(ModItemNames.ITEM_FORTUNE_HOLDER);
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (!GuiScreen.isShiftKeyDown()) {
            ToolExp.addPercentInformation(stack, tooltip);
            tooltip.add(StatCollector.translateToLocal("fortuneblocks.tooltip.holdShift").replaceAll("&", "\u00a7"));
        } else tooltip.add(StatCollector.translateToLocal("item.fortuneblocks:fortuneHolder.info"));
    }
}
