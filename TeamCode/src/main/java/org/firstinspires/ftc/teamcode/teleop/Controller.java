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

@TeleOp(name = "Controller", group = "Default")

    public class Controller extends LinearOpMode {

        @Override
        public void runOpMode() {
            DcMotor backLeft = hardwareMap.dcMotor.get("Motor0");
            DcMotor frontLeft = hardwareMap.dcMotor.get("Motor1");
            DcMotor backRight = hardwareMap.dcMotor.get("Motor2");
            DcMotor frontRight = hardwareMap.dcMotor.get("Motor3");

            waitForStart();

            if (opModeIsActive()) {

                backLeft.setPower(0.7);
                frontLeft.setPower(0.7);
                backRight.setPower(0.7);
                frontRight.setPower(0.7);
                sleep (1000);

                backLeft.setPower (0);
                frontLeft.setPower (0);
                backRight.setPower (0);
                frontRight.setPower (0);
                sleep(500);

            }
        }
}
