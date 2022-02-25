package org.usfirst.frc7913.Main.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class Shooter extends Subsystem {
    private PWMSparkMax bottomMotor;
    private PWMSparkMax topMotor;

    public Shooter() {
        bottomMotor = new PWMSparkMax(5);
        topMotor = new PWMSparkMax(6);
    }

    public void disableMotor() {
        setSpeed(0);
    }

    public void setSpeed(double speed) {
        topMotor.set(speed * -1);
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
    protected void initDefaultCommand() {
    }
}
