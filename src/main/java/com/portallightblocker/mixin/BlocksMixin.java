package com.portallightblocker.mixin;

import net.minecraft.block.BlockState; // <-- ADD THIS IMPORT
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// This tells Mixin to target the vanilla Blocks class
@Mixin(Blocks.class)
public class BlocksMixin {

    /**
     * This Mixin targets the 'Blocks' class static initializer (<clinit>)
     * where all vanilla blocks are created.
     *
     * We use @ModifyArg to find the exact line where NETHER_PORTAL's
     * 'luminance' property is set and change the value.
     */
    @ModifyArg(
        method = "<clinit>",
        at = @At(
            value = "INVOKE",
            // The target signature is correct, it just takes a ToIntFunction
            target = "Lnet/minecraft/block/AbstractBlock$Settings;luminance(Ljava/util/function/ToIntFunction;)Lnet/minecraft/block/AbstractBlock$Settings;",
            ordinal = 6 
        ),
        index = 0
    )
    private static java.util.function.ToIntFunction<BlockState> portallightblocker$modifyNetherPortalLuminance(
            java.util.function.ToIntFunction<BlockState> originalLuminance) {
        
        // The original function was 'state -> 11'.
        // We are replacing it with our new function: 'state -> 0'.
        // The 'state' parameter is automatically inferred to be of type BlockState.
        return (state) -> 0;
    }
}