package org.usfirst.frc7913.Main.subsystems;
import org.usfirst.frc7913.Main.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

public class Conveyor extends Subsystem {
    private PWMVictorSPX motor;

    public Conveyor() {
        motor = new PWMVictorSPX(7);
    }

    public void disableMotor() {
        setSpeed(0);
    }

    public void setSpeed(double speed) {
        speed = speed * -1;
        motor.set(speed);
    }

    public Command start(){
        setSpeed(0.75);
        return null;
    }

    public Command stop(){
        setSpeed(0);
        return null;
    }

    @Override
    public void periodic() {
        setSpeed(Robot.io.xboxController.getRightY() * -1);
       // System.out.println(Robot.io.xboxController.getRightY());;
    }

    @Override
    protected void initDefaultCommand() {
    }
}
