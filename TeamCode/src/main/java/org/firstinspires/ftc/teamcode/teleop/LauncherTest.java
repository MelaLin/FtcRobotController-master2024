package org.firstinspires.ftc.teamcode.teleop;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "LauncherTest", group = "Default")
public class LauncherTest extends LinearOpMode {

    double contPower;
    @Override
    public void runOpMode() {
        CRServo leftWheel = hardwareMap.crservo.get("leftWheel");
        CRServo rightWheel = hardwareMap.crservo.get("rightWheel");
        CRServo rotation = hardwareMap.get(CRServo.class, "rotation");

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()){

           contPower = 0.5;

        }
    }
}
