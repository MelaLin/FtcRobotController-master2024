package org.firstinspires.ftc.teamcode.teleop;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "LauncherTest1", group = "Default")
public class LauncherTest1 extends LinearOpMode {

    private CRServo leftWheel;
    private CRServo rightWheel;
    private CRServo rotation;

    double contPower;
    void disable(){

    }

    @Override
    public void runOpMode() {
        leftWheel = hardwareMap.crservo.get("leftWheel");
        rightWheel = hardwareMap.crservo.get("rightWheel");
        rotation = hardwareMap.get(CRServo.class, "rotation");


        telemetry.addData("Mode", "waiting");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()){

            if (gamepad2.a){
                leftWheel.setPower(1);
            }
            if (gamepad2.b){
                leftWheel.setPower(-1);
            }
            telemetry.update();


        }
        leftWheel.setPower(0);
    }
}
