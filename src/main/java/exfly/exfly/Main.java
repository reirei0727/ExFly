package exfly.exfly;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main plugin;
    public  static Main instance;
    public Main(){
        instance = this;
    }
    public static Main getInstance(){return instance;}
    @Override
    public void onEnable() {
        getCommand("game").setExecutor(new exfly(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin(){
        return plugin;
    }

}
