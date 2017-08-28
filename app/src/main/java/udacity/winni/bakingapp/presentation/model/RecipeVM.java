package udacity.winni.bakingapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import udacity.winni.bakingapp.data.model.Ingredient;
import udacity.winni.bakingapp.data.model.Step;

/**
 * Created by winniseptiani on 8/2/17.
 */

public class RecipeVM implements Parcelable{

    private int id;

    private String name;

    private List<IngredientVM> ingredients;

    private List<StepVM> steps;

    private int servings;

    private String image;

    public RecipeVM() {

    }

    protected RecipeVM(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = in.createTypedArrayList(IngredientVM.CREATOR);
        steps = in.createTypedArrayList(StepVM.CREATOR);
        servings = in.readInt();
        image = in.readString();
    }

    public static final Creator<RecipeVM> CREATOR = new Creator<RecipeVM>() {
        @Override
        public RecipeVM createFromParcel(Parcel in) {
            return new RecipeVM(in);
        }

        @Override
        public RecipeVM[] newArray(int size) {
            return new RecipeVM[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientVM> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientVM> ingredients) {
        this.ingredients = ingredients;
    }

    public List<StepVM> getSteps() {
        return steps;
    }

    public void setSteps(List<StepVM> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
        dest.writeInt(servings);
        dest.writeString(image);
    }
}
