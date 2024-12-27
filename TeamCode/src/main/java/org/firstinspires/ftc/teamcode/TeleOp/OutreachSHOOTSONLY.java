package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class OutreachSHOOTSONLY extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        if (isStopRequested()) return;
        Shooter shooter = new Shooter();
        shooter.initiate(hardwareMap);
        DriveTrain driveTrain = new DriveTrain();
                driveTrain.initiate(hardwareMap);
        while (opModeIsActive()) {
            shooter.shootServo(gamepad1.right_bumper);
            shooter.shootMotor(gamepad1.left_bumper);
        }
    }
}
