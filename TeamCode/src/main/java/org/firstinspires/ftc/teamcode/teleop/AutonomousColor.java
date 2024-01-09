package org.firstinspires.ftc.teamcode.teleop;

import android.app.Activity;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

public class AutonomousColor {

    /*
     * This is an example LinearOpMode that shows how to use
     * the REV Robotics Color-Distance Sensor.
     *
     * It assumes the sensor is configured with the name "sensor_color_distance".
     *
     * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
     * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
     */
    @TeleOp
// Comment this out to add to the opmode list
    public class SensorColor3 extends LinearOpMode {

        private DcMotorEx backLeft;
        private DcMotorEx backRight;
        private DcMotorEx frontLeft;
        private DcMotorEx frontRight;

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

        int redValue = 0;




        @Override
        public void runOpMode() {

            // get a reference to the color sensor.
            backLeft = hardwareMap.get(DcMotorEx.class, "Motor0");
            backRight = hardwareMap.get(DcMotorEx.class, "Motor3");
            frontLeft = hardwareMap.get(DcMotorEx.class, "Motor1");
            frontRight = hardwareMap.get(DcMotorEx.class, "Motor2");

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

            backLeft.setPower(-0.2);
            backRight.setPower(0.2);
            frontLeft.setPower(-0.2);
            frontRight.setPower(0.2);

            // loop and read the RGB and distance data.
            // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
            while (opModeIsActive()) {
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

                redValue = sensorColor.red();
                telemetry.addData("Red  ", redValue);

                telemetry.addData("Green", sensorColor.green());
                telemetry.addData("Blue ", sensorColor.blue());
                telemetry.addData("Hue", hsvValues[0]);
                // red value
                telemetry.update();

                if (redValue >= 5) {
                    telemetry.addData("Update ", "Red Over 5");
                    backLeft.setPower(0.2);
                    backRight.setPower(-0.2);
                    frontLeft.setPower(0.2);
                    frontRight.setPower(-0.2);
                }
                // blue
                else if (redValue == 2) {
                    backLeft.setPower(-0.2);
                    backRight.setPower(0.2);
                    frontLeft.setPower(-0.2);
                    frontRight.setPower(0.2);
                }
                // gray
/***            else if (redValue ==3) {
 backLeft.setPower(0.23);
 backRight.setPower(-0.22);
 frontLeft.setPower(0.2);
 frontRight.setPower(-0.2);
 }***/

                // change the background color to match the color detected by the RGB sensor.
                // pass a reference to the hue, saturation, and value array as an argument
                // to the HSVToColor method.
                relativeLayout.post(new Runnable() {
                    public void run() {
                        relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                    }
                });

//me when I see color 0-0
            }

            // Set the panel back to the default color
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.WHITE);
                }
            });

        }

    }
    // }
}
