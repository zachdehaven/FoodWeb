package group2.foodWeb.recipes;


public class PublishedRecipeList {

    private final int RECIPE_NOT_FOUND = -999;
    private final int DEFAULT_LIST_SIZE = 128;

    private Recipe[] recipes;
    private int recipeListSize;

    // Default constructor
    public PublishedRecipeList()
    {
        recipeListSize = DEFAULT_LIST_SIZE;
        recipes = new Recipe[ recipeListSize ];
    }
    // Copy constructor
    public PublishedRecipeList( PublishedRecipeList copy )
    {
        int index;

        recipeListSize = copy.recipeListSize;
        recipes = new Recipe[ recipeListSize ];

        for ( index = 0; index < recipeListSize; index++ )
        {
            recipes[ index ] = copy.recipes[ index ];
        }
    }

    public void addRecipe( Recipe newRecipe )
    {
        int insertIndex, attempts = 0;

        insertIndex = generateHash( newRecipe );

        if ( recipes[ insertIndex ] != null )
        {
            while ( recipes[ insertIndex ] != null && attempts < recipeListSize )
            {
                attempts++;

                // TODO: Resize (if deemed necessary)

                // increment index w/ quadratic, keep within bounds
                insertIndex += Math.pow( attempts, 2 );
                insertIndex %= recipeListSize;

            }
        }
    }

    public void removeRecipe( Recipe toBeRemoved )
    {
        int removeIndex;

        // get index of item
        removeIndex = findRecipeIndex( toBeRemoved );

        // check if item can be found
        if ( removeIndex != RECIPE_NOT_FOUND )
        {
            // set value to null
            recipes[ removeIndex ] = null;

        }
    }

    private int findRecipeIndex( Recipe recipeToFind )
    {
        int itemIndex, attempts = 0;

        itemIndex = generateHash( recipeToFind );

        // quadratic probing
        // loop while item not found and not at end of list
        while ( recipes[ itemIndex ].title
                .compareTo( recipeToFind.title ) != 0
                && attempts < recipeListSize )
        {
            attempts++;

            // reached end of bounds
            if ( attempts == recipeListSize )
            {
                return RECIPE_NOT_FOUND;
            }

            itemIndex += Math.pow( attempts, 2 );
            itemIndex %= recipeListSize;

        }

        return itemIndex;
    }

    private int generateHash( Recipe recipe )
    {
        int charIndex, hashIndex = 0;

        // sum ASCII character values - Josh Bloch Recipe for hash codes
        for ( charIndex = 0; charIndex < recipe.title.length(); charIndex++ )
        {
            hashIndex += (int)recipe.title.charAt( charIndex );
        }

        // convert to usable index
        hashIndex %= recipeListSize;

        return hashIndex;
    }

}
