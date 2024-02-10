package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {
    @AutoLog
    public static class ShooterIOInputs {
        public double appliedVoltage = 0.0;
        public double currentAmperage = 0.0;
        public double velocity = 0.0;
    }

    public default void updateInputs(ShooterIOInputs inputs) {}

    public default void setVelocity(double velocity) {}    
    
    public default void setInputVoltage(double voltage) {}

}
