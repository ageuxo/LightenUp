package org.ageuxo.lightenup.platform;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.ageuxo.lightenup.LightenUpForge;
import org.ageuxo.lightenup.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.util.function.Supplier;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public <E extends Block> Supplier<E> registerBlock(String name, Supplier<E> block) {
        return LightenUpForge.BLOCKS.register(name, block);
    }

    @Override
    public <BE extends BlockEntity> Supplier<BlockEntityType<BE>> registerBlockEntity(String name, Supplier<BlockEntityType<BE>> beType) {
        return LightenUpForge.BLOCK_ENTITIES.register(name, beType);
    }

    @Override
    public <I extends Item> Supplier<I> registerItem(String name, Supplier<I> item) {
        return LightenUpForge.ITEMS.register(name, item);
    }
}