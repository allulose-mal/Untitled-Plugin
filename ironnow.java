package net.allulose.untitled;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Ironnow extends JavaPlugin implements Listener {

    NPC npc =  new NPC(this);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(npc, this);
        getCommand("summonnpc").setExecutor(npc);
        getLogger().info("플러그인 활성화 완료!");
    }

    public final Map<UUID, Inventory> storageData = new HashMap<UUID, Inventory>();

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        player.sendMessage(ChatColor.GOLD + "너는 " + playerName + " 이라는 것이다");
    }


    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location location = player.getLocation();

        Team team1 = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("red1");

        if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam("red1") == null) {
            Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("red1");
            team.setColor(ChatColor.RED);
        }



            switch (event.getState()) {
                case CAUGHT_FISH -> {
                    player.sendMessage(ChatColor.AQUA + "잡았구나!");
                    Entity TNT = world.spawnEntity(location, EntityType.TNT);
                    player.playSound(location, Sound.ENTITY_PLAYER_ATTACK_CRIT, 1, 1);
                    TNT.setGlowing(true);
                    team1.addEntity(TNT);

                    {}

                    TNT.setCustomName(ChatColor.GOLD + "펑!!");
                    TNT.setCustomNameVisible(true);

                    if (event.getCaught() instanceof Item item) {
                        item.remove();
                    }
                }
                case LURED -> {
                    player.sendMessage(ChatColor.AQUA + "ㄷㄱㄷㄱ");
                    player.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                }
            }
    }


    @EventHandler
    public void onGuiClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        World world = player.getWorld();
        if (event.getCurrentItem() != null) {

            if (event.getView().getTitle().equals(ChatColor.BLACK +"" + ChatColor.BOLD + "잠에 들고 싶으십니까?")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "아침")) {
                    event.setCancelled(true);
                    event.getView().close();
                    player.sendMessage(ChatColor.GREEN + "좋은 아침!");
                    world.setTime(1000);
                }

                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "정오")) {
                    event.setCancelled(true);
                    event.getView().close();
                    player.sendMessage(ChatColor.AQUA + "좋은 하루!");
                    world.setTime(6000);
                }

                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "저녁")) {
                    event.setCancelled(true);
                    event.getView().close();
                    player.sendMessage(ChatColor.GRAY + "좋은 저녁!");
                    world.setTime(13000);
                }

                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "새벽")) {
                    event.setCancelled(true);
                    event.getView().close();
                    player.sendMessage(ChatColor.DARK_GRAY + "자라 좀");
                    world.setTime(18000);
                }
            }
        }
    }

    @EventHandler
    public void onBed(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);
        guiManage.guiManager.openBedGui(player);

    }


}

