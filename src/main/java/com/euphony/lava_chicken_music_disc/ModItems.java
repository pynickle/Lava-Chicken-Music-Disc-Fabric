package com.euphony.lava_chicken_music_disc;

import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item MUSIC_DISC_LAVA_CHICKEN = register(
            new MusicDiscItem(9, ModSounds.MUSIC_DISC_LAVA_CHICKEN.value(), new Item.Settings().maxCount(1).rarity(Rarity.RARE), 134),
            "music_disc_lava_chicken"
    );

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(LavaChickenMusicDisc.MOD_ID, id);

        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static void initialize() {
    }
}