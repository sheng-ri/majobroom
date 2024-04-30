package com.innky.majobroom.item;

import com.innky.majobroom.ModMajoBroom;
import com.innky.majobroom.registry.ClothModels;
import com.innky.majobroom.registry.ItemRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.innky.majobroom.item.ModArmorMaterial.CLOTH;

public class MajoWearableItem extends ArmorItem {

    public static final DyedItemColor DEFAULT_COLOR = new DyedItemColor(
            FastColor.ARGB32.color(221, 163, 199), true
    );

    public MajoWearableItem(Type p_266831_ ) {
        super(CLOTH, p_266831_, new Properties()
                .durability(ArmorItem.Type.LEGGINGS.getDurability(40))
                .component(DataComponents.DYED_COLOR,DEFAULT_COLOR));
    }

//    @Override
//    public void onArmorTick(ItemStack stack, Level world, Player player) {
////        player.addPotionEffect(new EffectInstance(Effects.SPEED,60,3));
//    }

    @Override
    public @NotNull Object getRenderPropertiesInternal() {

        return new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (itemStack.is(ItemRegistry.HAT)) {
                    return ClothModels.HAT;
                } else return ClothModels.CLOTH;
            }
        };
    }

    private static final ResourceLocation HAT_TEXTURE = new ResourceLocation(ModMajoBroom.MODID,
            "jsonmodels/textures/majo_hat.png");
    private static final ResourceLocation CLOTH_TEXTURE = new ResourceLocation(ModMajoBroom.MODID,
            "jsonmodels/textures/majo_cloth.png");
    /*
    Neoforge hook for render texture.
     */
    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        if (stack.is(ItemRegistry.HAT)) {
            if (layer.dyeable()) {
                return HAT_TEXTURE;
            } else return new ResourceLocation(ModMajoBroom.MODID, "jsonmodels/textures/majo_hat_overlay.png");
        } else return CLOTH_TEXTURE;
    }
}
