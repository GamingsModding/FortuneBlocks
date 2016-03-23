package com.gamingsmod.fortuneblocks.client.render;

import com.gamingsmod.fortuneblocks.common.init.ModItems;
import com.gamingsmod.fortuneblocks.common.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ItemRender
{
    public static void init()
    {
        register(ModItems.fortuneHolder);
    }

    private static void register(Item item)
    {
//        System.out.println(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(6 + Reference.MOD_ID_LOWER.length()));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(6 + Reference.MOD_ID_LOWER.length()), "inventory"));
    }
}
