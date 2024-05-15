package id.ac.polihasnur.ti.haqi.ujikom;
import static java.lang.Character.getType;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import id.ac.polihasnur.ti.haqi.ujikom.adapter.MahasiswaAdapter;
import id.ac.polihasnur.ti.haqi.ujikom.model.Mahasiswa;

public class MainList extends AppCompatActivity {

    public static final String MODE = "mode";
    public static final String MAHASISWA = "mahasiswa";
    public static final String MAHASISWAS = "mahasiswas";
    public static final String POSITION = "position";
    public static final int ADD_MODE = 0;
    public static final int VIEW_MODE = 1;
    public static final int EDIT_MODE = 2;
    private final List<Mahasiswa> mList = new ArrayList<>();
    private MahasiswaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getListFromSharedPreferences() != null) {
            // Ambil data dari SharedPreferences dan berikan ke mList
            mList.addAll(getListFromSharedPreferences());
        }
        mAdapter = new MahasiswaAdapter(mList, this);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(mAdapter);
        listView.setOnItemLongClickListener(this::onItemLongClick);
        listView.setOnItemClickListener(this::onItemClick);
        findViewById(R.id.fab).setOnClickListener(this::addItem);
    }
    private void addItem(View view) {
        Intent intent = new Intent(this, AddListMahasiswa.class);
        intent.putExtra(MainList.MODE, MainList.ADD_MODE);
        resultLauncher.launch(intent);
    }
    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, AddListMahasiswa.class);
        intent.putExtra(DetailActivity.MODE, DetailActivity.VIEW_MODE);
        intent.putExtra(MainList.MAHASISWA, mList.get(i));
        startActivity(intent);
    }
    private boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        CharSequence[] items = {"Edit", "Delete"};
        int[] checked = {-1};
        new AlertDialog.Builder(this)
                .setTitle("Your Action")
                .setSingleChoiceItems(items, checked[0], (dialogInterface, i1) -> checked[0] = i1)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Yes", (dialogInterface, i1) -> {
                    switch (checked[0]) {
                        case 0: //edit
                            Intent intent = new Intent(this, AddListMahasiswa.class);
                            intent.putExtra(MainList.MODE, MainList.EDIT_MODE);
                            intent.putExtra(MainList.MAHASISWA, mList.get(i));
                            intent.putExtra(MainList.POSITION, i);
                            resultLauncher.launch(intent);
                            break;
                        case 1: //delete
                            new AlertDialog.Builder(this)
                                    .setTitle("Confirm")
                                    .setMessage("Delete " + mList.get(i).toString() + "?")
                                    .setNegativeButton("Cancel", null)
                                    .setPositiveButton("Yes", (dialogInterface1, i2) -> {
                                        mList.remove(i);
                                        mAdapter.notifyDataSetChanged();
                                    }).show();
                            break;
                    }
                }).show();
        return true;
    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setTitle("View All Data Mahasiswa");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId() == R.id.mi_exit) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm")
                    .setMessage("Anda Yakin?")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Yes", (dialogInterface, i) -> finish()).show();
        }
        return super.onOptionsItemSelected(item);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new
            ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            if (result.getData().getIntExtra(MainList.MODE, -1) == MainList.ADD_MODE) {
                mList.add((Mahasiswa) result.getData().getSerializableExtra(MainList.MAHASISWA));
                mAdapter.notifyDataSetChanged();
            } else if (result.getData().getIntExtra(MainList.MODE, -1) == MainList.EDIT_MODE) {
                int pos = result.getData().getIntExtra(MainList.POSITION, -1);
                mList.set(pos, (Mahasiswa) result.getData().getSerializableExtra(MainList.MAHASISWA));
                mAdapter.notifyDataSetChanged();
            }
        }
    });
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MainList.MAHASISWAS, (Serializable) mList);
    }



        protected void onStop() {
            super.onStop();
            // Sebelum aplikasi dihentikan, simpan data ke SharedPreferences
            saveListToSharedPreferences(mList);
        }

        public ArrayList<Mahasiswa> getListFromSharedPreferences(){
            SharedPreferences sp = getSharedPreferences("mahasiswa_prefs", MODE_PRIVATE);
            // Ambil nilai String dari SharedPreferences dengan key MAHASISWAS
            String json = sp.getString(MAHASISWAS, null);
            // Konversi JSON ke bentuk ArrayList<Mahasiswa>
            return new Gson().fromJson(json, new TypeToken<ArrayList<Mahasiswa>>() {}.getType());
        }

        public void saveListToSharedPreferences(List<Mahasiswa> a) {
            SharedPreferences sp = getSharedPreferences("mahasiswa_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            String json = new Gson().toJson(a); // Konversi List<Mahasiswa> ke JSON
            editor.putString(MAHASISWAS, json).apply();
            // Simpan pada SharedPreferences dengan key MAHASISWAS.
        }


    }


