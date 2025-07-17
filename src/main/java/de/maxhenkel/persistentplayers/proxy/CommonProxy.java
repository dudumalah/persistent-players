package de.maxhenkel.persistentplayers.proxy;

import de.maxhenkel.persistentplayers.Config;
import de.maxhenkel.persistentplayers.Log;
import de.maxhenkel.persistentplayers.Main;
import de.maxhenkel.persistentplayers.compat.MWExtraSlotsHandler;
import de.maxhenkel.persistentplayers.entities.PersistentPlayerEntity;
import de.maxhenkel.persistentplayers.events.PlayerEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy {

    public static PlayerEvents PLAYER_EVENTS = new PlayerEvents();
    public static MWExtraSlotsHandler SLOTS_HANDLER = new MWExtraSlotsHandler();

    public void preinit(FMLPreInitializationEvent event) {
        Configuration c;
        try {
            c = new Configuration(event.getSuggestedConfigurationFile());
            Config.init(c);
        } catch (Exception e) {
            Log.w("Could not create config file: " + e.getMessage());
        }

        Log.setLogger(event.getModLog());

    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(PLAYER_EVENTS);
        if(Loader.isModLoaded("modularwarfare")){
            MinecraftForge.EVENT_BUS.register(SLOTS_HANDLER);
        }
        EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID, "player"), PersistentPlayerEntity.class, "player", 133704, Main.instance(), 128, 1, true);
    }

    public void postinit(FMLPostInitializationEvent event) {
        if(Loader.isModLoaded("modularwarfare") && Config.betterLogging){
            Log.w("Persistent Players: Modular Warfare detected!");
        }else{
            Log.w("Persistent Players: Modular Warfare not detected. If you have MW installed and got this message something is wrong!");
        }
    }

}
