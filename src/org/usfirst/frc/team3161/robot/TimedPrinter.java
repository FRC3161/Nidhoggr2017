package org.usfirst.frc.team3161.robot;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimedPrinter extends RepeatingPooledSubsystem {

	private int count = 0;

	public TimedPrinter() {
		super(2, TimeUnit.SECONDS);
	}

	@Override
	public void defineResources() {
		// none!
	}

	@Override
	public void task() {
		SmartDashboard.putNumber("TimedPrinterCount", count);
		count++;
	}

}
