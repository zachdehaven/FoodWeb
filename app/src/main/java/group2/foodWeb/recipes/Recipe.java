package group2.foodWeb.recipes;

import group2.foodWeb.user.User;

public class Recipe {

    private final int INGREDIENT_NOT_FOUND = -999;
    private final int DEFAULT_LIST_SIZE = 128;

    private IngredientList ingredients;

    public String title;
    public String description;
    public User author;

    // Default constructor
    public Recipe()
    {
        ingredients = new IngredientList();
    }

    // Copy constructor
    public Recipe( Recipe copy )
    {
        ingredients = new IngredientList( copy.ingredients );
        title = copy.title;
        description = copy.description;
        author = copy.author;

    }

    // TODO: initialization constructor

    public void addIngredient( Ingredient newIngredient )
    {
        ingredients.addIngredient( newIngredient );
    }

    public void removeIngredient( Ingredient toBeRemoved )
    {
        ingredients.removeIngredient( toBeRemoved );
    }

    public void changeIngredientName( Ingredient ingredientToChange, String newName )
    {
        Ingredient changedIngredient;

        changedIngredient = new Ingredient( ingredientToChange );
        changedIngredient.ingredientName = newName;

        removeIngredient( ingredientToChange );
        addIngredient( changedIngredient );
    }

}
