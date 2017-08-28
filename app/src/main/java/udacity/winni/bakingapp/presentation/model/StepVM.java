package udacity.winni.bakingapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by winniseptiani on 8/2/17.
 */

public class StepVM implements Parcelable {

    private int id;

    private String shortDescription;

    private String description;

    private String videoURL;

    private int clicked = 0;

    public StepVM() {

    }

    protected StepVM(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        clicked = in.readInt();
    }

    public static final Creator<StepVM> CREATOR = new Creator<StepVM>() {
        @Override
        public StepVM createFromParcel(Parcel in) {
            return new StepVM(in);
        }

        @Override
        public StepVM[] newArray(int size) {
            return new StepVM[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeInt(clicked);
    }

    public int getClicked() {
        return clicked;
    }

    public void setClicked(int clicked) {
        this.clicked = clicked;
    }
}
