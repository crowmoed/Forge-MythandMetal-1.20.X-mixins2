package net.pinto.mythandmetal.mixin;


import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class SimpleMixin {


    @Inject(method = "aiStep", at = @At("HEAD"))
    private void onAiStep(CallbackInfo info) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        if (player.input.jumping) {
            System.out.print("Jumping");
            player.setDeltaMovement(player.getDeltaMovement().add(0.0, 0.2, 0.0));
        }
    }

}
