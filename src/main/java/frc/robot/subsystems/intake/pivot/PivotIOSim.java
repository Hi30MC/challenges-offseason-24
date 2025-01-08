package frc.robot.subsystems.intake.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.Constants;

public class PivotIOSim implements PivotIO {
  // For instructions on how to implement this class, refer to the README.md file

  private SingleJointedArmSim m_sim;
  private DCMotor m_motor;
  private double m_voltage;

  public PivotIOSim() {
    m_motor = DCMotor.getNEO(1);
    m_sim =
        new SingleJointedArmSim(
            m_motor,
            Constants.IntakeConstants.kPivotGearing,
            Constants.IntakeConstants.kPivotJKgMetersSquared,
            Constants.IntakeConstants.kPivotLength,
            Constants.IntakeConstants.kPivotMinAngle,
            Constants.IntakeConstants.kPivotMaxAngle,
            false,
            2 * Math.PI / 3);
    m_voltage = 0;
  }

  @Override
  public void updateInputs(PivotInputs inputs) {
    m_sim.update(0.02);

    inputs.voltage = getVoltage();
    inputs.velocityRadPerSec = getVelocityRadPerSec();
    inputs.angleRad = getAngle().getRadians();
  }

  @Override
  public void setVoltage(double voltage) {
    m_sim.setInput(voltage);
    m_voltage = voltage;
  }

  @Override
  public double getVoltage() {
    return m_voltage;
  }

  @Override
  public double getVelocityRadPerSec() {
    return m_sim.getVelocityRadPerSec();
  }

  @Override
  public Rotation2d getAngle() {
    return new Rotation2d(m_sim.getAngleRads());
  }
}
