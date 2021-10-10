package com.example.rate_the_restroom;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class STC_F1_comment_feed extends AppCompatActivity {
    private String New_comment = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stc_f1_comment_feed);

        int comment_number = 0;
        TextView Comment1 = (TextView) findViewById(R.id.Comment1);
        TextView Comment2 = (TextView) findViewById(R.id.Comment2);
        TextView Comment3 = (TextView) findViewById(R.id.Comment3);
        TextView Comment4 = (TextView) findViewById(R.id.Comment4);
        TextView Comment5 = (TextView) findViewById(R.id.Comment5);

        /*Change_comment(Comment1, comment_number);
        comment_number += 1;
        Change_comment(Comment2, comment_number);
        comment_number += 1;
        Change_comment(Comment3, comment_number);
        comment_number += 1;
        Change_comment(Comment4, comment_number);
        comment_number += 1;
        Change_comment(Comment5, comment_number);*/

    }
    public void Change_comment(TextView A_comment, int comment_number){
        String Fetched_comment = "";
        //TODO This is where we will request the comments from the server, send comment_number to tell the server which comment is needed from list

        A_comment.setText(Fetched_comment);
        return;
    }

    public void Post_comment(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected;
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                New_comment = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

        return;
    }

}