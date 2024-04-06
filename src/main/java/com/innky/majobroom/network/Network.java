package com.innky.majobroom.network;

import com.innky.majobroom.ModMajoBroom;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
@SuppressWarnings("unused")
public class Network {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(ModMajoBroom.MODID)
                .play(SummonBroomPack.ID, SummonBroomPack::new, SummonBroomPack::handle)
                .play(RidePack.ID, RidePack::new, RidePack::handle)
                .versioned(ModMajoBroom.VERSION);
    }

}
