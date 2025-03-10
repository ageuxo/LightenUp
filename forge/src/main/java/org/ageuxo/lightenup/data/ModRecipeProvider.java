package org.ageuxo.lightenup.data;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.ageuxo.lightenup.LightenUp;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        smeltingResultFromBase(writer, LightenUp.GLOW_PASTE_ITEM.get(), Items.GLOW_BERRIES);

        surroundedIngredient(writer, LightenUp.TRANSIENT_PASTE_ITEM.get(), 16, Items.ENDER_PEARL, LightenUp.GLOW_PASTE_ITEM.get());
    }

    private static void surroundedIngredient(Consumer<FinishedRecipe> writer, BlockItem result, int count, Item centerIngredient, BlockItem outerIngredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result, count)
                .define('O', outerIngredient)
                .define('X', centerIngredient)
                .pattern("OOO")
                .pattern("OXO")
                .pattern("OOO")
                .unlockedBy(getHasName(outerIngredient), has(outerIngredient))
                .save(writer);
    }
}
