package udacity.winni.bakingapp.presentation.recipegallery;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.RecipeVM;

/**
 * Created by winniseptiani on 6/16/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickedListener onItemClickedListener;

    private List<RecipeVM> recipeVMs;

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvRecipe;

        @BindView(R.id.iv_recipe_image)
        ImageView ivRecipeImage;

        private View itemView;

        private Context context;

        private OnItemClickedListener onItemClickedListener;

        public RecipeViewHolder(Context context, View itemView,
            OnItemClickedListener onItemClickedListener) {
            super(itemView);
            this.context = context;
            this.itemView = itemView;
            this.onItemClickedListener = onItemClickedListener;
            ButterKnife.bind(this, itemView);
        }

        public void bindData(final RecipeVM recipeVM) {
            tvRecipe.setText(recipeVM.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickedListener != null) {
                        onItemClickedListener.onItemClicked(recipeVM);
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemClickedListener != null) {
                        onItemClickedListener.onItemLongClicked(recipeVM);
                    }
                    return true;
                }
            });

            String imagePath = recipeVM.getImage();
            if (imagePath.isEmpty()) {
                imagePath = null;
            }
            Picasso.with(context)
                .load(imagePath)
                .placeholder(R.drawable.no_image)
                .into(ivRecipeImage);

        }
    }

    public RecipeAdapter(List<RecipeVM> recipes, OnItemClickedListener onItemClickedListener) {
        this.recipeVMs = recipes;
        this.onItemClickedListener = onItemClickedListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(parent.getContext(), view, onItemClickedListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecipeViewHolder) holder).bindData(recipeVMs.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return recipeVMs.size();
    }

    public void addData(List<RecipeVM> recipes) {
        recipeVMs.addAll(recipes);
        notifyDataSetChanged();
    }

    public void clearData() {
        recipeVMs.clear();
        notifyDataSetChanged();
    }

    public void resetData(List<RecipeVM> recipes) {
        recipeVMs.clear();
        recipeVMs.addAll(recipes);
        notifyDataSetChanged();
    }

    public List<RecipeVM> getData() {
        return recipeVMs;
    }

    public interface OnItemClickedListener {

        void onItemClicked(RecipeVM recipe);

        void onItemLongClicked(RecipeVM recipeVM);
    }
}
