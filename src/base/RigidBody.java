package base;

import org.jblas.DoubleMatrix;

public class RigidBody {
	
	protected double x;
	protected double y;
	protected double z;
	protected double w;
	//All angles in radians
	//All rotation counter clockwise
	//Pitch is about y-z plane (x axis)
	//Yaw is about x-z plane (y axis)
	//Roll is about the x-y plane (z axis)
	protected double pitch;
	protected double yaw;
	protected double roll;
	
	//Position and rotation matrices are in projective coordinates;
	protected DoubleMatrix rotMatrix;
	protected DoubleMatrix posMatrix;
	
	public RigidBody(double x, double y, double z,double scale, double pitch, double yaw, double roll) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = scale;
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = roll;
		rotMatrix = new DoubleMatrix(new double[][] {
			new double[] {1.0             ,0.0             ,0.0             ,0.0             },
			new double[] {0.0             ,1.0             ,0.0             ,0.0             },
			new double[] {0.0             ,0.0             ,1.0             ,0.0             },
			new double[] {0.0             ,0.0             ,0.0             ,1.0             }
		});
		posMatrix = new DoubleMatrix(new double[][] {
			new double[] {0.0             },
			new double[] {0.0             },
			new double[] {0.0             },
			new double[] {0.0             }
		});
		
		updateRot();
		updatePos();
		normalizeProjectivePos();
	}
	
	protected void updateRot() {
		//Reset rotation matrix
		rotMatrix = new DoubleMatrix(new double[][] {
			new double[] {1.0             ,0.0             ,0.0             ,0.0             },
			new double[] {0.0             ,1.0             ,0.0             ,0.0             },
			new double[] {0.0             ,0.0             ,1.0             ,0.0             },
			new double[] {0.0             ,0.0             ,0.0             ,1.0             }
		});
		
		//Pitch rotation
		new DoubleMatrix(new double[][] {
			new double[] {1.0             ,0.0             ,0.0             ,0.0             },
			new double[] {0.0             ,Math.cos(pitch) ,-Math.sin(pitch),0.0             },
			new double[] {0.0             ,Math.sin(pitch) ,Math.cos(pitch) ,0.0             },
			new double[] {0.0             ,0.0             ,0.0             ,1.0             }
		}).mmuli(rotMatrix, rotMatrix);
		
		//Yaw rotation
		new DoubleMatrix(new double[][] {
			new double[] {Math.cos(yaw)   ,0.0             ,-Math.sin(yaw)  ,0.0             },
			new double[] {0.0             ,1.0             ,0.0             ,0.0             },
			new double[] {Math.sin(yaw)   ,0.0             ,Math.cos(yaw)   ,0.0             },
			new double[] {0.0             ,0.0             ,0.0             ,1.0             }
		}).mmuli(rotMatrix, rotMatrix);
		
		//Roll rotation
		new DoubleMatrix(new double[][] {
			new double[] {Math.cos(roll)  ,-Math.sin(roll) ,0.0             ,0.0             },
			new double[] {Math.sin(roll)  ,Math.cos(roll)  ,0.0             ,0.0             },
			new double[] {0.0             ,0.0             ,1.0             ,0.0             },
			new double[] {0.0             ,0.0             ,0.0             ,1.0             }
		}).mmuli(rotMatrix, rotMatrix);
		
	}
	
	protected void updatePos() {
		posMatrix = new DoubleMatrix(new double[][] {
			new double[] {x},
			new double[] {y},
			new double[] {z},
			new double[] {w}
		});
	}
	
	protected void normalizeProjectivePos() {
		posMatrix.divi(w);
		updatePosCoords();
	}
	
	protected void updatePosCoords() {
		x = posMatrix.get(0, 0);
		y = posMatrix.get(1, 0);
		z = posMatrix.get(2, 0);
		w = posMatrix.get(3, 0);
	}
	
	public DoubleMatrix getPosMatrix() {
		return posMatrix;
	}
	
	public void setPosMatrix(DoubleMatrix newPos) {
		posMatrix = newPos;
		updatePosCoords();
	}
	
	public DoubleMatrix getRotMatrix() {
		return rotMatrix;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double newX) {
		x = newX;
		updatePos();
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double newY) {
		y = newY;
		updatePos();
	}
	
	public double getZ() {
		return z;
	}
	
	public void setZ(double newZ) {
		z = newZ;
		updatePos();
	}
	
	public double getPitch() {
		return pitch;
	}
	
	public void setPitch(double newPitch) {
		pitch = newPitch;
		updateRot();
	}
	
	public double getYaw() {
		return yaw;
	}
	
	public void setYaw(double newYaw) {
		yaw = newYaw;
		updateRot();
	}
	
	public double getRoll() {
		return roll;
	}
	
	public void setRoll(double newRoll) {
		roll = newRoll;
		updateRot();
	}
	
	public void add(RigidBody toAdd) {
		toAdd.getPosMatrix().addi(posMatrix,posMatrix);
		updatePosCoords();
		toAdd.getRotMatrix().mmuli(rotMatrix,rotMatrix);
		pitch += toAdd.getPitch();
		pitch %= 2.0*Math.PI;
		yaw += toAdd.getYaw();
		yaw %= 2.0*Math.PI;
		roll += toAdd.getRoll();
		roll %= 2.0*Math.PI;
	}
	
}
