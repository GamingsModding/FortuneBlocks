package com.gamingsmod.fortuneblocks.override;

import com.gamingsmod.fortuneblocks.helper.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class ToolExp
{
    public static final String TAG_EXTRAFORTUNE = "extFortune";

    @SubscribeEvent
    public void onItemToolTip(ItemTooltipEvent e)
    {
        if (e.entityPlayer == null) {
            return;
        }

        if (e.itemStack.getItem() instanceof ItemPickaxe) {
            ItemStack pickaxe = e.itemStack;
            double level = NBTHelper.getDouble(pickaxe, TAG_EXTRAFORTUNE);
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            String percent = df.format(level) + "%";

            e.toolTip.add(StatCollector.translateToLocalFormatted("fortuneblocks.tooltip.extfortune", percent));
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent e)
    {
        if (e.getPlayer() != null && isRealPlayer(e.getPlayer())) {
            ItemStack pickaxe = e.getPlayer().getHeldItem();
            if (pickaxe.getItem() instanceof ItemPickaxe) {
                double level = NBTHelper.getDouble(pickaxe, TAG_EXTRAFORTUNE);
                Block broken = e.state.getBlock();
                double addLevel = expGain(broken);
                level = level + addLevel;
                NBTHelper.setDouble(pickaxe, TAG_EXTRAFORTUNE, level);
            }
        }
    }

    // Copied from psi
    // @source https://github.com/Vazkii/Psi/blob/master/src/main/java/vazkii/psi/common/item/ItemCAD.java #isTruePlayer
    public boolean isRealPlayer(Entity e)
    {
        Pattern fakePlayerPattern = Pattern.compile("^(?:\\[.*\\])|(?:ComputerCraft)$");
        if(!(e instanceof EntityPlayer))
            return false;

        EntityPlayer player = (EntityPlayer) e;

        String name = player.getName();
        return !(player instanceof FakePlayer || fakePlayerPattern.matcher(name).matches());
    }

    public static String[] getOreDicNames(ItemStack itemStack)
    {
        int[] ids = OreDictionary.getOreIDs(itemStack);
        String[] names = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            names[i] = OreDictionary.getOreName(ids[i]);
        }

        return names;
    }

    public double expGain(Block block)
    {
        for (String name : getOreDicNames(new ItemStack(block))) {
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
            } else if (name.equals("oreEmerald")) {
                return 0.75;
            } else if (name.equals("oreQuartz")) {
                return 0.75;
            }
        }
        return 0;
    }
}
