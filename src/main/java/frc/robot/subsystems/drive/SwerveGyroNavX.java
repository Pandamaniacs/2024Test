package frc.robot.subsystems.drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveGyroNavX implements SwerveGyroIO {
    private final AHRS m_NavX;

    public SwerveGyroNavX() {
        m_NavX = new AHRS(SPI.Port.kMXP); // instantiates gyroscope
        SmartDashboard.putString("GYRO_TYPE", "NavX");
        m_NavX.reset();
    }

    @Override
    public void updateInputs(SwerveGyroIOInputs inputs) {
        inputs.isConnected = m_NavX.isConnected();
        inputs.yawPositionRad = Units.degreesToRadians(getYaw());
        inputs.yawVelocityRadPerSec = Units.degreesToRadians(getYawVelocity());
    }

    public double getYaw() {
        SmartDashboard.putNumber("GYRO_READING", -m_NavX.getAngle());
        return -m_NavX.getAngle(); // must be negative or rotation issue happens.
    }

    public double getYawVelocity() {
        return m_NavX.getRate();
    }

    @Override
    public void zero() {
        m_NavX.reset();
    }
}
