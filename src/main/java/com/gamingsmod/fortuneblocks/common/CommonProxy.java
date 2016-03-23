package com.gamingsmod.fortuneblocks.common;

import com.gamingsmod.fortuneblocks.common.commands.CommandExtraFortune;
import com.gamingsmod.fortuneblocks.common.init.ModItems;
import com.gamingsmod.fortuneblocks.common.override.FortuneOverride;
import com.gamingsmod.fortuneblocks.common.override.ToolExp;
import com.gamingsmod.fortuneblocks.common.recipe.FortuneRecipe;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy
{
    public void preinit(FMLPreInitializationEvent e)
    {
        ModItems.init();
    }

    public void init(FMLInitializationEvent e)
    {
        CraftingManager.getInstance().getRecipeList().add(new FortuneRecipe());
        MinecraftForge.EVENT_BUS.register(new FortuneOverride());
        MinecraftForge.EVENT_BUS.register(new ToolExp());
    }

    public void postinit(FMLPostInitializationEvent e)
    {
        //NO-OP
    }

    public void serverStarting(FMLServerStartingEvent e)
    {
        e.registerServerCommand(new CommandExtraFortune());
    }
}
