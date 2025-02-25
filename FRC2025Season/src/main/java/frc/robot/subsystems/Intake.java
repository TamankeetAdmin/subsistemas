package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private final SparkMax muneca = new SparkMax(3, MotorType.kBrushless);
  private RelativeEncoder encoder;
  private PIDController PIDMuneca;

  // PID Constants
  private static final double kP = 0.1;
  private static final double kI = 0.0;
  private static final double kD = 0.0;

  // Encoder and gearbox parameters
  private static final int NEO_TICKS_PER_REV = 4092;  // Ticks per revolution for the Neo internal encoder
  private static final int GEARBOX_RATIO = 48;          // Gearbox ratio
  private static final double TICKS_PER_REVOLUTION = NEO_TICKS_PER_REV / GEARBOX_RATIO;  // Total ticks per output revolution
  private static final double TICKS_PER_DEGREE = TICKS_PER_REVOLUTION / 360.0;

  double setPoint = 0;

  /** Creates a new Intake. */
  public Intake() {
    //encoder = muneca.getEncoder();
    encoder.setPosition(0);  // Optionally reset encoder position

    PIDMuneca = new PIDController(kP, kI, kD);
    PIDMuneca.setTolerance(1);
  }

  @Override
  public void periodic() {
    // You can call actualizarMotor() here if you want continuous updates
  }

  public void ponerAngulo(double angulo) {
    // Convert desired angle in degrees to encoder ticks
    setPoint = angulo * TICKS_PER_DEGREE;
    PIDMuneca.setSetpoint(setPoint);
  }

  public void actualizarMotor() {
    double currentPositionTicks = encoder.getPosition();
    double pidOutput = PIDMuneca.calculate(currentPositionTicks, setPoint);
    muneca.set(pidOutput);
    System.out.println("SetPoint (ticks): " + setPoint);
    System.out.println("Encoder Position (ticks): " + currentPositionTicks);
    System.out.println("Current Angle (deg): " + getCurrentAngle());
  }

  public double getCurrentAngle() {
    return encoder.getPosition() / TICKS_PER_DEGREE;
  }
}