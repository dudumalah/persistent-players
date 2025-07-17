package de.maxhenkel.persistentplayers.compat;

import com.modularwarfare.common.capability.extraslots.ExtraContainer;
import com.modularwarfare.common.capability.extraslots.ExtraContainerProvider;
import de.maxhenkel.persistentplayers.entities.PersistentPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MWExtraSlotsHandler {
    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PersistentPlayerEntity) {
            ExtraContainer container = new ExtraContainer();
            event.addCapability(
                    new ResourceLocation("modularwarfare", "extraslots"),
                    new ExtraContainerProvider(container)
            );
        }
    }
}
