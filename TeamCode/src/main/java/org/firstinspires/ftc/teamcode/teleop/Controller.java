package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Controller", group = "Default")

    public class Controller extends LinearOpMode {
    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;
    private DcMotorEx armMotor;

    private CRServo leftWheel;
    private CRServo rightWheel;

    private Double speedLeft = 0.0;
    private Double speedRight = 0.0;
    private Double frontLeftVelocity = 0.0;
    private Double frontRightVelocity = 0.0;
    private Double backLeftVelocity = 0.0;
    private Double backRightVelocity = 0.0;
    private Double armMotorVelocity = 0.0;
    /*
    public Controller(DcMotorEx frontLeft) {
        this.frontLeft = frontLeft;
    } */

    @Override
        public void runOpMode() {
            backLeft = hardwareMap.get(DcMotorEx.class, "Motor0");
            frontLeft = hardwareMap.get(DcMotorEx.class, "Motor1");
            backRight = hardwareMap.get(DcMotorEx.class, "Motor2");
            frontRight = hardwareMap.get(DcMotorEx.class, "Motor3");
            armMotor = hardwareMap.get(DcMotorEx.class, "Motor4");
            leftWheel = hardwareMap.get(CRServo.class, "leftWheel");
            rightWheel = hardwareMap.get(CRServo.class, "rightWheel");
            CRServo rotation = hardwareMap.get(CRServo.class, "rotation");

            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            telemetry.addData("Status", "Initialized");
            telemetry.update();

            waitForStart();

            while (opModeIsActive()) {

                telemetry.addData("Status", "Running");
                telemetry.addData("gamepad1.right_stick_y: ", gamepad1.right_stick_y);
                telemetry.addData("gamepad1.right_stick_x: ", gamepad1.right_stick_x);
                telemetry.addData("gamepad1.left_stick_y: ", gamepad1.left_stick_y);
                telemetry.addData("gamepad1.left_stick_x: ", gamepad1.left_stick_x);
                telemetry.addData("gamepad2.right_trigger: ", gamepad2.right_trigger);
                telemetry.addData("speedLeft: ", speedLeft);
                telemetry.addData("speedRight: ", speedRight);
                telemetry.addData("Motor Left Front Speed: ", frontLeft.getVelocity());
                telemetry.addData("Motor Right Front Speed: ", frontRight.getVelocity());
                telemetry.addData("Motor Left Back Speed: ", backLeft.getVelocity());
                telemetry.addData("Motor Right Back Speed: ", backRight.getVelocity());

                telemetry.update();

                frontLeft.setMotorEnable();
                frontRight.setMotorEnable();
                backLeft.setMotorEnable();
                backRight.setMotorEnable();
                armMotor.setMotorEnable();

                speedLeft = 0.0;
                speedRight = 0.0;
                frontLeftVelocity = 0.0;
                frontRightVelocity = 0.0;
                backLeftVelocity = 0.0;
                backRightVelocity = 0.0;
                armMotorVelocity = 0.0;


                setSpeed();
                setRotation();
                setDirection();
                setArm();
                setLaunch();


                frontLeft.setVelocity(frontLeftVelocity);
                frontRight.setVelocity(frontRightVelocity);
                backLeft.setVelocity(backLeftVelocity);
                backRight.setVelocity(backRightVelocity);

            }
        }

        public void setLaunch(){

            if (gamepad2.right_trigger == 1) {
                leftWheel.setPower(1);
                rightWheel.setPower(1);
            }
            else if (gamepad2.right_trigger == 0){
                leftWheel.setPower(0);
                rightWheel.setPower(0);
            }

        }

        public void setArm() {
            if (gamepad2.right_stick_y <= -0.99 && (Math.abs(gamepad2.right_stick_x) < 0.3)){
                armMotorVelocity += 1250;
            }
            else if (gamepad1.right_stick_y >= 0.99 && (Math.abs(gamepad1.right_stick_x) < 0.3)){
                armMotorVelocity -= 1250;
                }
        }

    public void setSpeed() {
        Double offsetLeft = 1.08;
        Double offsetRight = 0.92;
        if ((gamepad1.right_trigger > 0.7) && (gamepad1.left_trigger > 0.7)) { //normal
            speedLeft = 1250.0 * offsetLeft;
            speedRight = 1250.0 * offsetRight;
        } else if (gamepad1.right_trigger > 0.7) { //fast
            speedLeft = 2000.0 * offsetLeft;
            speedRight = 2000.0 * offsetRight;
        } else if (gamepad1.left_trigger > 0.7) { //slow
            speedLeft = 650.0 * offsetLeft;
            speedRight = 650.0 * offsetRight;
        } else { //normal
            speedLeft = 1250.0 * offsetLeft;
            speedRight = 1250.0 * offsetRight;
        }
    }

    public void setRotation() {
        if (gamepad1.left_stick_x < -0.5) { //rotate left
            frontLeftVelocity -= speedLeft / 2;
            frontRightVelocity += speedRight / 2;
            backLeftVelocity -= speedLeft / 2;
            backRightVelocity += speedRight / 2;
        } else if (gamepad1.left_stick_x > 0.5) { //rotate right
            frontLeftVelocity += speedLeft / 2;
            frontRightVelocity -= speedRight / 2;
            backLeftVelocity += speedLeft / 2;
            backRightVelocity -= speedRight / 2;
        }
    }

    public void setDirection() {
        if (gamepad1.right_stick_y <= -0.99 && (Math.abs(gamepad1.right_stick_x) < 0.3)) { //forward
            frontLeftVelocity += speedLeft;
            frontRightVelocity += speedRight;
            backLeftVelocity += speedLeft;
            backRightVelocity += speedRight;
        } else if (gamepad1.right_stick_y >= 0.99 && (Math.abs(gamepad1.right_stick_x) < 0.3)) { //back
            frontLeftVelocity -= speedLeft;
            frontRightVelocity -= speedRight;
            backLeftVelocity -= speedLeft;
            backRightVelocity -= speedRight;
        } else if (gamepad1.right_stick_x <= -0.99 && (Math.abs(gamepad1.right_stick_y) < 0.3)) { //left
            frontLeftVelocity -= speedLeft;
            frontRightVelocity -= speedRight;
            backLeftVelocity += speedLeft;
            backRightVelocity += speedRight;
        } else if (gamepad1.right_stick_x >= 0.99 && (Math.abs(gamepad1.right_stick_y) < 0.3)) { //right
            frontLeftVelocity += speedLeft;
            frontRightVelocity += speedRight;
            backLeftVelocity -= speedLeft;
            backRightVelocity -= speedRight;
        } else if ((gamepad1.right_stick_y < -0.3) && (gamepad1.right_stick_x < -0.3)) { //forward left
            frontLeftVelocity += 0;
            frontRightVelocity += speedRight;
            backLeftVelocity += speedLeft;
            backRightVelocity += 0;
        } else if ((gamepad1.right_stick_y < -0.3) && (gamepad1.right_stick_x > 0.3)) { //forward right
            frontLeftVelocity += speedLeft;
            frontRightVelocity += 0;
            backLeftVelocity += 0;
            backRightVelocity += speedRight;
        } else if ((gamepad1.right_stick_y > 0.3) && (gamepad1.right_stick_x < -0.3)) { //back left
            frontLeftVelocity -= speedLeft;
            frontRightVelocity += 0;
            backLeftVelocity += 0;
            backRightVelocity -= speedRight;
        } else if ((gamepad1.right_stick_y > 0.3) && (gamepad1.right_stick_x > 0.3)) { //back right
            frontLeftVelocity += 0;
            frontRightVelocity -= speedRight;
            backLeftVelocity -= speedLeft;
            backRightVelocity += 0;
        } else { //no direction
            frontLeftVelocity = frontLeftVelocity * 2; //multiply velocity by 2 to speed up stationary rotation
            frontRightVelocity = frontRightVelocity * 2;
            backLeftVelocity = backLeftVelocity * 2;
            backRightVelocity = backRightVelocity * 2;
        }
    }
}
