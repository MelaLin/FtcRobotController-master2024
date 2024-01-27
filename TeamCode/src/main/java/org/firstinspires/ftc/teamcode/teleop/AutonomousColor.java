package org.firstinspires.ftc.teamcode.teleop;

import android.app.Activity;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;
    @TeleOp(name = "AutonomousColor", group = "Default")
// Comment this out to add to the opmode list
    public class AutonomousColor extends LinearOpMode {


        /**
         * Note that the REV Robotics Color-Distance incorporates two sensors into one device.
         * It has a light/distance (range) sensor.  It also has an RGB color sensor.
         * The light/distance sensor saturates at around 2" (5cm).  This means that targets that are 2"
         * or closer will display the same value for distance/light detected.
         *
         * Although you configure a single REV Robotics Color-Distance sensor in your configuration file,
         * you can treat the sensor as two separate sensors that share the same name in your op mode.
         *
         * In this example, we represent the detected color by a hue, saturation, and value color
         * model (see https://en.wikipedia.org/wiki/HSL_and_HSV).  We change the background
         * color of the screen to match the detected color.
         *
         * In this example, we  also use the distance sensor to display the distance
         * to the target object.  Note that the distance sensor saturates at around 2" (5 cm).
         *
         */
        ColorSensor sensorColor;
        DistanceSensor sensorDistance;


        @Override
        public void runOpMode() {

            // get a reference to the color sensor.
            DcMotorEx backLeft = hardwareMap.get(DcMotorEx.class, "Motor0");
            DcMotorEx backRight = hardwareMap.get(DcMotorEx.class, "Motor2");
            DcMotorEx frontLeft = hardwareMap.get(DcMotorEx.class, "Motor1");
            DcMotorEx frontRight = hardwareMap.get(DcMotorEx.class, "Motor3");
            DcMotorEx armMotor = hardwareMap.get(DcMotorEx.class, "Motor4");

            sensorColor = hardwareMap.get(ColorSensor.class, "color");

            // get a reference to the distance sensor that shares the same name.
            sensorDistance = hardwareMap.get(DistanceSensor.class, "color");

            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            // hsvValues is an array that will hold the hue, saturation, and value information.
            float hsvValues[] = {0F, 0F, 0F};

            // values is a reference to the hsvValues array.
            final float values[] = hsvValues;

            // sometimes it helps to multiply the raw RGB values with a scale factor
            // to amplify/attentuate the measured values.
            final double SCALE_FACTOR = 255;

            // get a reference to the RelativeLayout so we can change the background
            // color of the Robot Controller app to match the hue detected by the RGB sensor.
            int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
            final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

            // wait for the start button to be pressed.
            waitForStart();

            // loop and read the RGB and distance data.
            // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
            if (opModeIsActive()) {
                // convert the RGB values to HSV values.
                // multiply by the SCALE_FACTOR.
                // then cast it back to int (SCALE_FACTOR is a double)
                Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                        (int) (sensorColor.green() * SCALE_FACTOR),
                        (int) (sensorColor.blue() * SCALE_FACTOR),
                        hsvValues);
                // send the info back to driver station using telemetry function.
                telemetry.addData("Distance (cm)",
                        String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
                telemetry.addData("Alpha", sensorColor.alpha());
                telemetry.addData("Red  ", sensorColor.red());
                telemetry.addData("Green", sensorColor.green());
                telemetry.addData("Blue ", sensorColor.blue());
                telemetry.addData("Hue", hsvValues[0]);
                telemetry.update();
                //set hueValue to the hue sensor value on sensor
                double hueValue = hsvValues[0];
                double distance = sensorDistance.getDistance(DistanceUnit.CM);

                backLeft.setPower(0.5 * 0.96);
                backRight.setPower(0.5 * 1.07);
                frontLeft.setPower(0.5 * 0.96);
                frontRight.setPower(0.5 * 1.07);
                sleep(1500);

                backLeft.setPower(0.5 * 0.96);
                backRight.setPower(-0.5 * 1.07);
                frontLeft.setPower(0.5 * 0.96);
                frontRight.setPower(-0.5 * 1.07);
                sleep(760);

                backLeft.setPower(0.5 * 0.96);
                backRight.setPower(0.5 * 1.07);
                frontLeft.setPower(0.5 * 0.96);
                frontRight.setPower(0.5 * 1.07);
                sleep(5000);


                /*
                //move forward
                backLeft.setPower(0.5 * 0.96);
                backRight.setPower(0.5 * 1.07);
                frontLeft.setPower(0.5 * 0.96);
                frontRight.setPower(0.5 * 1.07);
                sleep(1000);

                backLeft.setPower(0);
                backRight.setPower(0);
                frontLeft.setPower(0);
                frontRight.setPower(0);
                sleep(1000);

                backLeft.setPower(-0.5 * 0.96);
                backRight.setPower(-0.5 * 1.07);
                frontLeft.setPower(-0.5 * 0.96);
                frontRight.setPower(-0.5 * 1.07);
                sleep(700);

                //rotate on the spot 90 degrees
                backLeft.setPower(-0.3 * 0.92);
                backRight.setPower(0.3 * 1.08);
                frontLeft.setPower(-0.3 * 0.92);
                frontRight.setPower(0.3 * 1.08);
                sleep(760);

                backLeft.setPower(0);
                backRight.setPower(0);
                frontLeft.setPower(0);
                frontRight.setPower(0);
                sleep(1000);

                backLeft.setPower(-0.2 * 0.96);
                backRight.setPower(-0.2 * 1.07);
                frontLeft.setPower(-0.2 * 0.96);
                frontRight.setPower(-0.2 * 1.07);
                sleep(300);

                backLeft.setPower(0.5 * 0.92);
                backRight.setPower(0.5 * 0.92);
                frontLeft.setPower(-0.5 * 1.08);
                frontRight.setPower(-0.5 * 0.92);
                sleep(1000);

                backLeft.setPower(0);
                backRight.setPower(0);
                frontLeft.setPower(0);
                frontRight.setPower(0);
                sleep(1000);
/*
                if (distance <= 7) {
                    //program this to drop the purple tile on the tape
                    //program this to move to the leftmost side of the backdrop where you put the tiles



                    backLeft.setPower(0);
                    backRight.setPower(0);
                    frontLeft.setPower(0);
                    frontRight.setPower(0);
                    sleep(1000);

                    backLeft.setPower(-0.1 * 1.08);
                    backRight.setPower(0.1 * 0.92);
                    frontLeft.setPower(-0.1 * 1.08);
                    frontRight.setPower(0.1 * 0.92);
                    sleep(500);

                }

                else{
                    backLeft.setPower(0.1 * 1.08);
                    backRight.setPower(-0.1 * 0.92);
                    frontLeft.setPower(0.1 * 1.08);
                    frontRight.setPower(-0.1 * 0.92);
                    sleep(500);

                    backLeft.setPower(0);
                    backRight.setPower(0);
                    frontLeft.setPower(0);
                    frontRight.setPower(0);
                    sleep(500);

                    if (distance <= 7) {

                        //program this to drop the purple tile on the tape
                        //program this to move to the middle of the backdrop where you put the tiles
                    }

                    else {
                        backLeft.setPower(0.1 * 1.08);
                        backRight.setPower(-0.1 * 0.92);
                        frontLeft.setPower(0.1 * 1.08);
                        frontRight.setPower(-0.1 * 0.92);
                        sleep(500);

                        backLeft.setPower(0);
                        backRight.setPower(0);
                        frontLeft.setPower(0);
                        frontRight.setPower(0);
                        sleep(500);

                        //program to drop the purple tile on the tape

                        //program this to move to the rightmost part of the backdrop where you put the tiles
                    }

                }

*/
                // red value
                //set Power to 0.2 times the motor ratio value (taken from Controller class)
                /* if (((60 <= hueValue) && (hueValue <= 70))) {
                    backLeft.setPower(0.2 * 1.08);
                    backRight.setPower(0.2 * 0.92);
                    frontLeft.setPower(0.2 * 1.08);
                    frontRight.setPower(0.2 * 0.92);
                }
                // blue
                else if ((71 <= hueValue) && (hueValue <= 80)) {
                    backLeft.setPower(-0.2 * 1.080);
                    backRight.setPower(-0.2 * 0.92);
                    frontLeft.setPower(-0.2 * 1.08);
                    frontRight.setPower(-0.2 * 0.92);


                } else {
                    backLeft.setPower(0);
                    backRight.setPower(0);
                    frontLeft.setPower(0);
                    frontRight.setPower(0);
                } */
                // gray

                // while ((179 >= hueValue) && (hueValue >= 31)||((315 >= hueValue) && (hueValue >= 281)){
                //backLeft.setPower(0);
                //backRight.setPower(0);
                //frontLeft.setPower(0);
                //frontRight.setPower(0);
                //}


                // change the background color to match the color detected by the RGB sensor.
                // pass a reference to the hue, saturation, and value array as an argument
                // to the HSVToColor method.

                /*
                relativeLayout.post(new Runnable() {
                    public void run() {
                        relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                    }
                });

            }

            // Set the panel back to the default color
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.WHITE);
                }
            });
*/


            }
        }

    }