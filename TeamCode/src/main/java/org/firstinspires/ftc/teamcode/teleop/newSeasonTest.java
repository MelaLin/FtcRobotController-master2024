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
import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;
import java.util.Locale;

@Autonomous(name = "newSeasonTest", group = "Default")
public class newSeasonTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotorEx backLeft = hardwareMap.get(DcMotorEx.class, "Motor0");
        DcMotorEx backRight = hardwareMap.get(DcMotorEx.class, "Motor2");
        DcMotorEx frontLeft = hardwareMap.get(DcMotorEx.class, "Motor1");
        DcMotorEx frontRight = hardwareMap.get(DcMotorEx.class, "Motor3");

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        waitForStart();
        if (opModeIsActive()){

            backLeft.setPower(1.0);
            backRight.setPower(1.0);
            frontLeft.setPower(1.0);
            frontRight.setPower(1.0);
            sleep(10000);

        }
    }
}