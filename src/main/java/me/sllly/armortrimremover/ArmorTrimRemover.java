package me.sllly.armortrimremover;

import me.sllly.armortrimremover.commands.ArmorTrimCommandSystem;
import me.sllly.armortrimremover.files.ItemConfig;
import me.sllly.armortrimremover.listeners.AnvilListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorTrimRemover extends JavaPlugin {

    public static ItemConfig itemConfig;
    public static ArmorTrimRemover plugin;

    @Override
    public void onEnable() {
        plugin = this;
        reloadConfigs();

        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
        new ArmorTrimCommandSystem("armortrimremover","atr").registerCommandBranch(this);

    }

    @Override
    public void onDisable() {
    }

    public void reloadConfigs(){
        itemConfig = new ItemConfig(getDataFolder(), "config");
        try {
            itemConfig.initialize();
        }catch (Exception  e){
            e.printStackTrace();
        }
    }
}
