package exfly.exfly;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class exfly implements CommandExecutor{
    private final Event eventListener = new Event();
    private boolean on = true;
    private final Main plugin;

    public exfly(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("game")){
            Bukkit.getServer().getPluginManager().registerEvents(eventListener, plugin);

            /**
            new BukkitRunnable(){
                int i = 5;
                @Override
                public void run() {
                    if(i==0){
                        for(Player op: Bukkit.getOnlinePlayers()){
                            Location loc = op.getLocation();
                            op.sendTitle(ChatColor.AQUA + "GAME START", "ブロックタッチ禁止の世界", 40, 80, 40);
                            loc.getWorld().playSound(loc, Sound.ENTITY_WITHER_SPAWN, 1, 1);
                            Bukkit.getServer().getPluginManager().registerEvents(eventListener, plugin);
                            cancel();
                        }
                    }
                    for(Player op: Bukkit.getOnlinePlayers()){
                        op.sendMessage("ゲーム開始まで"+i);
                        Location loc = op.getLocation();
                        loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 10);
                        i--;

                    }

                }
            }.runTaskTimer(Main.getPlugin(),100L,20L);

                **/
            return true;
        }
        /**
         // on が trueだったらイベントを登録して
         if (on){
         Bukkit.getServer().getPluginManager().registerEvents(eventListener,plugin);
         on = false;
         }else {
         // falseだったら　イベントを解除
         removeListener();
         on = true;
         }
         return true;

         **/
        return true;
    }
    public void removeListener(){
            HandlerList.unregisterAll(eventListener);
    }
}