package group2.foodWeb.recipes;

public class Ingredient {

    private final int STORED = 101;
    private final int NEEDED = 202;

    // used for display and hash creation
    // should only be changed by Recipe to avoid issues with hashing
    public String ingredientName;

    // ingredient storage
    public double amount;

    // Init constructor: name only
    public Ingredient(String name) {
        ingredientName = name;
        amount = 0;
    }

    // Init constructor: name and amount
    public Ingredient(String name, double enteredAmount) {
        ingredientName = name;
        amount = enteredAmount;
    }

    // Copy constructor
    public Ingredient(Ingredient ingredientToCopy) {
        ingredientName = ingredientToCopy.ingredientName;
        amount = ingredientToCopy.amount;
    }

}
