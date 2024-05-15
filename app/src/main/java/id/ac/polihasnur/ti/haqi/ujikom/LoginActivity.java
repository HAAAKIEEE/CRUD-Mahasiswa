package id.ac.polihasnur.ti.haqi.ujikom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // Sesuaikan dengan informasi login admin yang valid
    private static final String ADMIN_USERNAME = "admin123";
    private static final String ADMIN_PASSWORD = "admin123";

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi tampilan
        usernameEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);
        getSupportActionBar().setTitle("Login");

        // Memberikan aksi klik pada tombol login
        loginButton.setOnClickListener(v -> {
            // Mengambil nilai yang dimasukkan pengguna
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Memeriksa apakah informasi login yang dimasukkan adalah informasi admin yang valid
            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                // Jika benar, arahkan ke activity admin
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Menutup LoginActivity agar tidak dapat diakses kembali setelah AdminActivity dimulai
            } else {
                // Jika salah, tampilkan pesan kesalahan
                Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
