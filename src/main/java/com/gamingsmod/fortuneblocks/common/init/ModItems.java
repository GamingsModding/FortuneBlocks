package com.gamingsmod.fortuneblocks.common.init;

import com.gamingsmod.fortuneblocks.common.items.ItemFortuneHolder;
import com.gamingsmod.fortuneblocks.common.items.ModItem;
import com.gamingsmod.fortuneblocks.common.names.ModItemNames;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems
{
    public static ModItem fortuneHolder;

    public static void init()
    {
        fortuneHolder = register(new ItemFortuneHolder(), ModItemNames.ITEM_FORTUNE_HOLDER);
    }

    private static ModItem register(ModItem item, String name)
    {
        GameRegistry.registerItem(item, name);
        return item;
    }
}
