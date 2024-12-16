package net.pinto.mythandmetal.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pinto.mythandmetal.MythandMetal;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MythandMetal.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MYTHANDMETALCRTAB = CREATIVE_MODE_TABS.register("mythandmetalcrtab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EXPLOSIVESWORD.get()))
                    .title(Component.translatable("creativetab.mythandmetalcrtab"))
                    .displayItems((pParameters, pOutput) -> {



                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
