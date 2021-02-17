package com.strio.services;

import com.strio.domain.Recipe;
import com.strio.commands.RecipeCommand;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(String id);
    RecipeCommand findCommandById(String id);
    RecipeCommand  saveRecipeCommand(RecipeCommand command);
    void deleteById(String idToDelete);
}
