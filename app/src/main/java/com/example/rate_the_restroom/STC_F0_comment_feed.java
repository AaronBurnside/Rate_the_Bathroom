package com.example.rate_the_restroom;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public abstract class STC_F0_comment_feed extends AppCompatActivity {
    private A_Comment The_Comment = new A_Comment();
    URL url;
    {
        try {
            url = new URL("http://yourserver"); //TODO Replace with actual server URL
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stc_f0_comment_feed);

        int comment_number = 0;
        TextView Comment1 = (TextView) findViewById(R.id.Comment1);
        TextView Comment2 = (TextView) findViewById(R.id.Comment2);
        TextView Comment3 = (TextView) findViewById(R.id.Comment3);
        TextView Comment4 = (TextView) findViewById(R.id.Comment4);
        TextView Comment5 = (TextView) findViewById(R.id.Comment5);
        //TODO put each comment through Change_comment Function
        /*Change_comment(Comment1, comment_number);
        comment_number += 1;
        Change_comment(Comment2, comment_number);
        comment_number += 1;
        Change_comment(Comment3, comment_number);
        comment_number += 1;
        Change_comment(Comment4, comment_number);
        comment_number += 1;
        Change_comment(Comment5, comment_number);*/

        final FloatingActionButton button = findViewById(R.id.AddComment);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Post_comment(v);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }});


    }
    public void Change_comment(TextView Comment1, int comment_number){
        String Fetched_comment = "Place Holder";
        //TODO This is where we will request the comments from the server, send comment_number to tell the server which comment is needed from list
        //JsonObjectRequest request = new JsonObjectRequest("Our Server goes Here");

        Comment1.setText(Fetched_comment);
        return;
    }


    public void Post_comment(View view) throws FileNotFoundException {
        final String[] New_comment = {""};
        String FILENAME = "Comment_File";

        PrintWriter writer = new PrintWriter(FILENAME);
        writer.print(""); // Empty the File before writing a new Comment in it
        writer.close();

        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(STC_F0_comment_feed.this);
        builder.setTitle("Post your Comment");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected;
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Date C_date = Calendar.getInstance().getTime();
                DateFormat Date_Format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String Current_Time= Date_Format.format(C_date);
                New_comment[0] = input.getText().toString();
                The_Comment.setContent(New_comment[0]);
                The_Comment.setBuilding("STC");
                The_Comment.setFloor("F0");
                The_Comment.setRoom("Male");
                The_Comment.setTimestamp(Current_Time);
                The_Comment.CompileMessage();
                try {
                    fos.write(The_Comment.getComment().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //TODO We need to send the file to the server here


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

    public void SendCommentFile(){
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                "Comment_File");

        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;
        String pathToOurFile = "src/main/java/com/example/rate_the_restroom/Comment_File";
        String urlServer = "http://Our_server_Goes_Here";
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary =  "*****";

        int bytesRead;
        int bytesAvailable;
        int bufferSize;
        byte[] buffer;
        int maxBufferSize = 1*1024*1024;

        try
        {
            FileInputStream fileInputStream = new FileInputStream(file);

            URL url = new URL(urlServer);
            connection = (HttpURLConnection) url.openConnection();

            // Allow Inputs &amp; Outputs.
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            // Set HTTP method to POST.
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

            outputStream = new DataOutputStream( connection.getOutputStream() );
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pathToOurFile +"\"" + lineEnd);
            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // Read file
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0)
            {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            int serverResponseCode = connection.getResponseCode();
            String serverResponseMessage = connection.getResponseMessage();

            fileInputStream.close();
            outputStream.flush();
            outputStream.close();
        }
        catch (Exception ex)
        {
            //Exception handling
        }


        return;
    }

    private void writeStream(OutputStream out) {
    }

}
