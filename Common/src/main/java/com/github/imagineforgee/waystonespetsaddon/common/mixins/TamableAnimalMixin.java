package com.github.imagineforgee.waystonespetsaddon.common.mixins;

import com.github.imagineforgee.waystonespetsaddon.common.Constants;
import com.github.imagineforgee.waystonespetsaddon.common.api.events.PetTamedEvent;
import net.blay09.mods.balm.api.Balm;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TamableAnimal.class)
public abstract class TamableAnimalMixin {

    @Inject(method = "tame", at = @At("HEAD"))
    private void onTame(Player pl, CallbackInfo ci) {
        if (!(pl instanceof ServerPlayer player)) return;

        TamableAnimal pet = (TamableAnimal) (Object) this;

        Constants.LOG.info("Tamed pet with UUID of: {}", pet.getUUID());
        Balm.getEvents().fireEvent(new PetTamedEvent(player, pet));
    }
}
