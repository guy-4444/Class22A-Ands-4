package com.guy.class22a_ands_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MSPS.getInstance().putString("STUDENT_1", "Ram");
        MSPS.getInstance().putString("STUDENT_2", "Daniel Hay");

        Regular_MSP.getInstance().putString("STUDENT_3", "Michael");
        Regular_MSP.getInstance().putString("STUDENT_4", "Ahmad");

        String a = MSPS.getInstance().getString("STUDENT_1", "NA");
        String b = MSPS.getInstance().getString("STUDENT_2", "NA");
        String c = MSPS.getInstance().getString("STUDENT_3", "NA");

        Log.d("pttt", "a= " + a);
        Log.d("pttt", "b= " + b);
        Log.d("pttt", "c= " + c);

        Car car1 = new Car().setModel("Mitsubishi").setLicense("8715620");
        MSPS.getInstance().putObject("CAR_1", car1);

        Car car2 = MSPS.getInstance().getObject("CAR_1", Car.class);
        Log.d("pttt", "car= " + car2.getModel());


        ArrayList<Car> cars1 = new ArrayList<>();
        cars1.add(new Car().setModel("Mitsubishi").setLicense("8715620"));
        cars1.add(new Car().setModel("Mercedes").setLicense("1122233"));
        cars1.add(new Car().setModel("Ferrari").setLicense("22233222"));

        MSPS.getInstance().putArray("ALL_CARS", cars1);
        ArrayList<Car> cars2 = MSPS.getInstance().getArray("ALL_CARS", new TypeToken<ArrayList<Car>>() {});
        Log.d("pttt", "cars= " + cars2.size());

        HashMap<String, Car> map1 = new HashMap<>();
        for (Car car : cars1) {
            map1.put(car.getLicense(), car);
        }
        MSPS.getInstance().putHashMap("ALL_CARS_MAP", map1);

        HashMap<String, Car> map2 = MSPS.getInstance().getHashMap("ALL_CARS_MAP", new TypeToken<HashMap<String, Car>>() {});
        Log.d("pttt", "map= " + map2.size());

        HashMap<String, Car> map3 = MSPS.getInstance().getHashMap("ALL_CARS_MAP", String.class, Car.class);
        Log.d("pttt", "map= " + map3.size());


        HashMap<String, HashMap<String, Car>> bigMap = new HashMap<>();
        bigMap.put("FIRST", map1);
        MSPS.getInstance().putBigHashMap("BIG_MAP", bigMap);

        HashMap<String, HashMap<String, Car>> bigMap2 = MSPS.getInstance().getBigHashMap("BIG_MAP", String.class, String.class, Car.class);
        Log.d("pttt", "map= " + bigMap2.size());
    }
}