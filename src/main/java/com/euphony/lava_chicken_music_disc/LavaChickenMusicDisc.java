package com.euphony.lava_chicken_music_disc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroups;
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
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class LavaChickenMusicDisc implements ModInitializer {
    public static final String MOD_ID = "lava_chicken_music_disc";

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModSounds.initialize();

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(Identifier.of("minecraft", "entities/zombie")) && source.isBuiltin()) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS,
                                new EntityPredicate.Builder()
                                        .flags(new EntityFlagsPredicate.Builder().isBaby(true).build())
                                        .vehicle(new EntityPredicate.Builder()
                                                .type(EntityTypePredicate.create(EntityType.CHICKEN)).build()
                                        )
                        ).build())
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build())
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_LAVA_CHICKEN));
                tableBuilder.pool(poolBuilder.build());
            }
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((entries) -> {
            entries.add(ModItems.MUSIC_DISC_LAVA_CHICKEN);
        });
    }
}
