package org.ageuxo.lightenup;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class LightenUpFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        LightenUp.init();

        BlockRenderLayerMap.INSTANCE.putBlock(LightenUp.GLOW_PASTE_BLOCK.get(), RenderType.translucent());
    }
}
