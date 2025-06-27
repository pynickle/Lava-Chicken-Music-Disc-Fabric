package com.euphony.lava_chicken_music_disc;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_LAVA_CHICKEN = register(
            SoundEvent.of(Identifier.of(LavaChickenMusicDisc.MOD_ID, "music_disc.lava_chicken")),
            "music_disc.lava_chicken"
    );

    public static RegistryEntry.Reference<SoundEvent> register(SoundEvent sound, String id) {
        Identifier soundID = Identifier.of(LavaChickenMusicDisc.MOD_ID, id);

        return Registry.registerReference(Registries.SOUND_EVENT, soundID, sound);
    }

    public static void initialize() {
    }
}
