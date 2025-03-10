package org.ageuxo.lightenup.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.ageuxo.lightenup.LightenUp;

public class ModBlockProvider extends BlockStateProvider {
    public ModBlockProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        glowPasteWithItem();
        // Particle model
        models().withExistingParent("transient_paste", new ResourceLocation("minecraft", "block/barrier"))
                        .texture("particle", LightenUp.modRL("item/transient_paste"));
        itemModels().basicItem(LightenUp.TRANSIENT_PASTE_ITEM.get());
    }

    private void glowPasteWithItem() {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(LightenUp.GLOW_PASTE_BLOCK.get());
        ResourceLocation blockTexture = LightenUp.modRL("block/glow_paste");
        BlockModelBuilder model = models().withExistingParent("glow_paste", new ResourceLocation("minecraft", "block/vine"))
                .texture("particle", blockTexture)
                .texture("vine", blockTexture)
                .renderType("translucent");
        for (var entry : PipeBlock.PROPERTY_BY_DIRECTION.entrySet()) {
            builder.part()
                    .modelFile(model)
                        .rotationY((int) entry.getKey().getOpposite().toYRot())
                        .rotationX(entry.getKey().getStepY() * -90)
                        .addModel()
                    .condition(entry.getValue(), true)
                    .end();
        }
        itemModels().basicItem(LightenUp.GLOW_PASTE_ITEM.get());
    }

}
