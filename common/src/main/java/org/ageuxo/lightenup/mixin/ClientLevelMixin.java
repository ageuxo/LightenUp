package org.ageuxo.lightenup.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import org.ageuxo.lightenup.LightenUp;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "getMarkerParticleTarget", at = @At(value = "HEAD"), cancellable = true)
    private void lighten_up$getMarkerParticleInject(CallbackInfoReturnable<Block> cir) {
        LocalPlayer player = this.minecraft.player;
        if (player != null) {
            BlockItem transientPaste = LightenUp.TRANSIENT_PASTE_ITEM.get();
            if (player.getMainHandItem().is(transientPaste) || player.getOffhandItem().is(transientPaste)) {
                cir.setReturnValue(transientPaste.getBlock());
            }
        }
    }
}
