package org.usfirst.frc7913.Main.subsystems;

//import java.sql.Time;
import java.time.Instant;

import org.usfirst.frc7913.Main.Robot;
import org.usfirst.frc7913.Main.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveTrain extends Subsystem {
    private PWMVictorSPX leftLead;
    private PWMVictorSPX leftFollow;
    private MotorControllerGroup leftSide;
    private PWMVictorSPX rightLead;
    private PWMVictorSPX rightFollow;
    private MotorControllerGroup rightSide;

    private DifferentialDrive DriveTrain;

    public DriveTrain() {
        // Left motors looking from the back of the robot
        // Front-Left motor
        leftLead = new PWMVictorSPX(1);
        addChild("leftLead", leftLead);
        leftLead.setInverted(true);
        // Rear-Left motor
        leftFollow = new PWMVictorSPX(0);
        addChild("leftFollow", leftFollow);
        leftFollow.setInverted(true);

        // Assigning left motors to the same controller group
        leftSide = new MotorControllerGroup(leftLead, leftFollow);

        // Right motors looking from the back of the robot
        // Front-Right motor
        rightLead = new PWMVictorSPX(3);
        addChild("rightLead", rightLead);
        rightLead.setInverted(false);

        // Rear-Right motor
        rightFollow = new PWMVictorSPX(2);
        addChild("rightFollow", rightFollow);
        rightFollow.setInverted(false);

        // Assigning right motors to the same controller group
        rightSide = new MotorControllerGroup(rightLead, rightFollow);

        // Using differential Drive which is similar to a tank or skid steer
        // If using mechanum Drive, this must be changed to creating a MecanumDrive
        // object instead
        DriveTrain = new DifferentialDrive(leftSide, rightSide);

        // DriveTrain config
        DriveTrain.setSafetyEnabled(true);
        DriveTrain.setExpiration(0.1);
        DriveTrain.setMaxOutput(0.6);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }

    public void autoMode(int duration) throws InterruptedException {
        long stopTime = Instant.now().getEpochSecond() + duration;

        while (stopTime > Instant.now().getEpochSecond()) {
            DriveTrain.arcadeDrive(0.4, 0, false);
        }
        DriveTrain.arcadeDrive(0, 0, false);
        
    }

    @Override
    public void periodic() {
        // Gets X & Y axes from the joystick controller
        // Arcade Drive combines the two motor controller groups, first arg is
        // forward/reverse, second is rotation.
        // Unlike tank Drive which has the two motor controller groups separate where
        // the first arg is forward/reverse for the left side and the second is the same
        // for the rightP
        DriveTrain.arcadeDrive(Robot.io.xboxController.getLeftY(), Robot.io.xboxController.getLeftX()*-1, true);
    }
}
