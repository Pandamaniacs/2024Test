package frc.robot.subsystems.shooter;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class ShooterSim implements ShooterIO {
    private double velocity = 0;
    private double angle = 0;

    private PIDController pidController = new PIDController(1, 0, 0);
    private final FlywheelSim wheelSim = new FlywheelSim(DCMotor.getFalcon500(1), 1, 1);

    public ShooterSim() {
    }

    public void updateInputs(ShooterIOInputs inputs) {
        double appliedVoltage = 12.0
                * pidController.calculate(wheelSim.getAngularVelocityRPM() / 60, this.velocity);

        wheelSim.setInputVoltage(appliedVoltage);
        wheelSim.update(0.02);

        inputs.appliedVoltage = appliedVoltage;
        inputs.currentAmperage = wheelSim.getCurrentDrawAmps();
        inputs.velocity = wheelSim.getAngularVelocityRPM() / 60.0;
    }
    @Override
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public void setInputVoltage(double voltage) {
        this.wheelSim.setInputVoltage(voltage);
    }
}
