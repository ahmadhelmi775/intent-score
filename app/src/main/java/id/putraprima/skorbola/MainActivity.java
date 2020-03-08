package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String USER_KEY = "user";
    private EditText hometeaminput;
    private EditText awayteaminput;
    private ImageView avatarImage;
    private Uri imageUri;
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            hometeaminput = findViewById(R.id.home_team);
            awayteaminput = findViewById(R.id.away_team);
            avatarImage = findViewById(R.id.home_logo);
            avatarImage = findViewById(R.id.away_logo);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == RESULT_CANCELED){
                return;
            }
            if(requestCode == GALLERY_REQUEST_CODE){
                if(data != null){
                    try{
                        imageUri = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        avatarImage.setImageBitmap(bitmap);
                    } catch (IOException e){
                        Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        }

        public void handleChangeAvatar(View view) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, GALLERY_REQUEST_CODE);
        }

    //5. Next Button Pindah Ke MatchActivity
    public void handleNext(View view) {
        String awayteam = awayteaminput.getText().toString();
        String hometeam = hometeaminput.getText().toString();

        Uri image = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            image = imageUri.normalizeScheme();
        }
        if (TextUtils.isEmpty(hometeaminput.getText())){
            hometeaminput.setError("Silahkan diisi terlebih dahulu");
        } else if (TextUtils.isEmpty(awayteaminput.getText())){
            awayteaminput.setError("Silahkan diisi terlebih dahulu");
        } else if(imageUri == null){
            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
        }
        User user = new User(hometeam, awayteam, image);
        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra(USER_KEY, user);
        startActivity(intent);
    }

}

    //2. Validasi Input Away Team

        //3. Ganti Logo Home Team

        //4. Ganti Logo Away Team




