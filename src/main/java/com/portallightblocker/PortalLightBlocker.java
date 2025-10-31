package com.portallightblocker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class PortalLightBlocker implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("[PortalLightBlocker] Replacing Nether Portal luminance...");

        // Create new Nether Portal block with light disabled
        NetherPortalBlock newPortal = new NetherPortalBlock(
            FabricBlockSettings.create()
                .noCollision()
                .ticksRandomly()
                .strength(-1.0F)
                .sounds(BlockSoundGroup.GLASS)
                .luminance(state -> 0) // <-- light disabled
                .pistonBehavior(PistonBehavior.BLOCK)
        );

        try {
            // 1️⃣ Replace the final static field in Blocks.NETHER_PORTAL
            Field portalField = Blocks.class.getDeclaredField("NETHER_PORTAL");
            portalField.setAccessible(true);

            // Remove 'final' modifier
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(portalField, portalField.getModifiers() & ~Modifier.FINAL);

            // Replace with our custom block
            portalField.set(null, newPortal);

            // 2️⃣ Replace the registry entry for nether_portal
            var key = Identifier.ofVanilla("nether_portal");
            ((net.minecraft.registry.MutableRegistry<NetherPortalBlock>) Registries.BLOCK).add(
                net.minecraft.registry.RegistryKey.of(Registries.BLOCK.getKey(), key),
                newPortal,
                net.minecraft.registry.entry.RegistryEntryInfo.DEFAULT
            );

            System.out.println("[PortalLightBlocker] Nether Portal successfully overridden!");
        } catch (Throwable e) {
            System.err.println("[PortalLightBlocker] Failed to override Nether Portal block:");
            e.printStackTrace();
        }
    }
}
