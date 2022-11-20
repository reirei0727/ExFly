package exfly.exfly;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Event implements Listener {
    private double progress = 1.0;
    private Bar bar;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        //e.setCancelled(true);
        if(bar == null){
            bar = new Bar();
            bar.createBar();
            Bukkit.getOnlinePlayers().forEach((player)->{
                bar.addPlayer(player);
                player.setAllowFlight(true);
                player.setFlying(true);
                player.sendMessage("飛行モードON");
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,100000,10,false,false));
                Location loc = player.getLocation();
                player.sendTitle(net.md_5.bungee.api.ChatColor.AQUA + "GAME START", "ブロックタッチ禁止の世界", 40, 80, 40);
                loc.getWorld().playSound(loc, Sound.ENTITY_WITHER_SPAWN, 1, 1);
            });
        }
        Player p = e.getPlayer();
        World w = p.getWorld();
        for (int x = -1; x < 2; x++) {
            for (int y = -2; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    Block block = p.getLocation().subtract(x,y,z).getBlock();
                    if(block.getType() != Material.AIR&&block.getType() != Material.WATER){
                        progress = Math.round((progress - 0.1) * 10.0) / 10.0;
                            Bukkit.getOnlinePlayers().forEach((player) -> {
                                player.sendMessage(ChatColor.RED + p.getName() + "がブロックに触れました！");
                                Location l = player.getLocation();
                                w.createExplosion(l,50F,true,true);
                                player.sendMessage("残り体力"+progress*10);
                                bar.getBar().setProgress(progress);

                                if(progress == 0){
                                    player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH,1,1);
                                    player.sendTitle(net.md_5.bungee.api.ChatColor.DARK_PURPLE + "GAME OVER", "ブロックタッチ禁止の世界", 40, 250, 40);
                                   progress = 1;
                                 }
                            });

                    }

               }
            }
        }
    }

    @EventHandler
    public void PlayerItemEvent(EntityPickupItemEvent e){
        if(e.getEntity() instanceof Player && e.getItem().getItemStack().getType() == Material.DIAMOND){
            for(Player op: Bukkit.getOnlinePlayers()){
                op.getLocation().getWorld().playSound(op.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE,1,1);
                op.sendTitle(net.md_5.bungee.api.ChatColor.AQUA + "GAME CLEAR!!!", "ブロックタッチ禁止の世界", 40, 110, 40);
            }
        }
    }

    @EventHandler
    public void PlayerDeathEvent(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        p.setAllowFlight(true);
        p.setFlying(true);
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), task ->{
        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,100000,10,false,false));
        },5L);
    }

}