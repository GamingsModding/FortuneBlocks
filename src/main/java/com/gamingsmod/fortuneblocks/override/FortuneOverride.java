package com.gamingsmod.fortuneblocks.override;

import com.gamingsmod.fortuneblocks.helper.NBTHelper;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FortuneOverride
{
    @SubscribeEvent
    public void onHarvest(BlockEvent.HarvestDropsEvent e)
    {
        if (e.harvester != null && e.harvester.getHeldItem().getItem() instanceof ItemPickaxe) {
            double extraFortune = NBTHelper.getDouble(e.harvester.getHeldItem(), ToolExp.TAG_EXTRAFORTUNE);
            System.out.println(extraFortune);

            if (e.isSilkTouching) {

            } else {
                ItemStack[] droppingItems = new ItemStack[e.drops.size()];
                while (e.drops.iterator().hasNext()) {

                }
            }
        }
    }

    public static String getFirstOreDicName(ItemStack itemStack)
    {
        String[] names = ToolExp.getOreDicNames(itemStack);
        if (names.length > 0)
            return names[0];
        return "";
    }
}
