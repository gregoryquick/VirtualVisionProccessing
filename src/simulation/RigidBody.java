package simulation;

import control.Return;

public class RigidBody {
	
//	All angles are in radians
	private double pitch;
	private double roll;
	private double yaw;
	private double x;
	private double y;
	private double z;
	
	private int index;
	
	private Camera orgin;
	
	public RigidBody(double pitch, double roll, double yaw, double x,double y, double z, Camera from) {
		this.setPitch(pitch);
		this.setRoll(roll);
		this.setYaw(yaw);
		this.setX(x);
		this.setY(y);
		this.z = z;
		this.orgin = from;
	}
	
	public Return update() {
		return Return.fine;
	}

	public Return getPitch(double returnVar) {
		returnVar = pitch;
		return update();
	}

	public Return setPitch(double pitch) {
		this.pitch = pitch;
		return update();
	}

	public Return getRoll(double returnVar) {
		returnVar = roll;
		return update();
	}

	public Return setRoll(double roll) {
		this.roll = roll;
		return update();
	}

	public Return getYaw(double returnVar) {
		returnVar = yaw;
		return update();
	}

	public Return setYaw(double yaw) {
		this.yaw = yaw;
		return update();
	}

	public Return getX(double returnVar) {
		returnVar = x;
		return update();
	}

	public Return setX(double x) {
		this.x = x;
		return update();
	}

	public Return getY(double returnVar) {
		returnVar = y;
		return update();
	}

	public Return setY(double y) {
		this.y = y;
		return update();
	}
	
	public Return getZ(double returnVar) {
		returnVar = z;
		return update();
	}

	public Return setZ(double z) {
		this.z = z;
		return update();
	}

	public Return getCamera(Camera returnVar) {
		returnVar = orgin;
		return Return.fine;
	}

	public Return setCamera(Camera camera) {
		orgin.RemoveRigidBody(index);
		orgin = camera;
		orgin.AddRigidBody(this);
		return Return.fine;
	}
	
	public Return setIndex(int index) {
//		Should only be touch by the camera owning this
		this.index = index;
		return Return.fine;
	}
	
}
