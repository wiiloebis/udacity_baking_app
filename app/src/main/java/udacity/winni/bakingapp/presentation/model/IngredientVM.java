package udacity.winni.bakingapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by winniseptiani on 8/2/17.
 */

public class IngredientVM implements Parcelable {

    private double quantity;

    private String measure;

    private String ingredient;

    public IngredientVM() {

    }

    protected IngredientVM(Parcel in) {
        quantity = in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<IngredientVM> CREATOR = new Creator<IngredientVM>() {
        @Override
        public IngredientVM createFromParcel(Parcel in) {
            return new IngredientVM(in);
        }

        @Override
        public IngredientVM[] newArray(int size) {
            return new IngredientVM[size];
        }
    };

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }
}
