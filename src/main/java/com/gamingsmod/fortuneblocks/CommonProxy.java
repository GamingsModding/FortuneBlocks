package com.gamingsmod.fortuneblocks;

import com.gamingsmod.fortuneblocks.override.FortuneOverride;
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
    }

    public void postinit(FMLPostInitializationEvent e)
    {

    }
}
