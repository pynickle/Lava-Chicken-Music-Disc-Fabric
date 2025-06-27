package com.euphony.lava_chicken_music_disc;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public interface ModSongs {
    RegistryKey<JukeboxSong> LAVA_CHICKEN = of("lava_chicken");

    private static RegistryKey<JukeboxSong> of(String id) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(LavaChickenMusicDisc.MOD_ID, id));
    }
}
