package com.gamingsmod.fortuneblocks.common.evaluate;

import com.gamingsmod.fortuneblocks.common.helper.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class AxeXp
{
    private static Random random = new Random();

    public static double evalate(Block blockBroken)
    {
        for (String name : OreDictionaryHelper.getOreDicNames(new ItemStack(blockBroken))) {
            if (name.startsWith("log"))
                return random.nextDouble() / 2;
        }
        return 0;
    }
}
