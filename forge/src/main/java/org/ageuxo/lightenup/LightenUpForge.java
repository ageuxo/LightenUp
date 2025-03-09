package org.ageuxo.lightenup;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.ageuxo.lightenup.data.ModBlockProvider;
import org.ageuxo.lightenup.data.ModRecipeProvider;

import static org.ageuxo.lightenup.LightenUp.MOD_ID;

@Mod(MOD_ID)
public class LightenUpForge {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);

    public LightenUpForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        LightenUp.init();

        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        BLOCK_ENTITIES.register(modBus);

        modBus.addListener(this::onGatherData);
    }

    public void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(true, new ModBlockProvider(generator.getPackOutput(), MOD_ID, event.getExistingFileHelper()));
        generator.addProvider(true, new ModRecipeProvider(generator.getPackOutput()));
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents{
        @SubscribeEvent
        public static void onBuildCreativeModeTabEvent(BuildCreativeModeTabContentsEvent event){
            if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
                ITEMS.getEntries().forEach((item)->event.accept(item.get()));
            }
        }
        @SubscribeEvent
        public static void onRegisterBERenderer(EntityRenderersEvent.RegisterRenderers event){

        }
    }
}
