package com.portallightblocker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntryInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class PortalLightBlocker implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("[PortalLightBlocker] Replacing Nether Portal luminance...");

        // 1️⃣ Create a custom Nether Portal block with no light emission
        NetherPortalBlock newPortal = new NetherPortalBlock(
            AbstractBlock.Settings.create()
                .noCollision()
                .ticksRandomly()
                .strength(-1.0F)
                .sounds(BlockSoundGroup.GLASS)
                .luminance(state -> 0)
                .pistonBehavior(PistonBehavior.BLOCK)
        );

        try {
            // 2️⃣ Replace the static reference in Blocks.NETHER_PORTAL
            Field portalField = Blocks.class.getDeclaredField("NETHER_PORTAL");
            portalField.setAccessible(true);

            // Remove 'final' modifier (Java reflection trick)
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(portalField, portalField.getModifiers() & ~Modifier.FINAL);

            portalField.set(null, newPortal);

            // 3️⃣ Replace the registry entry for nether_portal
            Identifier key = Identifier.ofVanilla("nether_portal");
			@SuppressWarnings("unchecked")
            MutableRegistry<Block> mutableRegistry = (MutableRegistry<Block>) Registries.BLOCK;

            mutableRegistry.add(
                RegistryKey.of(Registries.BLOCK.getKey(), key),
                newPortal,
                RegistryEntryInfo.DEFAULT
            );

            System.out.println("[PortalLightBlocker] Nether Portal successfully overridden!");
        } catch (Throwable e) {
            System.err.println("[PortalLightBlocker] Failed to override Nether Portal block:");
            e.printStackTrace();
        }
    }
}
