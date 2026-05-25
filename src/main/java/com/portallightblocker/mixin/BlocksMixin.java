package com.portallightblocker.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * Replace the luminance lambda used when registering the Nether Portal block.
 * Use the correct ordinal to target the luminance() invocation that applies to nether_portal.
 */
@Mixin(Blocks.class)
public class BlocksMixin {

    @ModifyArg(
        method = "<clinit>",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/AbstractBlock$Settings;luminance(Ljava/util/function/ToIntFunction;)Lnet/minecraft/block/AbstractBlock$Settings;",
            ordinal = 14// <-- 13 instances of luminance()before NETHER_PORTAL in net.minecraft.block.Blocks
        )
    )
    private static java.util.function.ToIntFunction<BlockState> portallightblocker$modifyNetherPortalLuminance(
            java.util.function.ToIntFunction<BlockState> original) {
        return state -> 0;
    }
}
