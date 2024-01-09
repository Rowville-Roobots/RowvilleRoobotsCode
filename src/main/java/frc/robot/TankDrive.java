package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

// Main class for controlling the robot, all robot functions should use this.
public class TankDrive {

    private MotorController[] LeftMotors;
    private MotorController[] RightMotors;
    private MotorController[] ArmMotors;

    public Pnumatics Pnumatics;
    public Joystick Controller;

    public Hardware Hardware;

    // Hardware should be initialized before this is called. This is called in Hardware.java.
    public void Init(Hardware hardware) {
        Hardware = hardware;
        LeftMotors = new MotorController[] {
            hardware.LeftMotor1,
            hardware.LeftMotor2
        };
        RightMotors = new MotorController[] {
            hardware.RightMotor1,
            hardware.RightMotor2
        };
        ArmMotors = new MotorController[] {
            hardware.ArmInOut
        };
        Pnumatics = new Pnumatics(hardware.Solenoid);
        Controller = hardware.LeftJoystick;
    }

    public void Update(HardwareStates states) {

        // Drive
        SetLeftDrive(states.LeftDriveMotors);
        SetRightDrive(states.RightDriveMotors);

        // Winch
        SetWinchPower(states.Arm);

        // Arm
        SetArmPower(states.ArmInOutMotors);

        Hardware.Intake.set(states.Intake);

        //Claw Piston
        //SetClawPiston(states.ClawPiston);
    }

    public void SetLeftDrive(double s) {
        for (MotorController c : LeftMotors) {
            c.set(s);
        }
    }

    public void SetRightDrive(double s) {
        for (MotorController c : RightMotors) {
            c.set(s);
        }
    }

    public void SetWinchPower(double s) {
        Hardware.WinchLeft.set(s);
        Hardware.WinchRight.set(s);
    }

    public void SetArmPower(double s) {
        for (MotorController c : ArmMotors) {
            c.set(s);
        }
    }

    // Closed is True
    // Open is False
    public void SetClawPiston(boolean s) {
        Pnumatics.SetState(s);
    }

    public void Stop() {
        Hardware.Reset();
    }
    
}
