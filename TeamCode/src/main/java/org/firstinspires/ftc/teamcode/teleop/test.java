package org.firstinspires.ftc.teamcode.teleop;

//import org.firstinspires.ftc.robotcontroller.*;
//import org.firstinspires.ftc.teamcode.*;

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

@TeleOp(name = "test", group = "Default")

    public class test extends LinearOpMode {

    @Override
        public void runOpMode() {
        DcMotor motor = hardwareMap.dcMotor.get("Motor3");

            waitForStart();
//HELLO MELA!!!!
            if (opModeIsActive()) {
                motor.setPower(-0.36);
                sleep(1000);

                motor.setPower(0);
                sleep(1000);
            }
        }
    }



