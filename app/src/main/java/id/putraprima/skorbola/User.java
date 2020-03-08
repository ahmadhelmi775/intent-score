package id.putraprima.skorbola;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

public class User implements Parcelable {
    private String hometeam;
    private String awayteam;
    private Uri image;

    public User(String hometeam, String awayteam,Uri image) {
        this.hometeam = hometeam;
        this.awayteam = awayteam;
        this.image = image;
    }

    public String getHometeam() {
        return hometeam;
    }

    public void setHometeam(String hometeam) {
        this.hometeam = hometeam;
    }

    public String getAwayteam() {
        return awayteam;
    }

    public void setAwayteam(String awayteam) {
        this.awayteam = awayteam;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hometeam);
        dest.writeString(this.awayteam);
        dest.writeParcelable(this.image, flags);
    }

    protected User(Parcel in) {
        this.hometeam = in.readString();
        this.awayteam = in.readString();
        this.image = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
