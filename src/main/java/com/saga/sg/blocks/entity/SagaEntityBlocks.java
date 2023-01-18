package com.saga.sg.blocks.entity;

import com.saga.sg.Saga;
import com.saga.sg.blocks.SagaBlocks;
import com.saga.sg.blocks.entity.custom.BlacksmithStationEntity;
import com.saga.sg.blocks.entity.custom.SagaAnvilEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SagaEntityBlocks
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Saga.MOD_ID);

    public static final RegistryObject<BlockEntityType<SagaAnvilEntity>> SAGA_ANVIL_ENTITY =
            BLOCK_ENTITIES.register("saga_anvil_entity", () ->
                    BlockEntityType.Builder.of(SagaAnvilEntity::new,
                            SagaBlocks.SAGA_ANVIL.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlacksmithStationEntity>> BLACKSMITH_STATION_ENTITY =
            BLOCK_ENTITIES.register("saga_anvil_entity", () ->
                    BlockEntityType.Builder.of(BlacksmithStationEntity::new,
                            SagaBlocks.BLACKSMITH_STATION.get()).build(null));


    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
