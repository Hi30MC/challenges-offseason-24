package frc.robot.subsystems.wrist;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {
  // For instructions on how to implement this class, refer to the README.md file

  private WristIO m_io;
  private WristInputsAutoLogged m_inputs;
  private PIDController m_controller;
  private double m_controllerSetpoint;

  public Wrist(WristIO io, PIDController controller) {
    m_io = io;
    m_inputs = new WristInputsAutoLogged();
    m_controller = controller;
  }

  @Override
  public void periodic() {
    m_io.updateInputs(m_inputs);
    m_io.setVoltage(m_controller.calculate(m_io.getAngle().getDegrees(), m_controllerSetpoint));
  }

  public void setDesiredAngle(Rotation2d angle) {
    m_controllerSetpoint = angle.getDegrees();
  }

  public Command setDesiredAngleCommand(Rotation2d angle) {
    return runOnce(() -> setDesiredAngle(angle));
  }

  public boolean withinTolerance() {
    return m_controller.atSetpoint();
  }

  public WristInputsAutoLogged getInputs() {
    return m_inputs;
  }
}
