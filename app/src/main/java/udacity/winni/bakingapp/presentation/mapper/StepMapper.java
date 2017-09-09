package udacity.winni.bakingapp.presentation.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import udacity.winni.bakingapp.data.model.Ingredient;
import udacity.winni.bakingapp.data.model.Step;
import udacity.winni.bakingapp.presentation.model.IngredientVM;
import udacity.winni.bakingapp.presentation.model.StepVM;

/**
 * Created by winniseptiani on 7/8/17.
 */

public class StepMapper {

    public static StepVM transform(Step step) {
        StepVM stepVM = null;

        if (step != null) {
            stepVM = new StepVM();
            stepVM.setId(step.getId());
            stepVM.setDescription(step.getDescription());
            stepVM.setShortDescription(step.getShortDescription());
            stepVM.setVideoURL(step.getVideoURL());
            stepVM.setThumbnailURL(step.getThumbnailUrl());
        }

        return stepVM;
    }

    public static Step transform(StepVM stepVM) {
        Step step = null;

        if (stepVM != null) {
            step = new Step();
            step.setId(stepVM.getId());
            step.setDescription(stepVM.getDescription());
            step.setShortDescription(stepVM.getShortDescription());
            step.setVideoURL(stepVM.getVideoURL());
            step.setThumbnailUrl(stepVM.getThumbnailURL());
        }

        return step;
    }

    public static List<StepVM> transform(List<Step> steps) {
        List<StepVM> stepVMs = null;

        if (steps != null) {
            stepVMs = new ArrayList<>();

            for (Step step : steps) {
                stepVMs.add(transform(step));
            }
        } else {
            stepVMs = Collections.emptyList();
        }

        return stepVMs;
    }

    public static List<Step> transformToSteps(List<StepVM> stepVMs) {
        List<Step> steps = null;

        if (stepVMs != null) {
            steps = new ArrayList<>();

            for (StepVM stepVM : stepVMs) {
                steps.add(transform(stepVM));
            }
        } else {
            steps = Collections.emptyList();
        }

        return steps;
    }
}
