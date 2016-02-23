package com.gamingsmod.fortuneblocks.helper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHelper
{
    public static String[] getOreDicNames(ItemStack itemStack)
    {
        int[] ids = OreDictionary.getOreIDs(itemStack);
        String[] names = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            names[i] = OreDictionary.getOreName(ids[i]);
        }

        return names;
    }

    public static String getFirstOreDicName(ItemStack itemStack)
    {
        String[] names = getOreDicNames(itemStack);
        if (names.length > 0)
            return names[0];
        return "";
    }
}
