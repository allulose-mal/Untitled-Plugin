package net.allulose.untitled;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class guiManage {
    public static class guiManager {
        public static void openBedGui(Player player) {
            Inventory Gui = Bukkit.createInventory(null, 27, ChatColor.BLACK +"" + ChatColor.BOLD + "잠에 들고 싶으십니까?");

            ItemStack Day = new  ItemStack(Material.LIGHT_BLUE_WOOL, 1);
            ItemMeta DayMeta = Day.getItemMeta();
            DayMeta.setDisplayName(ChatColor.GREEN + "아침");
            Day.setItemMeta(DayMeta);

            ItemStack Noon =  new  ItemStack(Material.LIGHT_BLUE_CONCRETE, 1);
            ItemMeta NoonMeta = Noon.getItemMeta();
            NoonMeta.setDisplayName(ChatColor.DARK_AQUA + "정오");
            Noon.setItemMeta(NoonMeta);

            ItemStack Night = new   ItemStack(Material.BLUE_TERRACOTTA, 1);
            ItemMeta NightMeta = Night.getItemMeta();
            NightMeta.setDisplayName(ChatColor.GRAY + "저녁");
            Night.setItemMeta(NightMeta);

            ItemStack MidNight = new  ItemStack(Material.BLACK_TERRACOTTA, 1);
            ItemMeta MidNightMeta = MidNight.getItemMeta();
            MidNightMeta.setDisplayName(ChatColor.DARK_GRAY + "새벽");
            MidNight.setItemMeta(MidNightMeta);

            {}

            Gui.setItem(10, Day);
            Gui.setItem(12, Noon);
            Gui.setItem(14, Night);
            Gui.setItem(16, MidNight);

            player.openInventory(Gui);

        }

        public static void openPigStorage(Player player, Inventory inventory) {
            player.openInventory(inventory);
        }
    }
}

