package com.innky.majobroom.registry;

import com.innky.majobroom.model.MajoWearableModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClothModels {

    public static MajoWearableModel HAT = new MajoWearableModel("majo_hat");
    public static MajoWearableModel CLOTH = new MajoWearableModel("majo_cloth");

}
