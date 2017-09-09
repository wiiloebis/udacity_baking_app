package udacity.winni.bakingapp.presentation.recipegallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.StepVM;

/**
 * Created by winniseptiani on 6/16/17.
 */

public class RecipeMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int RECIPE_INGREDIENT = 0;

    private static final int RECIPE_STEP = 1;

    private static final int RECIPE_MENU_POSITION = 0;

    private OnItemClickedListener onItemClickedListener;

    private List<StepVM> stepVMs;

    private int selectedPosition;

    public class RecipeStepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_number)
        TextView tvNumber;

        private View itemView;

        private OnItemClickedListener onItemClickedListener;

        public RecipeStepViewHolder(View itemView,
            OnItemClickedListener onItemClickedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvNumber.setVisibility(View.VISIBLE);
            this.itemView = itemView;
            this.onItemClickedListener = onItemClickedListener;
        }

        public void bindData(final StepVM stepVM, int selectedPosition, int position) {
            tvTitle.setText(stepVM.getShortDescription());
            tvNumber.setText("" + getAdapterPosition());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickedListener != null) {
                        onItemClickedListener.onRecipeStepClicked(stepVM, getAdapterPosition());
                    }
                }
            });

            itemView.setSelected(position == selectedPosition);
        }
    }

    public class RecipeIngredientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        public RecipeIngredientViewHolder(Context context, View itemView,
            OnItemClickedListener onItemClickedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvTitle.setText(context.getString(R.string.recipe_ingredients));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickedListener != null) {
                        onItemClickedListener.onRecipeIngredient();
                    }
                }
            });
        }
    }

    public RecipeMenuAdapter(List<StepVM> steps, OnItemClickedListener onItemClickedListener) {
        this.stepVMs = steps;
        this.onItemClickedListener = onItemClickedListener;
    }

    public void setOnItemClickedListener(OnItemClickedListener listener) {
        this.onItemClickedListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case RECIPE_INGREDIENT:
                view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menu_ingredients, parent, false);
                return new RecipeIngredientViewHolder(parent.getContext(), view,
                    onItemClickedListener);
            case RECIPE_STEP:
                view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menu_step, parent, false);
                return new RecipeStepViewHolder(view, onItemClickedListener);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType
            + " + make sure your using types correctly");
    }

    @Override
    public int getItemViewType(int position) {
        if (position == RECIPE_MENU_POSITION) {
            return RECIPE_INGREDIENT;
        } else {
            return RECIPE_STEP;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case RECIPE_STEP:
                ((RecipeStepViewHolder) holder)
                    .bindData(stepVMs.get(holder.getAdapterPosition()), selectedPosition, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return stepVMs.size();
    }

    public void addData(List<StepVM> steps) {
        stepVMs.addAll(steps);
        notifyDataSetChanged();
    }

    public void clearData() {
        stepVMs.clear();
        notifyDataSetChanged();
    }

    public void resetDataWithDummyHeader(List<StepVM> steps) {
        stepVMs.clear();
        stepVMs.add(0, new StepVM());
        stepVMs.addAll(steps);
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    public List<StepVM> getData() {
        return stepVMs;
    }

    public interface OnItemClickedListener {

        void onRecipeStepClicked(StepVM step, int position);

        void onRecipeIngredient();

    }
}
