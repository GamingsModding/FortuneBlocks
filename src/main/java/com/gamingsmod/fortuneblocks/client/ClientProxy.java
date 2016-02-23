package com.gamingsmod.fortuneblocks.client;

import com.gamingsmod.fortuneblocks.client.render.ItemRender;
import com.gamingsmod.fortuneblocks.common.CommonProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preinit(FMLPreInitializationEvent e) {
        super.preinit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        ItemRender.init();
    }

    @Override
    public void postinit(FMLPostInitializationEvent e) {
        super.postinit(e);
    }
}
