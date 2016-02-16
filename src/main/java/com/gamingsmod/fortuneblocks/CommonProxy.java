package com.gamingsmod.fortuneblocks;

import com.gamingsmod.fortuneblocks.override.FortuneOverride;
import com.gamingsmod.fortuneblocks.override.ToolExp;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
    public void preinit(FMLPreInitializationEvent e)
    {

    }

    public void init(FMLInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new FortuneOverride());
        MinecraftForge.EVENT_BUS.register(new ToolExp());
    }

    public void postinit(FMLPostInitializationEvent e)
    {

    }
}
