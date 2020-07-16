package com.demo.opencvdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// This is Michael's project
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (!OpenCVLoader.initDebug()){
            Log.d("zencher","No OpenCV");
        }else {
            Log.d("zencher","OpenCV OK");
            Point point = new Point();
            point.x = 10;

            Point point2 = point ;
            point2.x = 6;

            Log.d("zencher",""+point.x);

        }
        Button btn = findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Hello";
                try {
                    Mat img = Utils.loadResource(MainActivity.this,R.drawable.iphone, CvType.CV_8UC4);
                    message = "b:"+img.get(50,50)[0]+"g:"+img.get(50,50)[1]+"r:"+img.get(50,50)[2];

                    img = img.submat(0,300,0,300);

                    updateImageView(img);
                    Log.d("zencher","channel "+img.channels());

                } catch (Exception e) {
                    Log.d("zencher",""+e);
                }

                Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();

            }
        });

        Button btn2 = findViewById(R.id.button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Point firstPoint = new Point(100, 200);
                    Point secondPoint = new Point(100, 400);
                    Point middlePoint = new Point(firstPoint.x,
                            firstPoint.y + 0.5 * (secondPoint.y -  firstPoint.y));

                    Scalar lineColor = new Scalar(255, 0, 0, 255);
                    int lineWidth = 3;

                    Scalar textColor = new Scalar(255, 255, 255, 255);

                    Mat img = Utils.loadResource(MainActivity.this,R.drawable.iphone, CvType.CV_8UC4);

                    Imgproc.line(img, firstPoint, secondPoint, lineColor, lineWidth);
                    Imgproc.putText(img, "OpenCV" , middlePoint,Core.NORM_HAMMING , 5.0 , textColor,5);

                    Rect rect = new Rect(200,200,120,120);
                    Imgproc.rectangle(img,rect, lineColor);

                    Imgproc.circle(img, new Point(50,50),50,textColor, -1);

//                    Imgproc.arrowedLine();
//                    Imgproc.ellipse();
//                    Imgproc.polylines();
                    updateImageView(img);

                    ArrayList<Integer> numbers = new ArrayList<Integer>();

                    numbers.add(5);
                    numbers.add(8);
                    Log.d("zencher",""+numbers);

                    ArrayList<Point> points = new ArrayList<>();
                    points.add(new Point(5,6));
                    points.add(new Point(10,20));


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        ArrayList<Shape> shapes = new ArrayList<>();

        shapes.add(new Rectangle());
        shapes.add(new Circle());

        Rectangle r = (Rectangle) shapes.get(0);

        Log.d("zencher",""+shapes);



//        array_demo();
    }

    private void updateImageView(Mat img) {
        Bitmap bitmap = Bitmap.createBitmap(img.width(), img.height(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(img, bitmap);
        ImageView imgView = findViewById(R.id.imageView);
        imgView.setImageBitmap(bitmap);
    }


    void array_demo(){
        int[] int_array = {5,6,7,8,9,10}; // one dimension array
        Log.d("zencher","array value:"+int_array[2]);


        int[] int_array2 = {0,1,2,3,4,5};
        int[][] array2d = {int_array,int_array2}; // two dimensions array
        Log.d("zencher",""+array2d[1][4]);


        int[] r = getArray3d(2,1);
        Log.d("zencher", ""+r[1]);
    }

    int[] getArray3d(int x, int y ){
        int[][][] array3d = new int[5][5][5];
        // initialization
        for (int i = 0 ; i < 5 ; i++) {
            for (int j = 0 ; j < 5  ; j++) {
                for (int k = 0 ; k < 5 ; k++){
                    array3d[i][j][k] = i+j+k;
                }
            }
        }

        return array3d[x][y];
    }
}
