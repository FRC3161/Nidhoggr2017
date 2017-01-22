
package org.usfirst.frc.team3161.robot;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.TitanBot;
import ca.team3161.lib.robot.motion.drivetrains.Drivetrains;
import ca.team3161.lib.robot.motion.drivetrains.TankDrivetrain;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import ca.team3161.lib.utils.controls.DeadbandJoystickMode;
import ca.team3161.lib.utils.controls.InvertedJoystickMode;
import ca.team3161.lib.utils.controls.LogitechDualAction;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechAxis;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechControl;
import edu.wpi.first.wpilibj.Victor;

public class Robot extends TitanBot {

	private final TankDrivetrain drivetrain = Drivetrains.tankdrive()
			.leftControllers(new Victor(0))
			.rightControllers(new Victor(1))
			.build();
	private final LogitechDualAction gamepad = new LogitechDualAction(0);
	private final RepeatingPooledSubsystem timedPrinter = new TimedPrinter();

	@Override
	public int getAutonomousPeriodLengthSeconds() {
		return 15;
	}

	@Override
	public void robotSetup() {
		registerLifecycleComponent(drivetrain);
		registerLifecycleComponent(gamepad);
		
		gamepad.setMode(new DeadbandJoystickMode(0.1).andThen(new InvertedJoystickMode()));
		gamepad.map(LogitechControl.LEFT_STICK, LogitechAxis.Y, drivetrain::setLeftTarget);
		gamepad.map(LogitechControl.RIGHT_STICK, LogitechAxis.Y, drivetrain::setRightTarget);
		timedPrinter.start();
	}

	@Override
	public void autonomousSetup() {
	}

	@Override
	public void autonomousRoutine() throws InterruptedException {
		drivetrain.setLeftTarget(1);
		drivetrain.setRightTarget(1);
		waitFor(5, TimeUnit.SECONDS);
		drivetrain.setLeftTarget(0);
		drivetrain.setRightTarget(0);
	}

	@Override
	public void teleopSetup() {
	}

	@Override
	public void teleopRoutine() {
	}

	@Override
	public void disabledSetup() {
	}

	@Override
	public void disabledRoutine() {
	}

	@Override
	public void testRoutine() {
	}

	@Override
	public void testSetup() {
	}

}
