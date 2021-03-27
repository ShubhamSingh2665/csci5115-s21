package io.moonen.charles.greengrocery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
>>>>>>> 862b062121b849456ed1a8d59fe98ed2043cefee

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

<<<<<<< HEAD
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.moonen.charles.greengrocery.ui.gardenessentials.PlantRow;
=======
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.moonen.charles.greengrocery.ReceiptContentManagement.CSVFile;
import io.moonen.charles.greengrocery.ReceiptContentManagement.DataAdapter;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Product;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Receipt;
import io.moonen.charles.greengrocery.ui.receipt_scorecard.ReceiptScorecardFragment;
>>>>>>> 862b062121b849456ed1a8d59fe98ed2043cefee

public class MainActivity extends AppCompatActivity {
    Receipt receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<PlantRow> plants =  new ArrayList<>(Arrays.asList(
                new PlantRow("Evergreen Tree", 100, "Plant", R.id.evergreenView, R.drawable.evergreen1, R.drawable.evergreen2, R.drawable.evergreen3),
                new PlantRow("Maple Tree", 50,"Plant", R.id.mapleView, R.drawable.maple1, R.drawable.maple2, R.drawable.maple3),
                new PlantRow("Berry Bush", 20, "Plant", R.id.bushView, R.drawable.berrybush1, R.drawable.berrybush2, R.drawable.berrybush3),
                new PlantRow("Tulip", 10, "Plant", R.id.tulipView, R.drawable.tulip1, R.drawable.tulip2, R.drawable.tulip3)
        ));

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("points", 1000);

        Gson gson = new Gson();
        String jsonText = gson.toJson(plants);
        editor.putString("plants", jsonText);

        editor.apply();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_rscan, R.id.navigation_garden, R.id.navigation_pscan)
//                .build();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
        NavigationUI.setupWithNavController(navView, navController);
        //get example receipt data from csv file
        InputStream inputStream = getResources().openRawResource(R.raw.fake_database);
        CSVFile csvFile = new CSVFile(inputStream);
        List rowList = csvFile.readFile();

        //create list of products for example receipt
        List receiptProducts = new ArrayList<>();
        DataAdapter adapter;
        int numRows = rowList.size();
        for (int i = 1; i < numRows; i++){  //skip first row
            adapter = new DataAdapter((String[]) rowList.get(i));
            Product currProduct = adapter.createProduct();
            receiptProducts.add(currProduct);
        }

        //create example receipt
        receipt = new Receipt(receiptProducts);

        //test
        //String testing = ((Product) receiptProducts.get(1)).getName();

//        //ADDED BUTTON FOR TESTING
//        Button test = findViewById(R.id.test);
//        test.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                //test
//                //Toast.makeText(getApplicationContext(), testing, Toast.LENGTH_LONG).show();
//
//                ReceiptScorecardFragment frag = new ReceiptScorecardFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container,frag);
//                transaction.commit();
//            }
//        });
    }
    //returns example receipt
    public Receipt getReceiptData(){
        return receipt;
    }
}
