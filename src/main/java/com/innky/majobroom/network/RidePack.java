package com.innky.majobroom.network;

import com.innky.majobroom.ModMajoBroom;
import com.innky.majobroom.entity.MajoBroomEntity;
import com.innky.majobroom.registry.ItemRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record RidePack(int entityId, boolean isRide) implements CustomPacketPayload {

    static final ResourceLocation ID = new ResourceLocation(ModMajoBroom.MODID, "ride_pack");

    public RidePack(FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readBoolean());
    }

    @Override
    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeInt(entityId);
        pBuffer.writeBoolean(isRide);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handle(RidePack ridePack, PlayPayloadContext ctx) {
        if (ctx.player().isEmpty()) return;

        ctx.workHandler().submitAsync(() -> {
            Player playerEntity = ctx.player().get();
            var broomEntity = (MajoBroomEntity) playerEntity.level().getEntity(ridePack.entityId);
            if (broomEntity == null) return;
            if (ridePack.isRide) {
                playerEntity.startRiding(broomEntity);
            } else {
                broomEntity.remove(Entity.RemovalReason.KILLED);
                ItemStack itemStack = new ItemStack(ItemRegistry.broomItem.get());

                itemStack.getOrCreateTag().putBoolean("controlMode", broomEntity.getControlMode());
                if (!playerEntity.isCreative() && !playerEntity.getInventory().add(itemStack)) {
                    broomEntity.spawnAtLocation(broomEntity.getDropItem());
                }
            }
        });
    }
}
