package net.pinto.mythandmetal.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pinto.mythandmetal.MythandMetal;
import net.pinto.mythandmetal.item.customfun.ExplosiveSwordItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MythandMetal.MOD_ID);





    public static final RegistryObject<Item> EXPLOSIVESWORD = ITEMS.register("explosive_sword",
            ()-> new ExplosiveSwordItem(Tiers.IRON,0,2,new Item.Properties().durability(4)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

