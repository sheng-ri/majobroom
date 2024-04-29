package com.innky.majobroom.network;

import com.innky.majobroom.ModMajoBroom;
import com.innky.majobroom.MajoBroomEntity;
import com.innky.majobroom.registry.ItemRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import static com.innky.majobroom.registry.ComponentRegistry.CONTROL_MODE;

public record RidePack(int entityId, boolean isRide) implements CustomPacketPayload {

    public static final Type<RidePack> TYPE = new Type<>(new ResourceLocation(ModMajoBroom.MODID, "ride_pack"));

    public static final StreamCodec<FriendlyByteBuf, RidePack> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, RidePack::entityId,
            ByteBufCodecs.BOOL, RidePack::isRide,
            RidePack::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext ctx) {

        ctx.enqueueWork(() -> {
            Player player = ctx.player();
            var broomEntity = (MajoBroomEntity) player.level().getEntity(entityId);
            if (broomEntity == null) return;
            if (isRide) {
                player.startRiding(broomEntity);
            } else {
                broomEntity.remove(Entity.RemovalReason.KILLED);
                ItemStack itemStack = new ItemStack(ItemRegistry.broomItem.get());

                itemStack.getOrDefault(CONTROL_MODE,broomEntity.getControlMode());
                if (!player.isCreative() && !player.getInventory().add(itemStack)) {
                    broomEntity.spawnAtLocation(broomEntity.getDropItem());
                }
            }
        });
    }
}
