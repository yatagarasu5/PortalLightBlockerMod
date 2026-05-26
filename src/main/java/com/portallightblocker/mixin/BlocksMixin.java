package com.portallightblocker.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyReturnValue;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {

    @ModifyReturnValue(
        method = "getLuminance",
        at = @At("RETURN")
    )
    private int portallightblocker$noLight(int original, BlockState state) {
        return 0;
    }
}
