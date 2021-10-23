package group2.foodWeb;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import group2.foodWeb.databinding.FragmentSecondBinding;
import group2.foodWeb.recipes.Ingredient;
import group2.foodWeb.recipes.IngredientList;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private ListView ingredientNameView;
    private ListView ingredientAmtView;

    private ArrayList<String> ingredientNames;
    private ArrayList<Double> ingredientAmounts;

    private IngredientList ingredientList;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter arrayAdapter_names, arrayAdapter_amts;

        ingredientNames = new ArrayList<>();
        ingredientAmounts = new ArrayList<>();

        ingredientNames.add("Banana");
        ingredientAmounts.add(1.0);

        ingredientNameView = binding.listPublishIngredientsList;
        ingredientAmtView = binding.listPublishIngredientAmts;

        ingredientList = new IngredientList();

        arrayAdapter_names = new ArrayAdapter( getActivity(), android.R.layout.simple_list_item_1, ingredientNames );
        arrayAdapter_amts = new ArrayAdapter( getActivity(), android.R.layout.simple_list_item_1, ingredientAmounts );

        ingredientNameView.setAdapter(arrayAdapter_names);
        ingredientAmtView.setAdapter(arrayAdapter_amts);

        binding.buttonPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredientName, tempAmt;
                double amt;

                ingredientName = binding.textIngredientName.getText().toString();

                tempAmt = binding.textIngredientAmt.getText().toString();
                amt = Double.parseDouble(tempAmt);

                ingredientList.addIngredient(new Ingredient(ingredientName, amt));

                ingredientNames.add(ingredientName);
                ingredientAmounts.add(amt);

                arrayAdapter_amts.notifyDataSetChanged();
                arrayAdapter_names.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}