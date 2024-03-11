package me.sllly.armortrimremover.listeners;

import me.sllly.armortrimremover.util.NbtApiUtils;
import me.sllly.armortrimremover.util.Util;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();

        ItemStack slot2 = inventory.getItem(2);
        if (slot2 != null) {
            return;
        }

        ItemStack slot0test = inventory.getItem(0);
        if (slot0test == null) {
            return;
        }
        ItemStack slot0 = slot0test.clone();
        ItemStack slot1 = inventory.getItem(1);
        if (slot1 == null) {
            return;
        }
        if (!NbtApiUtils.hasNBTKey(slot1, "armortrimremover")){
            return;
        }

        ItemMeta slot0Meta = slot0.getItemMeta();
        if (!(slot0Meta instanceof ArmorMeta)){
            return;
        }
        ArmorMeta armorMeta = (ArmorMeta) slot0Meta;
        if (!armorMeta.hasTrim()){
            return;
        }
        armorMeta.setTrim(null);

        slot0.setItemMeta(armorMeta);
        event.setResult(slot0);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Check if the click is in an anvil inventory
        if (event.getClickedInventory() instanceof AnvilInventory) {
            AnvilInventory anvilInventory = (AnvilInventory) event.getClickedInventory();

            ItemStack slot1 = anvilInventory.getItem(1);
            if (slot1 == null) {
                return;
            }
            if (!NbtApiUtils.hasNBTKey(slot1, "armortrimremover")){
                return;
            }

            // Check if the slot is the result slot (slot 2 in an anvil)
            if (event.getSlot() == 2 && event.getSlotType() == InventoryType.SlotType.RESULT) {
                // Prevent default behavior
                event.setCancelled(true);

                // Get the result item
                ItemStack resultItem = event.getCurrentItem();

                // Check if the result item is not null and not air (to ensure there's an item to work with)
                if (resultItem != null && resultItem.getType() != Material.AIR) {
                    // Set the cursor to the result item
                    event.getWhoClicked().setItemOnCursor(resultItem.clone());

                    // Clear the input slots
                    anvilInventory.setItem(0, null);
                    anvilInventory.setItem(1, null);

                    event.getWhoClicked().getWorld().playSound(event.getWhoClicked(), Sound.BLOCK_ANVIL_USE, 1f, 1f);
                }
            }
        }
    }
}
