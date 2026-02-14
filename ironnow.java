package net.allulose.untitled;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener; // 1. 리스너 임포트 추가
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

// 2. 클래스 뒤에 "implements Listener"를 꼭 붙여야 합니다.
public final class Ironnow extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // 3. 여기가 핵심! 서버에 이 클래스의 이벤트를 등록합니다.
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("플러그인 활성화 완료!");
    }

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

    public void openBedGui(Player player) {
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
        openBedGui(player);


    }


}
