package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Controllerv2", group = "Default")

public class Controllerv2 extends LinearOpMode{

    @Override
    public void runOpMode() {
//        DcMotor backLeft = hardwareMap.dcMotor.get("Motor0");
//        DcMotor frontLeft = hardwareMap.dcMotor.get("Motor1");
//        DcMotor backRight = hardwareMap.dcMotor.get("Motor2");
//        DcMotor frontRight = hardwareMap.dcMotor.get("Motor3");

        DcMotorEx backLeft = hardwareMap.get(DcMotorEx.class, "Motor0");
        DcMotorEx frontLeft = hardwareMap.get(DcMotorEx.class, "Motor1");
        DcMotorEx backRight = hardwareMap.get(DcMotorEx.class, "Motor2");
        DcMotorEx frontRight = hardwareMap.get(DcMotorEx.class, "Motor3");

        waitForStart();

        if(opModeIsActive()){

            frontRight.setPower(1);
            frontLeft.setPower(-1);
            backRight.setPower(1);
            backLeft.setPower(-1);
            sleep(500);

            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);
            sleep(1000);
//this is a test
            //this is also a test



        }
    }

}
