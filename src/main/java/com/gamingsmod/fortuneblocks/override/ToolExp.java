package com.gamingsmod.fortuneblocks.override;

import com.gamingsmod.fortuneblocks.evalate.AxeXp;
import com.gamingsmod.fortuneblocks.evalate.PickaxeXp;
import com.gamingsmod.fortuneblocks.helper.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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

        if (e.itemStack.getItem() instanceof ItemPickaxe || e.itemStack.getItem() instanceof ItemAxe) {
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
            if (pickaxe != null && (pickaxe.getItem() instanceof ItemPickaxe || pickaxe.getItem() instanceof ItemAxe)) {
                double level = NBTHelper.getDouble(pickaxe, TAG_EXTRAFORTUNE);
                Block broken = e.state.getBlock();
                double addLevel = expGain((Item) e.getPlayer().getHeldItem().getItem(), broken);
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

    public double expGain(Item tool, Block block) {
        if (tool instanceof ItemPickaxe) {
            return PickaxeXp.evaluate(block);
        } else if (tool instanceof ItemAxe) {
            return AxeXp.evalate(block);
        }
        return 0;
    }
}
