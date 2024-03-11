package me.sllly.armortrimremover.files;

import com.octanepvp.splityosis.configsystem.configsystem.AnnotatedConfig;
import com.octanepvp.splityosis.configsystem.configsystem.ConfigField;
import me.sllly.armortrimremover.util.Util;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class ItemConfig extends AnnotatedConfig {
    public ItemConfig(File parentDirectory, String name) {
        super(parentDirectory, name);
    }

    @ConfigField(path = "trim-remover")
    public ItemStack trimRemoverItem = Util.createItemStack(Material.PAPER, 1, "&bRemove Armor Trim", "&7Use this item in an anvil!");

}
