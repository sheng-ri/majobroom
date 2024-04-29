package com.innky.majobroom.registry;

import com.innky.majobroom.ModMajoBroom;
import com.innky.majobroom.network.RidePack;
import com.innky.majobroom.network.SummonBroomPack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@SuppressWarnings("unused")
public class NetworkRegistry {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final var registrar = event.registrar(ModMajoBroom.MODID)
                .playToServer(SummonBroomPack.TYPE,SummonBroomPack.STREAM_CODEC,SummonBroomPack::handle)
//                .playToServer(SummonBroomPack.ID, SummonBroomPack::new, SummonBroomPack::handle)
                .playToServer(RidePack.TYPE, RidePack.STREAM_CODEC, RidePack::handle)
                .versioned(ModMajoBroom.VERSION);
    }

}
