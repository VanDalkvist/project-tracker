package com.orden.phoenix.tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;

/**
 * Created on 5/25/2014.
 */
public class EditTaskActivity extends Activity {
    public static final String TASK_EXTRA = "taskItem";
    private TaskViewModel taskToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        init();
    }

    private void init() {
        taskToEdit = (TaskViewModel)getIntent().getSerializableExtra(TASK_EXTRA);
        final EditText nameText = (EditText) findViewById(R.id.edit_task_name_text);
        nameText.setText(taskToEdit.getName());

        final EditText estimatedText = (EditText) findViewById(R.id.edit_task_estimated_options);
        estimatedText.setText(Long.toString(taskToEdit.getEstimate()));

        final EditText descriptionText = (EditText) findViewById(R.id.edit_task_description_text);
        descriptionText.setText(taskToEdit.getDescription());

        Button saveButton = (Button) findViewById(R.id.edit_task_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskToEdit.setName(nameText.getText().toString());
                try {
                    taskToEdit.setEstimate(Long.parseLong(estimatedText.getText().toString()));
                } catch (NumberFormatException e) {
                    taskToEdit.setEstimate(0L);
                }
                taskToEdit.setDescription(descriptionText.getText().toString());
                goBackToList(false);
            }
        });
        Button cancelButton = (Button) findViewById(R.id.edit_task_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToList(true);
            }
        });
    }

    private void goBackToList(boolean cancel) {
        Intent goToList = new Intent();
        if(cancel) {
            setResult(RESULT_CANCELED, goToList);
        } else {
            goToList.putExtra(TASK_EXTRA, taskToEdit);
            setResult(RESULT_OK, goToList);
        }
        finish();
    }
}
