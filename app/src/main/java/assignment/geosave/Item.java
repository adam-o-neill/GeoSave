package assignment.geosave;

import java.io.Serializable;

/**
 * Created by HP Lab1 on 27/11/2016.
 */
public class Item implements Serializable {

    private String image;
    private String mLatitudeText;
    private String mLongitudeText;
    private String description;
    private String mUserId;

    public Item() {}

    public Item(String mLatitudeText, String mLongitudeText, String image, String description, String mUserId) {
        this.mLatitudeText = mLatitudeText;
        this.mLongitudeText = mLongitudeText;
        this.image = image;
        this.description = description;
        this.mUserId = mUserId;
    }

    public String getmLatitudeText() {
        return mLatitudeText;
    }

    public void setmLatitudeText(String mLatitudeText) {
        this.mLatitudeText = mLatitudeText;
    }

    public String getmLongitudeText() {
        return mLongitudeText;
    }

    public void setmLongitudeText(String mLongitudeText) {
        this.mLongitudeText = mLongitudeText;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }
}