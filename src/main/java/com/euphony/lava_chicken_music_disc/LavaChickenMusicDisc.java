package com.euphony.lava_chicken_music_disc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public class LavaChickenMusicDisc implements ModInitializer {
    public static final String MOD_ID = "lava_chicken_music_disc";

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModSounds.initialize();

        LootTableEvents.MODIFY.register((RegistryKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, RegistryWrapper.WrapperLookup registries) -> {
            if (key.getValue().equals(Identifier.ofVanilla("entities/zombie")) && source.isBuiltin()) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS,
                                new EntityPredicate.Builder()
                                        .flags(new EntityFlagsPredicate.Builder().isBaby(true))
                                        .vehicle(new EntityPredicate.Builder()
                                                .type(EntityType.CHICKEN)
                                        )
                        ).build())
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build())
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_LAVA_CHICKEN));
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
