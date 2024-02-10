package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AdvantageKitConstants;

public class ShooterSubsystem extends SubsystemBase {
    public ShooterIO top;
    public ShooterIO bottom;

    public ShooterIOInputsAutoLogged topInputs = new ShooterIOInputsAutoLogged();
    public ShooterIOInputsAutoLogged bottomInputs = new ShooterIOInputsAutoLogged();

    public ShooterSubsystem() {
        switch (AdvantageKitConstants.getMode()) {
            case REAL:
                top = new ShooterSparkMAX();
                bottom = new ShooterSparkMAX();
                break;
            case SIM:
                top = new ShooterSim();
                bottom = new ShooterSim();
                break;
            case REPLAY:
            default:
                top = new ShooterIO() {

                };
                bottom = new ShooterIO() {

                };
                break;
        }
    }

    @Override
    public void periodic() {
        top.updateInputs(topInputs);
        bottom.updateInputs(bottomInputs);

        Logger.processInputs("Shooter/Top", topInputs);
        Logger.processInputs("Shooter/Bottom", bottomInputs);

        if (DriverStation.isDisabled()) {
            this.top.setVelocity(0);
            this.bottom.setVelocity(0);
        }

    }


    public void runVelocity(double velocity) {
        // double intialv = 50;
        top.setVelocity(velocity);
        bottom.setVelocity(velocity);
    }

    public Command runVelocityCommand() {
        return new InstantCommand(() -> runVelocity(50));
    }

    public Command stopVelocityCommand() {
        return new InstantCommand(() -> runVelocity(0));
    }


}
