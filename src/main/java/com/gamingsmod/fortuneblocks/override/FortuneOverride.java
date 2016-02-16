package com.gamingsmod.fortuneblocks.override;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class FortuneOverride
{
    @SubscribeEvent
    public void onHarvest(BlockEvent.HarvestDropsEvent e)
    {
        if (e.harvester != null && e.harvester.getHeldItem().getItem() instanceof ItemPickaxe) {
            int itemsDropping = 0;
            int fortuneLvl = e.fortuneLevel;



            if (fortuneLvl > 0) {
                Random rand = new Random();
                ItemStack itemDropping = e.drops.iterator().next();
                e.drops.clear();

                itemsDropping = rand.nextInt(fortuneLvl + 1);
                e.drops.add(new ItemStack(itemDropping.getItem(), itemsDropping));
            }

        }
    }
}