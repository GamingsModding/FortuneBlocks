package com.gamingsmod.fortuneblocks.override;

import com.gamingsmod.fortuneblocks.helper.NBTHelper;
import com.gamingsmod.fortuneblocks.helper.OreDictionaryHelper;
import net.minecraft.item.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FortuneOverride
{
    @SubscribeEvent
    public void onHarvest(BlockEvent.HarvestDropsEvent e)
    {
        if (e.harvester != null) {
            ItemStack heldItemStack = e.harvester.getHeldItem();
            Item heldItem = heldItemStack.getItem();
            if (!e.isSilkTouching && (heldItem instanceof ItemPickaxe || heldItem instanceof ItemAxe)) {
                double extraFortune = NBTHelper.getDouble(e.harvester.getHeldItem(), ToolExp.TAG_EXTRAFORTUNE);
                extraFortune = extraFortune / 100;

                if (e.harvester.getHeldItem().getItem() instanceof ItemPickaxe) {
                    if (OreDictionaryHelper.getFirstOreDicName(new ItemStack(e.state.getBlock())).startsWith("ore")) {
                        ItemStack itemDropping = e.drops.iterator().next();
                        int fortuneLvl = e.fortuneLevel;
                        Double fortune = fortuneLvl + extraFortune;
                        if (!(itemDropping.getItem() instanceof ItemBlock)) {
                            e.drops.clear();
                            e.drops.add(new ItemStack(itemDropping.getItem(), fortune.intValue()));
                        }
                    }
                } else if (e.harvester.getHeldItem().getItem() instanceof ItemAxe) {
                    if (OreDictionaryHelper.getFirstOreDicName(new ItemStack(e.state.getBlock())).startsWith("log")) {
                        ItemStack itemDropping = e.drops.iterator().next();
                        int fortuneLvl = e.fortuneLevel;
                        Double fortune = fortuneLvl + extraFortune;
                        itemDropping.stackSize = fortune.intValue() == 0 ? 1 : fortune.intValue();
                        e.drops.clear();
                        e.drops.add(itemDropping);
                    }
                }
            }
        }
    }
}
