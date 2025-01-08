package frc.robot.subsystems.intake.rollers;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.Constants;

public class RollerIOSim implements RollerIO {
  // For instructions on how to implement this class, refer to the README.md file

  private DCMotorSim m_sim;
  // define more members here as necessary
  private double m_voltage;

  public RollerIOSim() {
    m_sim =
        new DCMotorSim(
            DCMotor.getKrakenX60(1),
            Constants.IntakeConstants.kRollerGearing,
            Constants.IntakeConstants.kRollerJKgMetersSquared);
    m_voltage = 0;
  }

  @Override
  public void updateInputs(RollerInputs inputs) {
    m_sim.update(0.02);

    inputs.voltage = getVoltage();
    inputs.velocityRadPerSec = getVelocityRadPerSec();
  }

  @Override
  public void setVoltage(double voltage) {
    m_sim.setInputVoltage(voltage);
    m_voltage = voltage;
  }

  @Override
  public double getVoltage() {
    return m_voltage;
  }

  @Override
  public double getVelocityRadPerSec() {
    return m_sim.getAngularVelocityRadPerSec();
  }
}
