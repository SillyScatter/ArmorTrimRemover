package me.sllly.armortrimremover.util;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NbtApiUtils {

    public static ItemStack applyNBTString(ItemStack itemStack, String key, String value){
        if (itemStack!= null && itemStack.getType()!= Material.AIR){
            NBTItem nbtItem = new NBTItem(itemStack);
            nbtItem.setString(key, value);
            return nbtItem.getItem();
        }
        return null;
    }

    public static String getNBTString(ItemStack itemStack, String key){
        if (itemStack!= null && itemStack.getType()!= Material.AIR){
            NBTItem nbtItem = new NBTItem(itemStack);
            if (nbtItem.hasTag(key)){
                String string = nbtItem.getString(key);
                return string;
            }
        }
        return "";
    }

    public static List<String> getNBTKeys(ItemStack itemStack){
        if (itemStack!= null && itemStack.getType()!= Material.AIR){
            NBTItem nbtItem = new NBTItem(itemStack);
            return new ArrayList<>(nbtItem.getKeys());
        }
        return null;
    }

    public static boolean hasNBTKey(ItemStack itemStack, String key){
        if (itemStack!= null && itemStack.getType()!= Material.AIR){
            NBTItem nbtItem = new NBTItem(itemStack);
            return nbtItem.hasTag(key);
        }
        return false;
    }
}
