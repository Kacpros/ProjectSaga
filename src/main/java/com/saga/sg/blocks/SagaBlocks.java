package com.saga.sg.blocks;

import com.saga.sg.Saga;
import com.saga.sg.blocks.custom.BlacksmithStationBlock;
import com.saga.sg.blocks.custom.SagaAnvilBlock;
import com.saga.sg.items.SagaItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class SagaBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Saga.MOD_ID);


    public static final RegistryObject<Block> SAGA_ANVIL = registerBlock("saga_anvil",
            () -> new SagaAnvilBlock(BlockBehaviour.Properties.of(Material.METAL)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BLACKSMITH_STATION = registerBlock("blacksmith_station",
            () -> new BlacksmithStationBlock(BlockBehaviour.Properties.of(Material.METAL)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends  Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return SagaItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void registerBlocks(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
