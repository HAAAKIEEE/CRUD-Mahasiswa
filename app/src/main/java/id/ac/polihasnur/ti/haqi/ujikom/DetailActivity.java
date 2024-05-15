package id.ac.polihasnur.ti.haqi.ujikom;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import id.ac.polihasnur.ti.haqi.ujikom.adapter.MahasiswaAdapter;
import id.ac.polihasnur.ti.haqi.ujikom.model.Mahasiswa;

public class DetailActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_detail);
    }
}