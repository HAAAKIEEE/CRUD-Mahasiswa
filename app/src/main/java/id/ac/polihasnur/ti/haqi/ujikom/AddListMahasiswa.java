package id.ac.polihasnur.ti.haqi.ujikom;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import id.ac.polihasnur.ti.haqi.ujikom.model.Mahasiswa;

public class AddListMahasiswa extends AppCompatActivity {
    private EditText etId, etName, etAlamat, etJenkel,etTgll;
    private Button btSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_mahasiswa);
        etId =(EditText) findViewById(R.id.et_id);
        etName =(EditText) findViewById(R.id.et_nama);
        etTgll =(EditText) findViewById(R.id.et_tgll);
        etJenkel =(EditText) findViewById(R.id.et_jenkel);
        etAlamat =(EditText) findViewById(R.id.et_alamat);
        btSave = (Button) findViewById(R.id.btn_save);
        btSave.setOnClickListener(this::saveForm);
    }

    private void saveForm(View view) {
        if (etId.getText().toString().isEmpty() || etName.getText().toString().isEmpty()) {
            Snackbar.make(view, "id and name cannot be empty", Snackbar.LENGTH_SHORT).show();
            return;
        }

        Mahasiswa mahasiswa = new Mahasiswa(
                Integer.parseInt(etId.getText().toString()),
                etName.getText().toString(),
                etTgll.getText().toString(),
                etJenkel.getText().toString(),
                etAlamat.getText().toString());
        assert getIntent().getExtras() != null;
        if (getIntent().getIntExtra(MainList.MODE, -1) == MainList.ADD_MODE) {
            Intent intent = new Intent();

            intent.putExtra(MainList.MODE, MainList.ADD_MODE);
            intent.putExtra(MainList.MAHASISWA, mahasiswa);
            setResult(RESULT_OK, intent); // Mengatur hasil dan data yang akan dikirim kembali
        } else if (getIntent().getIntExtra(MainList.MODE, -1) == MainList.EDIT_MODE) {
            int pos = getIntent().getIntExtra(MainList.POSITION, -1);
            Intent intent = new Intent();

            intent.putExtra(MainList.MODE, MainList.EDIT_MODE);
            intent.putExtra(MainList.MAHASISWA, mahasiswa);
            intent.putExtra(MainList.POSITION, pos);
            setResult(RESULT_OK, intent); // Mengatur hasil dan data yang akan dikirim kembali
        }
        finish();
    }
    protected void onStart() {
        super.onStart();

        assert getIntent().getExtras() != null;

        if (getIntent().getIntExtra(MainList.MODE, -1) == MainList.ADD_MODE) {
            getSupportActionBar().setTitle("Add Mahasiswa");

        } else if (getIntent().getIntExtra(MainList.MODE, -1) == MainList.VIEW_MODE)
        {
            getSupportActionBar().setTitle("View Data Mahasiswa");

            Mahasiswa mahasiswa = (Mahasiswa) getIntent().getSerializableExtra(MainList.MAHASISWA);
            assert mahasiswa != null;
            etId.setText(String.valueOf(mahasiswa.getId()));
            etName.setText(mahasiswa.getNama());
            etTgll.setText(mahasiswa.getTanggall());
            etJenkel.setText(mahasiswa.getJenisKelamin());
            etAlamat.setText(mahasiswa.getAlamat());
            etId.setFocusable(false);
            etName.setFocusable(false);
            etTgll.setFocusable(false);
            etJenkel.setFocusable(false);
            etAlamat.setFocusable(false);
            btSave.setVisibility(View.GONE);
        } else if (getIntent().getIntExtra(MainList.MODE, -1) == MainList.EDIT_MODE)
        {
            Mahasiswa mahasiswa = (Mahasiswa) getIntent().getSerializableExtra(MainList.MAHASISWA);
            assert mahasiswa != null;
            etId.setText(String.valueOf(mahasiswa.getId()));
            etName.setText(mahasiswa.getNama());
            etTgll.setText(mahasiswa.getTanggall());
            etJenkel.setText(mahasiswa.getJenisKelamin());
            etAlamat.setText(mahasiswa.getAlamat());
            etId.setFocusable(false);
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = getSharedPreferences("my_prefs", MODE_PRIVATE).edit();
        editor.putString("key_data", "nilai_data");
        editor.apply();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId() == R.id.mi_exit) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm")
                    .setMessage("Anda yakin Back?")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        // Menutup aplikasfinish();
                    }).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}