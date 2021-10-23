package group2.foodWeb.recipes;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class IngredientList {

    private final int INGREDIENT_NOT_FOUND = -999;
    private final int DEFAULT_LIST_SIZE = 128;

    public Ingredient[] ingredients;
    private int ingredientListSize;

    // Default constructor
    public IngredientList()
    {
        ingredientListSize = DEFAULT_LIST_SIZE;
        ingredients = new Ingredient[ ingredientListSize ];
    }
    // Copy constructor
    public IngredientList(IngredientList copy )
    {
        int index;

        ingredientListSize = copy.ingredientListSize;
        ingredients = new Ingredient[ ingredientListSize ];

        for ( index = 0; index < ingredientListSize; index++ )
        {
            ingredients[ index ] = copy.ingredients[ index ];
        }
    }

    // TODO: initialization constructor

    public void addIngredient( Ingredient newIngredient )
    {
        int insertIndex, attempts = 0;

        insertIndex = generateHash( newIngredient );

        if ( ingredients[ insertIndex ] != null )
        {
            while ( ingredients[ insertIndex ] != null && attempts < ingredientListSize )
            {
                attempts++;

                // TODO: Resize (if deemed necessary)

                // increment index w/ quadratic, keep within bounds
                insertIndex += Math.pow( attempts, 2 );
                insertIndex %= ingredientListSize;

            }
        }
    }

    public void removeIngredient( Ingredient toBeRemoved )
    {
        int removeIndex;

        // get index of item
        removeIndex = findIngredientIndex( toBeRemoved );

        // check if item can be found
        if ( removeIndex != INGREDIENT_NOT_FOUND )
        {
            // set value to null
            ingredients[ removeIndex ] = null;

        }
    }

    public void changeIngredientName( Ingredient ingredientToChange, String newName )
    {
        Ingredient changedIngredient;

        changedIngredient = new Ingredient( ingredientToChange );
        changedIngredient.ingredientName = newName;

        removeIngredient( ingredientToChange );
        addIngredient( changedIngredient );
    }

    private int findIngredientIndex( Ingredient ingredientToFind )
    {
        int itemIndex, attempts = 0;

        itemIndex = generateHash( ingredientToFind );

        // quadratic probing
        // loop while item not found and not at end of list
        while ( ingredients[ itemIndex ].ingredientName
                .compareTo( ingredientToFind.ingredientName ) != 0
                && attempts < ingredientListSize )
        {
            attempts++;

            // reached end of bounds
            if ( attempts == ingredientListSize )
            {
                return INGREDIENT_NOT_FOUND;
            }

            itemIndex += Math.pow( attempts, 2 );
            itemIndex %= ingredientListSize;

        }

        return itemIndex;
    }

    public ArrayList getNameList()
    {
        ArrayList<String> nameArray = new ArrayList<>();
        int i;

        for ( i = 0; i < ingredientListSize; i++ )
        {
            if ( ingredients[i] != null )
            {
                nameArray.add( ingredients[i].ingredientName );
            }
        }

        return nameArray;
    }

    public ArrayList getAmtList()
    {
        ArrayList<Double> amtArray = new ArrayList<>();
        int i;

        for ( i = 0; i < ingredientListSize; i++ )
        {
            if ( ingredients[i] != null )
            {
                amtArray.add( ingredients[i].amount);
            }
        }

        return amtArray;
    }

    private int generateHash( Ingredient ingredient )
    {
        int charIndex, hashIndex = 0;

        // sum ASCII character values - Josh Bloch Recipe for hash codes
        for ( charIndex = 0; charIndex < ingredient.ingredientName.length(); charIndex++ )
        {
            hashIndex += (int)ingredient.ingredientName.charAt( charIndex );
        }

        // convert to usable index
        hashIndex %= ingredientListSize;

        return hashIndex;
    }

}