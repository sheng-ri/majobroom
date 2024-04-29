package com.innky.majobroom.item;

import com.innky.majobroom.model.MajoWearableModel;

import java.util.HashMap;
import java.util.Map;

public class Modelinit {
    public static Map<String, MajoWearableModel> modelMap = new HashMap<>();
    public static MajoWearableModel defaultModel = null;

    public static void reg(String name, boolean isRemote) {
        MajoWearableModel item = null;
        if (isRemote) {
            item = new MajoWearableModel(name);
            defaultModel = item;
        }

        modelMap.put(name, item);
    }
}
