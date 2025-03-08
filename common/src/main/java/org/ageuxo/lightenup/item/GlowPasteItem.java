package org.ageuxo.lightenup.item;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.ParametersAreNonnullByDefault;

import static net.minecraft.world.level.block.MultifaceBlock.getFaceProperty;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GlowPasteItem extends BlockItem {

    public GlowPasteItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos clickPos = context.getClickedPos();
        Direction clickedFace = context.getClickedFace();
        Level level = context.getLevel();
        ItemStack itemstack = context.getItemInHand();
        BlockState clickState = level.getBlockState(clickPos);
        Direction placeDirection = clickedFace.getOpposite();
        BooleanProperty faceProperty = getFaceProperty(placeDirection);
        if (clickState.is(getBlock()) && !clickState.getValue(faceProperty)) {
            BlockPos supportPos = clickPos.relative(placeDirection);
            BlockState supportState = level.getBlockState(supportPos);
            if (supportState.isFaceSturdy(level, supportPos, clickedFace) && level.setBlockAndUpdate(clickPos, clickState.setValue(faceProperty, true))) {
                itemstack.shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        return super.useOn(context);
    }
}
