package udacity.winni.bakingapp.data.model;

/**
 * Created by winniseptiani on 8/2/17.
 */

public class Ingredient {

    private double quantity;

    private String measure;

    private String ingredient;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
}
