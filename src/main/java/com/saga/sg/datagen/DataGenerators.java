package com.saga.sg.datagen;

import com.saga.sg.Saga;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Saga.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent gatherDataEvent)
    {
            DataGenerator generator = gatherDataEvent.getGenerator();
        ExistingFileHelper existingFileHelper = gatherDataEvent.getExistingFileHelper();
        generator.addProvider(new SagaItemsModelProvider(generator, existingFileHelper));
    }
}
