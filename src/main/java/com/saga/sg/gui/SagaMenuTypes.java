package com.saga.sg.gui;

import com.saga.sg.Saga;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SagaMenuTypes
{
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Saga.MOD_ID);

    public static final RegistryObject<MenuType<SagaAnvilMenu>> SAGA_ANVIL_MENU = registerMenuType(SagaAnvilMenu::new, "saga_anvil_menu");

    public static final RegistryObject<MenuType<BlacksmithStationMenu>> BLACKSMITH_STATION_MENU =
            registerMenuType(BlacksmithStationMenu::new, "blacksmith_station_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                 String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void registerMenus(IEventBus eventBus)
    {
        MENUS.register(eventBus);
    }
}
