package com.gamingsmod.fortuneblocks.common.commands;

import com.gamingsmod.fortuneblocks.common.helper.NBTHelper;
import com.gamingsmod.fortuneblocks.common.items.ItemFortuneHolder;
import com.gamingsmod.fortuneblocks.common.override.ToolExp;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandExtraFortune extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "giveExtraFortune";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "<Player> <Amount>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 2)
            throw new WrongUsageException("<Player> <Amount>");
        else {
            EntityPlayerMP playerMP = getPlayer(sender, args[0]);
            ItemStack itemStackHeld = playerMP.getHeldItem();
            Item itemHeld = itemStackHeld.getItem();
            int amountToAdd = parseInt(args[1]);

            if (itemHeld instanceof ItemTool || itemHeld instanceof ItemFortuneHolder) {
                double extFortune = NBTHelper.getDouble(itemStackHeld, ToolExp.TAG_EXTRAFORTUNE);
                extFortune += amountToAdd;
                NBTHelper.setDouble(itemStackHeld, ToolExp.TAG_EXTRAFORTUNE, extFortune);
                sender.addChatMessage(new ChatComponentText("Extra Fortune Added!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
            } else throw new CommandException("You must be holding a tool");
        }
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }
}
