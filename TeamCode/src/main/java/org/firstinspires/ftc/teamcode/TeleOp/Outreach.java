package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystem.Arm;
import org.firstinspires.ftc.teamcode.Subsystem.DriveTrain;

@TeleOp
public class Outreach extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        if (isStopRequested()) return;
        DriveTrain driveTrain = new DriveTrain();
        Arm arm = new Arm();
        driveTrain.initiate(hardwareMap);
        arm.initiate(hardwareMap);
        while (opModeIsActive()) {
        
        }
    }
}
