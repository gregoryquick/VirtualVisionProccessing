package simulation;

import java.util.ArrayList;

import control.Return;

public class Camera {
	
	private ArrayList<RigidBody> objects = new ArrayList<RigidBody>();
	private double pitch;
	private double roll;
	private double yaw;
	private double focalLength;
	
	public Camera(double pitch, double roll, double yaw, double focalLength) {
		this.pitch = pitch;
		this.roll = roll;
		this.yaw = yaw;
		this.focalLength = focalLength;
	}
	
	public  Return AddRigidBody(RigidBody toAdd) {
		int index = objects.size();
		toAdd.setIndex(index);
		objects.add(toAdd);
		return Return.fine;
	}
	
	public Return RemoveRigidBody(int index) {
		objects.remove(index);
		int length = objects.size();
		for(int i = 0; i < length; i++) {
			RigidBody toNotify = objects.get(i);
			toNotify.setIndex(i);
		}
		return Return.fine;
	}
	
	

}
