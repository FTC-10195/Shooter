package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;

import java.sql.Time;

@Config
public class Shooter {
    DcMotor shooterMotor;
    Servo shooterServo;
    double shootTime;
    double autoShootTime;
    public void initiate(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.dcMotor.get("shooter");
        shooterServo = hardwareMap.servo.get("servo");
        shooterServo.setDirection(Servo.Direction.REVERSE);
        shootTime = System.currentTimeMillis();
        autoShootTime = System.currentTimeMillis();
    }
    boolean rightbumperPressed = false;
    public static double shooterRestPos = .5;
    public static double shooterShootPos = .65;
    double shooterPos = shooterRestPos;
    public void shootServo(boolean rightBumper) {
        if (!rightbumperPressed && rightBumper){
            shootTime = System.currentTimeMillis();
            rightbumperPressed = true;
                shooterPos = shooterShootPos;
        }else if (!rightBumper){
            rightbumperPressed = false;
        }
        if (System.currentTimeMillis() - shootTime > 200){
            shooterPos = shooterRestPos;
        }
    }
   public static double MIN_REST_TIME = .14;
    public static double MIN_FIRE_HOLD_TIME = .14;
    public void autoShooter(double rightTrigger, Telemetry telemetry){
        double REST_RATE = MIN_REST_TIME + (1 - rightTrigger);
        double REST_TIME = REST_RATE * 1000; //Converts to an actual time in milliseconds
        double HOLD_TIME = MIN_FIRE_HOLD_TIME * 1000;
        double TimePassed = System.currentTimeMillis() - autoShootTime;
        if (rightTrigger > .1) {
            if (TimePassed < HOLD_TIME){
                shooterPos = shooterShootPos;
            } else {
                shooterPos = shooterRestPos;
                if (TimePassed > (HOLD_TIME + REST_TIME)){
                    autoShootTime = System.currentTimeMillis();
                }
            }
        }else{
            autoShootTime = System.currentTimeMillis();
        }
    }
    public void updateShooter(){
        shooterServo.setPosition(shooterPos);
    }
    boolean leftbumperPressed = false;
    boolean motorRunning = false;
    public static double maxPower =1;
    double power = 0;
    public void shootMotor(boolean leftBumper) {
        if (!leftbumperPressed && leftBumper){
            leftbumperPressed = true;
            if (motorRunning == false){
                motorRunning = true;
                power = -maxPower;
            } else{
                power= 0;
                motorRunning = false;
            }
        }else if (!leftBumper){
            leftbumperPressed = false;
        }
        shooterMotor.setPower(power);
    }
    public void motorController(double leftTrigger){
        if (leftTrigger > Math.abs(power)){
            shooterMotor.setPower(-leftTrigger);
        } else {
            shooterMotor.setPower(power);
        }
    }
}
