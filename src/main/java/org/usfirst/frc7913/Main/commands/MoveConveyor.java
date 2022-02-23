package org.usfirst.frc7913.Main.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc7913.Main.Robot;

public class MoveConveyor extends Command {

    
    public MoveConveyor() {
        requires(Robot.DriveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.Conveyor.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.Conveyor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
