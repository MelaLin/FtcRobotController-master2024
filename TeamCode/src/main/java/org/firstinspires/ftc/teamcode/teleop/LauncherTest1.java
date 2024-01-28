package org.firstinspires.ftc.teamcode.teleop;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "LauncherTest1", group = "Default")
public class LauncherTest1 extends LinearOpMode {

    private CRServo launch;
    private CRServo launch2;
    private CRServo propeller;


    @Override
    public void runOpMode() {
        launch = hardwareMap.crservo.get("leftWheel");
        launch2 = hardwareMap.crservo.get("rightWheel");
        propeller = hardwareMap.crservo.get("propeller");

     //   launch.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();


        waitForStart();

        launch.setPower(0);
        launch2.setPower(0);

        while (opModeIsActive()){




           /* if (gamepad2.a) {

                launch.setPower(1);

                telemetry.addData("Update: ", "set power");
                telemetry.update();

                propeller.setPower(0.5);
                sleep(500);

            }
            else if(gamepad2.b){
                launch.setPower(0);
                propeller.setPower(0.5);
            }*/


        }

    }
}
