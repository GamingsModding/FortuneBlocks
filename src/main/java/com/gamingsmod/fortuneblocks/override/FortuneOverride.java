package com.gamingsmod.fortuneblocks.override;

import net.minecraft.block.Block;
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
            Block block = e.state.getBlock();
            String firstOre = "";
            ItemStack itemDropping = e.drops.iterator().next();

            if (!e.isSilkTouching) {
                firstOre = getFirstOreDicName(new ItemStack(block));
            } else {
                firstOre = getFirstOreDicName(itemDropping);
            }

            if ((fortuneLvl > 0 || e.isSilkTouching) && firstOre.startsWith("ore")) {
                System.out.println("Fired");
                Random rand = new Random();
                System.out.println(itemDropping);
                e.drops.clear();

                itemsDropping = rand.nextInt(fortuneLvl + 1) + 1;
                e.drops.add(new ItemStack(itemDropping.getItem(), itemsDropping));
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
