package com.saga.sg.datagen;

import com.saga.sg.Saga;
import com.saga.sg.items.SagaItems;
import com.saga.sg.items.custom.SagaItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class SagaItemsModelProvider extends ItemModelProvider
{

    public SagaItemsModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, Saga.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        simpleItem(SagaItems.STEEL_INGOT.get());
        simpleItem(SagaItems.BRONZE_INGOT.get());
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Saga.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Saga.MOD_ID,"item/" + item.getId().getPath()));
    }
}

