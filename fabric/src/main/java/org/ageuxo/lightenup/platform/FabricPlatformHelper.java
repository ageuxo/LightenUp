package org.ageuxo.lightenup.platform;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.ageuxo.lightenup.LightenUp;
import org.ageuxo.lightenup.RegistryObject;
import org.ageuxo.lightenup.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <E extends Block> Supplier<E> registerBlock(String name, Supplier<E> block) {
        return registerSupplier(BuiltInRegistries.BLOCK, name, block);
    }

    @Override
    public <BE extends BlockEntity> Supplier<BlockEntityType<BE>> registerBlockEntity(String name, Supplier<BlockEntityType<BE>> beType) {
        return registerSupplier(BuiltInRegistries.BLOCK_ENTITY_TYPE, name, beType);
    }

    @Override
    public <I extends Item> Supplier<I> registerItem(String name, Supplier<I> item) {
        return registerSupplier(BuiltInRegistries.ITEM, name, item);
    }

    private static <T, R extends Registry<? super T>> Supplier<T> registerSupplier(R registry, String id, Supplier<T> object) {
        //noinspection unchecked
        final T registeredObject = Registry.register((Registry<T>)registry, LightenUp.modRL(id), object.get());
        return new RegistryObject<>(registeredObject);
    }
}
