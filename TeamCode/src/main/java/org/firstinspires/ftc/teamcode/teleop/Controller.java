package org.firstinspires.ftc.teamcode.teleop;
//we are importing all of the needed code that FTC has given us for the hardware devices
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

//labeling this code to show up in the TeleOp column of the Driver Station App
@TeleOp(name = "Controller", group = "Default")

    public class Controller extends LinearOpMode {
    //define the variables we are going to be using
    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;
    private DcMotorEx armMotor;
    private DcMotorEx handMotor;

    private CRServo leftWheel;
   // private CRServo rightWheel;
    private CRServo rotation;

    //define the speed/velocity variables and set them equal to 0.0
    private Double speedLeft = 0.0;
    private Double speedRight = 0.0;
    private Double frontLeftVelocity = 0.0;
    private Double frontRightVelocity = 0.0;
    private Double backLeftVelocity = 0.0;
    private Double backRightVelocity = 0.0;
    private Double armMotorVelocity = 0.0;
    private Double handMotorVelocity = 0.0;
//initiate the arm and hand motor position variables
    int armMotorPosition;
    int handMotorPosition;

    @Override
        public void runOpMode() {
        //define variables and get them from the robot configuration
            backLeft = hardwareMap.get(DcMotorEx.class, "Motor0");
            frontLeft = hardwareMap.get(DcMotorEx.class, "Motor1");
            backRight = hardwareMap.get(DcMotorEx.class, "Motor2");
            frontRight = hardwareMap.get(DcMotorEx.class, "Motor3");
            armMotor = hardwareMap.get(DcMotorEx.class, "Motor4");
            handMotor = hardwareMap.get(DcMotorEx.class, "Motor5");
            leftWheel = hardwareMap.get(CRServo.class, "leftWheel");
           // rightWheel = hardwareMap.get(CRServo.class, "rightWheel");
            rotation = hardwareMap.get(CRServo.class, "rotation");

            //reset encoders for the arm and hand motors
            armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            handMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //reverse the directions of the frontLeft and backLeft motors so we don't have to use a mix of positive and negative signs when making the robot move forward
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            //tell the motors to brake when their velocity is at 0 so they will hold the arm in place
            armMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            handMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            //set the current position the arm and hand motors are at to position 0
            armMotor.setTargetPosition(0);
            handMotor.setTargetPosition(0);

            //when all of the above has been done, this will show up on the Driver Station App
            telemetry.addData("Status", "Initialized");
            telemetry.update();
            //waiting for the start button to be pressed
            waitForStart();
        //the arm and hand motor positions are the current positions that they are at
        armMotorPosition = armMotor.getCurrentPosition();
        handMotorPosition = handMotor.getCurrentPosition();

        //make the motor run until it reaches the target position
        armMotor.setTargetPosition(armMotorPosition);
        handMotor.setTargetPosition(handMotorPosition);


//make the motor run to the target position
        armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
       handMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            while (opModeIsActive()) {


                //this is all data that will show up on the Driver Station App
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
                telemetry.addData("armMotor Position", armMotorPosition);
                telemetry.addData("handMotor Position", handMotorPosition);
                telemetry.addData("armMotor real position", armMotor.getCurrentPosition());
                telemetry.addData("handMotor real position", handMotor.getCurrentPosition());



                telemetry.update();
//enable the motors so they work
                frontLeft.setMotorEnable();
                frontRight.setMotorEnable();
                backLeft.setMotorEnable();
                backRight.setMotorEnable();
                armMotor.setMotorEnable();
                handMotor.setMotorEnable();
                //set the speed variables to 0.0
                speedLeft = 0.0;
                speedRight = 0.0;
                frontLeftVelocity = 0.0;
                frontRightVelocity = 0.0;
                backLeftVelocity = 0.0;
                backRightVelocity = 0.0;
                armMotorVelocity = 0.0;
                handMotorVelocity = 0.0;

//run all these different things
                setSpeed();
                setRotation();
                setDirection();
                setLaunch();
                setLimit();
                setIntake();




//the velocity of the motor is the velocity that the motor is going at
                frontLeft.setVelocity(frontLeftVelocity);
                frontRight.setVelocity(frontRightVelocity);
                backLeft.setVelocity(backLeftVelocity);
                backRight.setVelocity(backRightVelocity);
                armMotor.setVelocity(armMotorVelocity);
                handMotor.setVelocity(handMotorVelocity);

            }
        }

        public void setIntake() {
        //this is for the intake controls
            //if the left trigger is pushed, the intake will spin in the positive direction.
            if (gamepad2.left_trigger > 0.1) {
                rotation.setPower(1);

            }
            //if the right trigger is pushed, the intake will spin in the negative direction.
            else if (gamepad2.right_trigger > 0.1){
                rotation.setPower(-1);
            }
            //if both the right trigger and the left trigger are not being pushed, the intake will not spin.
            else if (gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0) {
                rotation.setPower(0);
            }
        }

        public void setLimit(){
        //this is for the arm controls
        //this is the sensitivity of the gamepad sticks and how much that value is multiplied to calculate the arm and hand motor position
            armMotorPosition += -gamepad2.right_stick_y * 10;
            handMotorPosition += -gamepad2.left_stick_y * 10;
            //the arm and hand motor's target position is the value set by the gamepad stick * 10
            armMotor.setTargetPosition(armMotorPosition);
            handMotor.setTargetPosition(handMotorPosition);
            //the arm motor velocity will be 500 ticks per second
            armMotorVelocity = 500.0;
            //the hand motor velocity will be 1000 ticks per second
            handMotorVelocity = 1000.0;
            //here, we are setting limits so the robot arm and hand don't go crazy
            if(armMotorPosition < -500){
                armMotorPosition = -500;
            }
            if(handMotorPosition < 0){
                handMotorPosition = 0;
            }
            if(armMotorPosition > 0){
                armMotorPosition = 0;
            }
            if(handMotorPosition > 1000){
                handMotorPosition = 1000;
            }
        }

        public void setLaunch(){
//this is for the airplane launcher
            if (gamepad2.right_bumper) {
                leftWheel.setPower(1);
            //    rightWheel.setPower(1);
            }
            else {
                leftWheel.setPower(0);
           //     rightWheel.setPower(0);
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
