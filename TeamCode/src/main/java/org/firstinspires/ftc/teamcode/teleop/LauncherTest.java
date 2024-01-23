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
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;
@TeleOp(name = "LauncherTest", group = "Default")
public class LauncherTest extends LinearOpMode {
    @Override
    public void runOpMode(){
        CRServo leftWheel = hardwareMap.get(CRServo.class, "leftWheel");
        CRServo rightWheel = hardwareMap.get(CRServo.class, "rightWheel");
        CRServo rotation = hardwareMap.get(CRServo.class, "rotation");

        waitForStart();
        while (opModeIsActive()){
           rightWheel.setPower(0.5);
           leftWheel.setPower(0.5);

        }
    }
}
