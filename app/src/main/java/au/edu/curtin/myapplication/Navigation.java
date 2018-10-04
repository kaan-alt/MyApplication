/*package au.edu.curtin.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Navigation extends Activity {
    //Buttons
    private Button northButton = (Button) findViewById(R.id.northButton);
    private Button eastButton = (Button) findViewById(R.id.eastButton);
    private Button southButton = (Button) findViewById(R.id.southButton);
    private Button westButton = (Button) findViewById(R.id.westButton);
    private Button optionButton = (Button) findViewById(R.id.optionButton);

    //Text
    private EditText descriptionDisplay = (EditText) findViewById(R.id.descriptionDisplay);
    private EditText townDisplay = (EditText) findViewById(R.id.townDisplay);
    private EditText healthDisplay = (EditText) findViewById(R.id.healthDisplay);
    private EditText equipmentMassDisplay = (EditText) findViewById(R.id.equipmentMassDisplay);
    private EditText cashDisplay = (EditText) findViewById(R.id.cashDisplay);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        GameData theGameMap = new GameData();

        Player thePlayer = new Player(0, 0, 0, 100.0, 0, new List<Equipment>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Equipment> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(Equipment equipment) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Equipment> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Equipment> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Equipment get(int i) {
                return null;
            }

            @Override
            public Equipment set(int i, Equipment equipment) {
                return null;
            }

            @Override
            public void add(int i, Equipment equipment) {

            }

            @Override
            public Equipment remove(int i) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Equipment> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Equipment> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Equipment> subList(int i, int i1) {
                return null;
            }
        });

        healthDisplay.setText(Double.toString(thePlayer.getPlayerHealth()));
        cashDisplay.setText(thePlayer.getCash());
        equipmentMassDisplay.setText(Double.toString(thePlayer.getEquipmentMass()));

        descriptionDisplay.setText(thePlayer.getColLocation() + ", " + thePlayer.getRowLocation());

        if(theGameMap.grid[thePlayer.getColLocation()][thePlayer.getRowLocation()].isTown())
        {
            townDisplay.setText("Town");
        }
        else
        {
            townDisplay.setText("Wilderness");
        }







    }

}*/
