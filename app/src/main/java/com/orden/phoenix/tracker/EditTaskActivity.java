package com.orden.phoenix.tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created on 5/25/2014.
 */
@ContentView(R.layout.edit_task)
public class EditTaskActivity extends RoboActivity {

    public static final String TASK_EXTRA = "taskItem";
    private TaskViewModel taskToEdit;

    @InjectView(R.id.edit_task_save_button)
    private Button saveButton;

    @InjectView(R.id.edit_task_name_text)
    private EditText nameText;

    @InjectView(R.id.edit_task_estimated_options)
    private EditText estimatedText;

    @InjectView(R.id.edit_task_description_text)
    private EditText descriptionText;

    @InjectView(R.id.edit_task_cancel_button)
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        taskToEdit = (TaskViewModel) getIntent().getSerializableExtra(TASK_EXTRA);

        nameText.setText(taskToEdit.getName());
        estimatedText.setText(Long.toString(taskToEdit.getEstimate()));
        descriptionText.setText(taskToEdit.getDescription());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskToEdit.setName(nameText.getText().toString());
                taskToEdit.setDescription(descriptionText.getText().toString());
                taskToEdit.setEstimate(getEstimate());

                goBackToList(false);
            }

            private long getEstimate() {
                long estimate = 0L;
                try {
                    estimate = Long.parseLong(estimatedText.getText().toString());
                } catch (NumberFormatException ignored) {
                }
                return estimate;
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToList(true);
            }
        });
    }

    private void goBackToList(boolean cancel) {
        Intent goToList = new Intent();
        int result = RESULT_CANCELED;
        if (!cancel) {
            goToList.putExtra(TASK_EXTRA, taskToEdit);
            result = RESULT_OK;
        }
        setResult(result, goToList);
        finish();
    }
}