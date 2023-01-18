package com.saga.sg.items;

import com.saga.sg.Saga;
import com.saga.sg.items.custom.SagaItem;
import com.saga.sg.items.custom.SagaPickaxe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

@Mod.EventBusSubscriber(modid = Saga.MOD_ID)
public class SagaItems
{
    public static final ResourceLocation STANDARD = new ResourceLocation("saga:item/standard.png");

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Saga.MOD_ID);

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () ->
            new SagaItem(new Item.Properties(), STANDARD));

    public static void registerItems(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }


}

