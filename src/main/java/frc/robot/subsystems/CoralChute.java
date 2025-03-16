// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.Constants.CoralChuteConstants.*;

public class CoralChute extends SubsystemBase {
    private SparkMax leftOutputMotor;
    private SparkMax rightOutputMotor;

    public CoralChute() {
        SparkMaxConfig leftConfig = new SparkMaxConfig();
        leftConfig.inverted(false);
        
        SparkMaxConfig rightConfig = new SparkMaxConfig();
        rightConfig.inverted(true);
        // leftOutputMotor = new SparkMax(kLeftOutputMotorPort, MotorType.kBrushless);
        //  leftOutputMotor.configure(notInvertedConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // rightOutputMotor = new SparkMax(kRightOutputMotorPort, MotorType.kBrushless);
        //  rightOutputMotor.configure(notInvertedConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void setLeftOutputMotor(double speed) {
        leftOutputMotor.set(speed);
    }

    public void setRightOutputMotor(double speed) {
        rightOutputMotor.set(speed);
    }
}

