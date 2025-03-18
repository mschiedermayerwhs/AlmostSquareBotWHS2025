// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants {
       
    public static final class DriveConstants {
       public static final int kBackLeftDrivePort = 2;
       public static final int kFrontLeftDrivePort = 4;
       public static final int kFrontRightDrivePort = 1;
       public static final int kBackRigthDrivePort = 3; 

       public static final int kTicksPerRevolution = 42;     // default for Rev Neo
       public static final double kDriveGearRatio = 8.45;       // default for ToughBox Mini S
       public static final double kDriveWheelsCircumference = 152.4 * Math.PI; //6 inch diam in mm 
       public static final double kDriveEncoderConversionFactor = kDriveGearRatio * kDriveWheelsCircumference / kTicksPerRevolution;

       public static final double kMinOutput = -0.25; // only used
       public static final double kMaxOutput = 0.25;
       public static final double kP = 0.0005;
       public static final double kI = 0.0;
       public static final double kD = 0.0;
       
    }    

    public static final class ElevatorConstants {
        public static final int kElevatorDriveLeftPort = 5;
        public static final int kElevatorDriveRightPort = 6;

        public static final int kTicksPerRevolution = 42;     // default for Rev Neo
        public static final double kGearRatio = 6; // 12:1 gearbox, 1:2 belt pulleys
        public static final double kSprocketCircumference = 45*Math.PI; // stick with 45 mm diam
        public static final double kElevatorEncoderConversionFactor = kGearRatio * kSprocketCircumference / kTicksPerRevolution;

        public static final double kP = 0.01; //.01 is stable and sounds good, .02 has small oscillation sounds
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        public static final double kMinOutput = -0.5;
        public static final double kMaxOutput = 0.5; 

        public static final double lvl0Height = 0; //checked good
        public static final double lvl1Height = 310; //good
        public static final double lvl2Height = 860; //good
        public static final double lvl3Height = 1890; //good, as high as possible
        public static final double minHeight = 0;
        public static final double maxHeight = 1900;
    }

    // Double check constants for AlgaeGrabber Motors
    public static final class AlgaeGrabberConstants {
        public static final int kArmRotateMotorPort = 7; // 
        public static final int kBallGrabberMotorPort = 8;

        public static final int kTicksPerRevolution = 42;     // default for Rev Neo
        public static final double kArmRotateGearRatio = 80; // gearbox is 80:1
        public static final double kArmRotateSprocketCircumference = 45 * Math.PI;
        public static final double kArmRotateConverionFactor = kArmRotateGearRatio * kArmRotateSprocketCircumference / kTicksPerRevolution;

        public static final double kP = .001;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        public static final double kMinOutput = -0.25;
        public static final double kMaxOutput = 0.25;

        public static final double kMinPosition = 0;
        public static final double kMaxPosition = 100;

        public static final double kArmDownPos = 0;
        public static final double kArmMidPos = 3000;
        public static final double kArmUpPos = 8500;  // TODO tune this
    }

    public static final class CoralChuteConstants {
        public static final int kLeftOutputMotorPort = 9;
        public static final int kRightOutputMotorPort = 10;
    }
}

