package de.maxhenkel.persistentplayers.compat;

import com.modularwarfare.common.capability.extraslots.CapabilityExtra;
import com.modularwarfare.common.capability.extraslots.IExtraItemHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class MWSlots {
    public void mwDropSlots(EntityPlayerMP p){
        IExtraItemHandler slots = (IExtraItemHandler) p.getCapability((Capability) CapabilityExtra.CAPABILITY, (EnumFacing) null);

        for(int i = 0; i < slots.getSlots(); i++){
            ItemStack stackInSlot = slots.getStackInSlot(i);
            p.entityDropItem(stackInSlot, 0F);
            stackInSlot.setCount(0);
        }
    }
}
