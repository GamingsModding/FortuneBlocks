package com.gamingsmod.fortuneblocks.common.evaluate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockClay;
import net.minecraft.block.BlockSand;

public class SpadeXp
{
    public static double evalate(Block block)
    {
        if (block instanceof BlockSand)
            return 0.05;
        else if (block instanceof BlockClay)
            return 0.10;
        return 0;
    }
}
