package org.usfirst.frc7913.Main;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc7913.Main.commands.*;
import org.usfirst.frc7913.Main.subsystems.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.cscore.CameraServerCvJNI;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static IO io;
    public static DriveTrain DriveTrain;
    public static Intake Intake = new Intake();
    public static Conveyor Conveyor = new Conveyor();
    public static Shooter Shooter = new Shooter();
    private JoystickButton startShoot;
    private JoystickButton stopShoot;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        System.out.println("Robot is initalizing?");
        DriveTrain = new DriveTrain();
        io = new IO();
        startShoot = new JoystickButton(io.getXboxController(), 1);
        stopShoot = new JoystickButton(io.getXboxController(), 2);


        // chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

        SmartDashboard.putData("Auto mode", chooser);

        startShoot.whenPressed(new StartIntake());
        stopShoot.whenPressed(new StopIntake());

        //Starts the camera server based on what was given in these docs
        //https://docs.wpilib.org/en/stable/docs/software/vision-processing/roborio/using-the-cameraserver-on-the-roborio.html
        CameraServer.startAutomaticCapture();
        CameraServer.startAutomaticCapture();
    }

    @Override
    public void robotPeriodic() {

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        // autonomousCommand = chooser.getSelected();
        // // schedule the autonomous command (example)
        // if (autonomousCommand != null)
        //     autonomousCommand.start();

        Shooter.setSpeed(0.8);
                
        driveWait(3);

        wait(1500);
        
        Conveyor.setSpeed(0.65);

        wait(1500);

        Intake.start();
        Conveyor.setSpeed(0.5);
        wait(500);

       driveWait(2);
        Intake.setSpeed(0);

        Conveyor.setSpeed(-0.5);
        wait(400);
        Conveyor.setSpeed(0);

        Shooter.setSpeed(0.95);
        
        wait(1500);

        Conveyor.setSpeed(0.5);

        wait(1500);

        Conveyor.setSpeed(0);
        Shooter.setSpeed(0);
        
    }

    static void driveWait(int sec){
        try {
            DriveTrain.autoMode(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void wait(int ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
     //   System.out.println("TEST");
    }
}

