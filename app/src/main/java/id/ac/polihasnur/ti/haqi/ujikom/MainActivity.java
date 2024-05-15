package id.ac.polihasnur.ti.haqi.ujikom;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button lihatButton,inputButton,informasiButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        informasiButton = (Button) findViewById(R.id.btn_info);
        lihatButton = (Button) findViewById(R.id.btn_lihat);
    inputButton = (Button)findViewById(R.id.btn_input);
        inputButton.setVisibility(View.GONE);

       onClick();

    }
    public void onClick(){
        informasiButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        });
        lihatButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainList.class);
            startActivity(intent);
        });

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
                    .setMessage("Close App?")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Yes", (dialogInterface, i) -> finish()).show();
        }
        return super.onOptionsItemSelected(item);
    }
}