// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.pathplanner.lib.path.PathConstraints;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class AdvantageKitConstants {
        public static enum Mode {
            REAL,
            REPLAY,
            SIM
        }

        private static Mode kfakeMode = Mode.SIM;

        public static Mode getMode() {
            return RobotBase.isReal() ? Mode.REAL : kfakeMode;
        }
    }

    public static final class PathPlannerConstants {
        public static final Alliance DEFAULT_ALLIANCE = Alliance.Blue;

        public static final double kMaxAngularAcceleration = 4 * Math.PI; // This was made up
        public static final double kMaxAccelerationMetersPerSecondSquared = 3.00; // This was made up

        public static final PathConstraints DEFAULT_PATH_CONSTRAINTS = new PathConstraints(
                DriveConstants.kMaxSpeedMetersPerSecond,
                PathPlannerConstants.kMaxAccelerationMetersPerSecondSquared,
                DriveConstants.kMaxAngularSpeed,
                5 * Math.PI);

        public static final PathConstraints TEST_PATH_CONSTRAINTS = new PathConstraints(
                1.0,
                PathPlannerConstants.kMaxAccelerationMetersPerSecondSquared,
                DriveConstants.kMaxAngularSpeed,
                5 * Math.PI);
    }

    public static final class DriveConstants {
        // Driving Parameters - Note that these are not the maximum capable speeds of
        // the robot, rather the allowed maximum speeds
        public static final double kMaxSpeedMetersPerSecond = 4.2;
        public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second

        public static final double kDirectionSlewRate = 1.2; // radians per second
        public static final double kMagnitudeSlewRate = 1.8; // percent per second (1 = 100%)
        public static final double kRotationalSlewRate = 2.0; // percent per second (1 = 100%)

        // Chassis configuration
        public static final double kTrackWidth = Units.inchesToMeters(20.5);
        // Distance between centers of right and left wheels on robot
        public static final double kWheelBase = Units.inchesToMeters(20.5);
        // Distance between front and back wheels on robot
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

        // Angular offsets of the modules relative to the chassis in radians
        public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
        public static final double kFrontRightChassisAngularOffset = Math.PI * 2;
        public static final double kBackLeftChassisAngularOffset = Math.PI;
        public static final double kBackRightChassisAngularOffset = Math.PI / 2;

        // SPARK MAX CAN IDs
        public static final int kFrontLeftDrivingCanId = 1;
        public static final int kRearLeftDrivingCanId = 3;
        public static final int kFrontRightDrivingCanId = 2;
        public static final int kRearRightDrivingCanId = 4;

        public static final int kFrontLeftTurningCanId = 5;
        public static final int kRearLeftTurningCanId = 7;
        public static final int kFrontRightTurningCanId = 6;
        public static final int kRearRightTurningCanId = 8;

        public static final int frontRightEncoder = 0;
        public static final int frontLeftEncoder = 2;
        public static final int backRightEncoder = 1;
        public static final int backLeftEncoder = 3;

        public static final boolean kGyroReversed = false;
        public static final int kGyroCanId = 1;
    }

    public static final class ModuleConstants {
        // The MAXSwerve module can be configured with one of three pinion gears: 12T,
        // 13T, or 14T.
        // This changes the drive speed of the module (a pinion gear with more teeth
        // will result in a
        // robot that drives faster).
        public static final int kDrivingMotorPinionTeeth = 13;

        // Invert the turning encoder, since the output shaft rotates in the opposite
        // direction of
        // the steering motor in the MAXSwerve Module.
        public static final boolean kTurningEncoderInverted = true;

        // Calculations required for driving motor conversion factors and feed forward
        public static final double kDrivingMotorFreeSpeedRps = NeoMotorConstants.kFreeSpeedRpm / 60;
        public static final double kWheelDiameterMeters = 0.0762;
        public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
        // 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15
        // teeth on the bevel pinion
        public static final double kDrivingMotorReduction = (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);
        public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters)
                / kDrivingMotorReduction;

        public static final double kDrivingEncoderPositionFactor = (kWheelDiameterMeters * Math.PI)
                / kDrivingMotorReduction; // meters
        public static final double kDrivingEncoderVelocityFactor = ((kWheelDiameterMeters * Math.PI)
                / kDrivingMotorReduction) / 60.0; // meters per second

        public static final double kTurningEncoderPositionFactor = (2 * Math.PI); // radians
        public static final double kTurningEncoderVelocityFactor = (2 * Math.PI) / 60.0; // radians per second

        public static final double kTurningEncoderPositionPIDMinInput = 0; // radians
        public static final double kTurningEncoderPositionPIDMaxInput = kTurningEncoderPositionFactor; // radians

        public static final double kDrivingP = .45;
        public static final double kDrivingI = 0;
        public static final double kDrivingD = 0;
        public static final double kDrivingFF = .5 / kDriveWheelFreeSpeedRps;
        public static final double kDrivingMinOutput = -1;
        public static final double kDrivingMaxOutput = 1;

        public static final double kTurningP = 0.15;
        public static final double kTurningI = 0;
        public static final double kTurningD = 0;
        public static final double kTurningFF = 0;
        public static final double kTurningMinOutput = -1;
        public static final double kTurningMaxOutput = 1;

        public static final IdleMode kDrivingMotorIdleMode = IdleMode.kBrake;
        public static final IdleMode kTurningMotorIdleMode = IdleMode.kBrake;

        public static final int kDrivingMotorCurrentLimit = 50; // amps
        public static final int kTurningMotorCurrentLimit = 20; // amps
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kOperatorControllerPort = 1;
        public static final double kDriveDeadband = 0.05;
    }

    public static final class NeoMotorConstants {
        public static final double kFreeSpeedRpm = 5676;
    }

    public static final class IntakeConstants {
        public static final CurrentLimitsConfigs rollerCurrentConfig = new CurrentLimitsConfigs()
                .withSupplyCurrentLimit(30.0)
                .withSupplyCurrentThreshold(35.0)
                .withSupplyTimeThreshold(0.5);

        public static final int kTopRollerCanId = 2;
        public static final int kBottomRollerCanId = 3;
        public static final int kBeamBreakChannel = 0;

        public static final double kPivotMinDegrees = 10.0;
        public static final double kPivotMaxDegrees = 167.25;

        public static final double kPivotReduction = 240.0;

        public static final int kPivotCanId = 20;
        public static final double kPivotVelocityRadiansPerSecond = Units.degreesToRadians(15.0);
    }
}
