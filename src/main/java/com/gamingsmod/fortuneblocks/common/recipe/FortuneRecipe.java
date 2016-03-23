package com.gamingsmod.fortuneblocks.common.recipe;

import com.gamingsmod.fortuneblocks.common.helper.NBTHelper;
import com.gamingsmod.fortuneblocks.common.items.ItemFortuneHolder;
import com.gamingsmod.fortuneblocks.common.override.ToolExp;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class FortuneRecipe implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        boolean foundTool = false;
        boolean foundHolder = false;

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack != null) {
                if (stack.getItem() instanceof ItemPickaxe || stack.getItem() instanceof ItemAxe) {
                    if (foundTool)
                        return false;

                    foundTool = true;
                } else if(stack.getItem() instanceof ItemFortuneHolder) {
                    if (foundHolder)
                        return false;

                    foundHolder = true;
                }
            }
        }

        return foundTool && foundHolder;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack tool = null;
        ItemStack holder = null;

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack != null) {
                if (stack.getItem() instanceof ItemPickaxe || stack.getItem() instanceof ItemAxe) {
                    tool = stack;
                } else if(stack.getItem() instanceof ItemFortuneHolder) {
                    holder = stack;
                }
            }
        }

        double fortune = NBTHelper.getDouble(holder, ToolExp.TAG_EXTRAFORTUNE);
        double current = NBTHelper.getDouble(tool, ToolExp.TAG_EXTRAFORTUNE);
        fortune = fortune + current;

        NBTHelper.setDouble(tool, ToolExp.TAG_EXTRAFORTUNE, fortune);
        return tool;
    }

    @Override
    public int getRecipeSize() {
        return 10;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv) {
        ItemStack[] stacks = new ItemStack[inv.getSizeInventory()];
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if(stack != null && stack.getItem() instanceof ItemFortuneHolder) {
                NBTHelper.setDouble(stack, ToolExp.TAG_EXTRAFORTUNE, 0);
                stacks[i] = stack;
            }
        }

        return stacks;
    }
}
