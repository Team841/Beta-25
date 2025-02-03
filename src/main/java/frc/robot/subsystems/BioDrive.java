package frc.robot.subsystems;

import com.ctre.phoenix6.swerve.SwerveModule;
import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.generated.TunerConstants;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import static edu.wpi.first.units.Units.*;

public class BioDrive extends Command {

	public BioDrive(CommandSwerveDrivetrain drivetrain, DoubleSupplier velocityXGetter, DoubleSupplier velocityYGetter, DoubleSupplier velocityOmegaGetter, BooleanSupplier snapGetter){
		this.drivetrain = drivetrain;
		this.mVelocityX = velocityXGetter;
		this.mVelocityY = velocityYGetter;
		this.mVelocityOmega = velocityOmegaGetter;
		this.snapGetter = snapGetter;

		this.controller.setTolerance(1);

		addRequirements(drivetrain);
		setName("BioDrive");
	}

	private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
	private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

	/* Setting up bindings for necessary control of the swerve drive platform */
	private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
			.withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
			.withDriveRequestType(SwerveModule.DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
	private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
	private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
	private final SwerveRequest.ApplyRobotSpeeds robotSpeeds = new SwerveRequest.ApplyRobotSpeeds();

	private CommandSwerveDrivetrain drivetrain;

	private DoubleSupplier mVelocityX, mVelocityY, mVelocityOmega;
	private BooleanSupplier snapGetter;

	private double velocity_y = 0.0;
	private double velocity_x = 0.0;
	private double velocity_omega = 0.0;
	private boolean snap = false;

	private String limelightName = "limelight";

	private Pose2d goal = new Pose2d(14.31,3.86, new Rotation2d(Math.PI));

	private PIDController controller = new PIDController(0.5, 0, 0.5/4);

	@Override
	public void initialize() {
		return;
	}

	@Override
	public void execute() {

		this.velocity_x = mVelocityX.getAsDouble();
		this.velocity_y = mVelocityY.getAsDouble();
		this.velocity_omega = mVelocityOmega.getAsDouble();
		this.snap = snapGetter.getAsBoolean();

		double setpoint = 23.3;
		SmartDashboard.putNumber("Setpoint", setpoint);
		double speed = controller.calculate(LimelightHelpers.getTX(limelightName), setpoint);
		SmartDashboard.putNumber("NOT Clamped Output", speed);
		speed = speed >= 0 ? Math.max(0, Math.min(speed, 8.5)) : -Math.max(0, Math.min(-speed, 8.5));
		speed /= 8.5;
		SmartDashboard.putNumber("Clamped Ouput", speed);
		if (this.snap){
			this.drivetrain.setControl(robotSpeeds.withSpeeds(new ChassisSpeeds(0, speed, 0)));
		} else {
			this.drivetrain.setControl(drive.withVelocityX(-this.velocity_x).withVelocityY(-this.velocity_y).withRotationalRate(this.velocity_omega));
		}
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void end(boolean interrupted) {
		System.out.println("Ended");
	}




}