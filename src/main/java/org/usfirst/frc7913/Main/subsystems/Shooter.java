package org.usfirst.frc7913.Main.subsystems;
import org.usfirst.frc7913.Main.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

public class Shooter extends Subsystem {
    private PWMVictorSPX bottomMotor;
    private PWMVictorSPX topMotor;

    public Shooter() {
        bottomMotor = new PWMVictorSPX(5);
        topMotor = new PWMVictorSPX(6);
    }

    public void disableMotor() {
        setSpeed(0);
    }

    public void setSpeed(double speed) {
        speed = speed * -1;
        topMotor.set(speed);
        bottomMotor.set(speed);
    }

    public Command start(){
        setSpeed(1);
        return null;
    }

    public Command stop(){
        setSpeed(0);
        return null;
    }

    @Override
    public void periodic() {
        setSpeed((Robot.io.xboxController.getRightTriggerAxis() * 0.8) + (Robot.io.xboxController.getLeftTriggerAxis() * 0.5));
    }

    @Override
    protected void initDefaultCommand() {
    }
}
