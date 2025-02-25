package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.commands.IntakeCommands.SetIntakeAngle;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;




public class RobotContainer {

  public final Intake m_intake = new Intake();
  private final XboxController  controller = new XboxController (0);

  
  public RobotContainer() {
    configureBindings();
    m_intake.setDefaultCommand(new SetIntakeAngle(m_intake));
  }
  
  
  private void configureBindings() {
    final JoystickButton buttonA = new JoystickButton(controller, XboxController.Button.kA.value);
    final JoystickButton buttonB = new JoystickButton(controller, XboxController.Button.kB.value);

    //3
    buttonA.onTrue(new InstantCommand(() -> m_intake.ponerAngulo(90)));
    buttonB.onTrue(new InstantCommand(() -> m_intake.ponerAngulo(0)));
  }

  public XboxController getController(){
    return controller;
  }

  public Command getAutonomousCommand() {
    return Autos.exampleAuto(null);
  }

}
