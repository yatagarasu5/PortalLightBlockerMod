package com.portallightblocker.mixin;

// Import the correct classes we need
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// This tells Mixin to target the vanilla BlockState class, NOT MinecraftServer
@Mixin(BlockState.class)
public abstract class BlockStateMixin {

    // This gives us access to the 'getBlock()' method from the original BlockState class
    @Shadow
    public abstract Block getBlock();

    /**
     * Injects code into the getLuminance() method of the BlockState class.
     * We run our code at the "RETURN" point (just before the method returns a value).
     * We make it 'cancellable' so we can overwrite the return value.
     */
    @Inject(method = "getLuminance", at = @At("RETURN"), cancellable = true)
    private void portallightblocker$modifyPortalLuminance(CallbackInfoReturnable<Integer> cir) {
        
        // Check if the block being checked is a NETHER_PORTAL
        // We use 'this.getBlock()' which we got from the @Shadow above
        if (this.getBlock() == Blocks.NETHER_PORTAL) {
            
            // If it is, cancel the original return value (11)
            // and set the new return value to 0.
            cir.setReturnValue(0);
        }

        // If it's not a nether portal, we do nothing, and the method
        // returns its original value (e.g., 14 for a torch, 0 for stone).
    }
}