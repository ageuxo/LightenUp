package org.ageuxo.lightenup;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.ageuxo.lightenup.block.GlowPasteBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

import static org.ageuxo.lightenup.platform.Services.PLATFORM;

public class LightenUp {

    public static final String MOD_ID = "lighten_up";
    private static final String MOD_NAME = "Lighten Up";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final Supplier<GlowPasteBlock> GLOW_PASTE_BLOCK = PLATFORM.registerBlock("glow_paste", ()-> new GlowPasteBlock(BlockBehaviour.Properties.of().noOcclusion().lightLevel(GlowPasteBlock::stateToLightLevel)));

    public static final Supplier<BlockItem> GLOW_PASTE_ITEM = PLATFORM.registerItem("glow_paste", ()-> new BlockItem(GLOW_PASTE_BLOCK.get(), new Item.Properties()));


    public static void init() {

    }

    public static ResourceLocation modRL(String id){
        return new ResourceLocation(MOD_ID, id);
    }

}