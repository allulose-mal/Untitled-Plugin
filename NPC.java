package net.allulose.untitled;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.UUID;

public class NPC implements Listener, CommandExecutor {

    final public Ironnow plugin;

    public NPC(Ironnow plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            World world = player.getWorld();
            Pig pig = (Pig) world.spawnEntity(((Player) sender).getLocation(), EntityType.PIG);
            pig.setAI (false);
            pig.setInvulnerable(true);
            pig.setNoPhysics(true);
            pig.setSilent(true);

                pig.setCustomNameVisible(true);
                pig.setCustomName(ChatColor.AQUA + pig.getUniqueId().toString());

        }
        return true;
    }

   guiManage.guiManager gui = new guiManage.guiManager();


    @EventHandler
    public void clickPig(PlayerInteractEntityEvent event) {

        Map<UUID, Inventory> storageData = plugin.storageData;
        Player player = event.getPlayer();
        Entity pig = event.getRightClicked();
        if (pig instanceof Pig) {
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.3f, 1.0f);

            if (storageData.containsKey(pig.getUniqueId())) {
                Inventory inventory = storageData.get(pig.getUniqueId());
                guiManage.guiManager.openPigStorage(player, inventory);
            } else {
                Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "도야지 창고");
                storageData.put(pig.getUniqueId(), inventory);
                guiManage.guiManager.openPigStorage(player, inventory);
            }

        }
    }

    @EventHandler
    public void closePig(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().equals(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "도야지 창고")) {
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_CLOSE, 0.3f, 1.0f);
        }
    }

}
