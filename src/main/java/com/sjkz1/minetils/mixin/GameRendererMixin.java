package com.sjkz1.minetils.mixin;

import com.sjkz1.minetils.Minetils;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "bobViewWhenHurt",at = @At("HEAD"),cancellable = true)
    public void setNoHurtTime(MatrixStack matrixStack, float f, CallbackInfo ci) {
       if(Minetils.CONFIG.getConfig().disableHurtCamera) {
           ci.cancel();
       }
    }
}