package com.strio.bootstrap;

import com.strio.Constants.BootstrapConstants;
import com.strio.domain.*;
import com.strio.repositories.CategoryRepository;
import com.strio.repositories.RecipeRepository;
import com.strio.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

/**
 * Created by jt on 6/13/17.
 */
@Slf4j
@Component
public class RecipeBootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private UnitOfMeasure tblSpoon;
    private UnitOfMeasure teaSpoon;
    private UnitOfMeasure dash;
    private UnitOfMeasure pint;
    private UnitOfMeasure kgs;
    private UnitOfMeasure nos;
    private UnitOfMeasure cup;
    private Category american;
    private Category mexican;

    public RecipeBootstrap(CategoryRepository categoryRepository,
                           RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        saveCategories();
        saveUoms();
        loadUnitOfMeasure();
        loadCategory();
        loadGuacamoleRecipeRecipe();
        loadSpicyGrilledChickenTacosRecipe();
    }

    private void saveCategories() {
        saveCategory(BootstrapConstants.AMERICAN);
        saveCategory(BootstrapConstants.ITALIAN);
        saveCategory(BootstrapConstants.MEXICAN);
        saveCategory(BootstrapConstants.FAST_FOOD);
    }

    private void saveCategory(String value) {
        Category cat = new Category();
        cat.setDescription(value.toUpperCase());
        categoryRepository.save(cat);
    }

    private void saveUoms() {
        saveUOM(BootstrapConstants.TABLESPOON);
        saveUOM(BootstrapConstants.TEASPOON);
        saveUOM(BootstrapConstants.CUP);
        saveUOM(BootstrapConstants.NOS);
        saveUOM(BootstrapConstants.PINCH);
        saveUOM(BootstrapConstants.KGS);
        saveUOM(BootstrapConstants.EACH);
        saveUOM(BootstrapConstants.PINT);
        saveUOM(BootstrapConstants.DASH);
        saveUOM(BootstrapConstants.CLOVE);
    }

    private void saveUOM(String value) {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(value);
        unitOfMeasureRepository.save(uom);
    }

    private void loadCategory() {
        american = getValidCategory(BootstrapConstants.AMERICAN);
        mexican = getValidCategory(BootstrapConstants.MEXICAN);
    }

    private void loadUnitOfMeasure() {
        tblSpoon = getValidUnitOfMeasure(BootstrapConstants.TABLESPOON);
        teaSpoon = getValidUnitOfMeasure(BootstrapConstants.TEASPOON);
        nos = getValidUnitOfMeasure(BootstrapConstants.NOS);
        dash = getValidUnitOfMeasure(BootstrapConstants.DASH);
        cup = getValidUnitOfMeasure(BootstrapConstants.CUP);
        pint = getValidUnitOfMeasure(BootstrapConstants.PINT);
        kgs = getValidUnitOfMeasure(BootstrapConstants.KGS);
    }

    private UnitOfMeasure getValidUnitOfMeasure(String description) {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription(description);
        if (uom.isPresent())
            return uom.get();
        else throw new RuntimeException();
    }

    private Category getValidCategory(String description) {
        Optional<Category> category = categoryRepository.findByDescription(description);
        if (category.isPresent())
            return category.get();
        else throw new RuntimeException();
    }

    private void loadSpicyGrilledChickenTacosRecipe() {

        //Spicy Grilled Chicken Tacos
        //Serves 6 - 8
        //PreTime 20 mins
        //Cook Time 15 mins
        log.debug("Adding Grilled chicken Tacos Recipe");
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setServings(8);
        tacosRecipe.setCookTime(15);
        tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, " +
                "oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. " +
                "Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into " +
                "the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. " +
                "As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for " +
                "a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula." +
                " Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. " +
                "Serve with lime wedges.");


        Notes notes = new Notes();
        notes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. " +
                "I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting" +
                " through the house.");
        tacosRecipe.setNotes(notes);

        tacosRecipe.getCategories().add(american);
        tacosRecipe.getCategories().add(mexican);

        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", BigDecimal.valueOf(1), tblSpoon));
        tacosRecipe.addIngredient(new Ingredient("ancho chili powder", BigDecimal.valueOf(2), tblSpoon));
        tacosRecipe.addIngredient(new Ingredient("dried oregano", BigDecimal.valueOf(1), teaSpoon));
        tacosRecipe.addIngredient(new Ingredient("dried cumin", BigDecimal.valueOf(1), teaSpoon));
        tacosRecipe.addIngredient(new Ingredient("sugar", BigDecimal.valueOf(1), teaSpoon));
        tacosRecipe.addIngredient(new Ingredient("salt", BigDecimal.valueOf(1 / 2), teaSpoon));
        tacosRecipe.addIngredient(new Ingredient("clove garlic, finely chopped", BigDecimal.valueOf(1), pint));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zest", BigDecimal.valueOf(1), tblSpoon));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", BigDecimal.valueOf(3), tblSpoon));
        tacosRecipe.addIngredient(new Ingredient("olive oil", BigDecimal.valueOf(2), tblSpoon));
        tacosRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs", BigDecimal.valueOf(11 / 4), kgs));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillas", BigDecimal.valueOf(8), nos));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", BigDecimal.valueOf(3), tblSpoon));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", BigDecimal.valueOf(2), tblSpoon));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", BigDecimal.valueOf(4), teaSpoon));
        tacosRecipe.addIngredient(new Ingredient("pint cherry tomatoes, halved", BigDecimal.valueOf(1 / 2), pint));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", BigDecimal.valueOf(1 / 4), pint));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", BigDecimal.valueOf(1), pint));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", BigDecimal.valueOf(1 / 2), cup));

        recipeRepository.save(tacosRecipe);
        System.out.println("Recipe in bootstrap " + recipeRepository.count());
        log.debug("Recipe in bootstrap " + recipeRepository.count());

    }

    private void loadGuacamoleRecipeRecipe() {
        //Guacamole Recipe
        //Serves 2 - 4
        //PreTime 10 mins
        log.debug("Adding Guacamole recipe");

        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(10);
        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setSource("Simply Recipes");
        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half." +
                " Remove the pit. Score the inside of the avocado with a blunt knife and " +
                "scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. " +
                "(Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. " +
                "The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary " +
                "individually in their hotness. So, start with a half of one chili pepper and add " +
                "to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in " +
                "the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped " +
                "tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, \n" +
                "place plastic wrap on the surface of the guacamole and press down to cover \n" +
                "it and to prevent air reaching it. (The oxygen in the air causes oxidation which will \n" +
                "turn the guacamole brown.) Refrigerate until ready to serve");

        Notes notes = new Notes();
        notes.setRecipeNotes("The word “guacamole”, \n" +
                "and the dip, are both originally from Mexico,\n" +
                "where avocados have been cultivated for thousands of years. \n" +
                "The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).");

        guacamoleRecipe.getCategories().add(mexican);
        guacamoleRecipe.getCategories().add(american);
        guacamoleRecipe.setNotes(notes);

        guacamoleRecipe.addIngredient(new Ingredient("Ripe Avocados", BigDecimal.valueOf(2), tblSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("Salt", BigDecimal.valueOf(1 / 4), teaSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("Fresh lime juice or lemon juice", BigDecimal.valueOf(1), tblSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(2), tblSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("Serrano chilies, stems and seeds removed, minced", BigDecimal.valueOf(2), teaSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2), tblSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("A dash of freshly grated black pepper", BigDecimal.valueOf(1), dash));
        guacamoleRecipe.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed, chopped", BigDecimal.valueOf(1 / 2), tblSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("Red radishes or jicama, to garnish", BigDecimal.valueOf(1), tblSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("Tortilla chips, to serve", BigDecimal.valueOf(2), tblSpoon));

        recipeRepository.save(guacamoleRecipe);
        System.out.println("Recipe in bootstrap " + recipeRepository.count());
        log.debug("Recipe in bootstrap " + recipeRepository.count());

      //  System.out.println("Ingredients in bootstrap " + recipeRepository.findById("1").get().getIngredients().size());
    }
}
