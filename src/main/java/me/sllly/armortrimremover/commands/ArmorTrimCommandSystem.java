package me.sllly.armortrimremover.commands;

import com.octanepvp.splityosis.commandsystem.SYSCommand;
import com.octanepvp.splityosis.commandsystem.SYSCommandBranch;
import com.octanepvp.splityosis.commandsystem.arguments.PlayerArgument;
import me.sllly.armortrimremover.ArmorTrimRemover;
import me.sllly.armortrimremover.util.NbtApiUtils;
import me.sllly.armortrimremover.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ArmorTrimCommandSystem extends SYSCommandBranch {
    public ArmorTrimCommandSystem(String... names) {
        super(names);
        setPermission("armortrimremover.admin");

        addCommand(new SYSCommand("give")
                .setArguments(new PlayerArgument())
                .setUsage("/atr give [playername]")
                .executes((commandSender, strings) -> {
                    Player player = Bukkit.getPlayer(strings[0]);
                    if (Util.countEmptySlots(player) == 0){
                        Util.sendMessage(commandSender, "&cThis player has a full inventory!");
                        return;
                    }
                    ItemStack item = ArmorTrimRemover.itemConfig.trimRemoverItem.clone();
                    item = NbtApiUtils.applyNBTString(item, "armortrimremover", "true");
                    //item = NbtApiUtils.applyNBTString(item, "un-stacker", UUID.randomUUID().toString());
                    Util.giveItemsToPlayer(player, item, 1);
                }));

        addCommand(new SYSCommand("reload")
                .setArguments()
                .executes((commandSender, strings) -> {
                    ArmorTrimRemover.plugin.reloadConfigs();
                    Util.sendMessage(commandSender, "&aPlugin reloaded!");
                }));
    }
}
