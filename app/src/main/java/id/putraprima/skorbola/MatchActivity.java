package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {
    public static final String USER_KEY = "user";
    private TextView homeText;
    private TextView awayText;
    private ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeText = findViewById(R.id.home_team);
        awayText = findViewById(R.id.away_team);
        gambar = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            User data = getIntent().getParcelableExtra(USER_KEY);
            homeText.setText(data.getHometeam());
            awayText.setText(data.getAwayteam());
            try {
                Bitmap image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getImage());
                gambar.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
