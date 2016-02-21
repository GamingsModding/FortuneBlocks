package com.gamingsmod.fortuneblocks.override;

import com.gamingsmod.fortuneblocks.helper.NBTHelper;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FortuneOverride
{
    @SubscribeEvent
    public void onHarvest(BlockEvent.HarvestDropsEvent e)
    {
        if (e.harvester != null && !e.isSilkTouching) {
            double extraFortune = NBTHelper.getDouble(e.harvester.getHeldItem(), ToolExp.TAG_EXTRAFORTUNE);
            extraFortune = extraFortune / 100;

            if (e.harvester.getHeldItem().getItem() instanceof ItemPickaxe) {
                if (getFirstOreDicName(new ItemStack(e.state.getBlock())).startsWith("ore")) {
                    ItemStack itemDropping = e.drops.iterator().next();
                    int fortuneLvl = e.fortuneLevel;
                    Double fortune = fortuneLvl + extraFortune;
                    if (!(itemDropping.getItem() instanceof ItemBlock)) {
                        e.drops.clear();
                        e.drops.add(new ItemStack(itemDropping.getItem(), fortune.intValue()));
                    }
                }
            } else if (e.harvester.getHeldItem().getItem() instanceof ItemAxe) {
                if (getFirstOreDicName(new ItemStack(e.state.getBlock())).startsWith("log")) {
                    ItemStack itemDropping = e.drops.iterator().next();
                    int fortuneLvl = e.fortuneLevel;
                    Double fortune = fortuneLvl + extraFortune;
                    System.out.println(itemDropping);
                    System.out.println(itemDropping.getItem());
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
