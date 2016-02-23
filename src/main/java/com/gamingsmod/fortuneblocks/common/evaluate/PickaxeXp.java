package com.gamingsmod.fortuneblocks.common.evaluate;

import com.gamingsmod.fortuneblocks.common.helper.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class PickaxeXp
{
    public static double evaluate(Block blockBroken)
    {
        for (String name : OreDictionaryHelper.getOreDicNames(new ItemStack(blockBroken))) {
            if (name.equals("oreCoal")) {
                return 0.1;
            } else if (name.equals("oreCopper")) {
                return 0.15;
            } else if (name.equals("oreIron")) {
                return 0.2;
            } else if (name.equals("oreLapis")) {
                return 0.2;
            } else if (name.equals("oreRedstone")) {
                return 0.3;
            } else if (name.equals("oreGold")) {
                return 0.35;
            } else if (name.equals("oreDiamond")) {
                return 0.5;
            } else if (name.equals("oreQuartz")) {
                return 0.5;
            } else if (name.equals("oreEmerald")) {
                return 0.75;
            }
        }
        return 0;
    }
}
