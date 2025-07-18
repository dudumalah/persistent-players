package de.maxhenkel.persistentplayers.compat;

import com.modularwarfare.common.capability.extraslots.CapabilityExtra;
import com.modularwarfare.common.capability.extraslots.IExtraItemHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class MWCompat {
    public void mwDropSlots(EntityPlayerMP p) {
        IExtraItemHandler slots = (IExtraItemHandler) p.getCapability((Capability) CapabilityExtra.CAPABILITY, (EnumFacing) null);
        for (int i = 0; i < slots.getSlots(); i++) {
            ItemStack stackInSlot = slots.getStackInSlot(i);
            if (!stackInSlot.isEmpty()) {
                EntityItem dropped = p.entityDropItem(stackInSlot.copy(), 0F);
                if (dropped != null) {
                    dropped.setPickupDelay(10);
                }
                stackInSlot.setCount(0);
            }
        }
    }
}
