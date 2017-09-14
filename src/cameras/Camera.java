package cameras;

import org.jblas.DoubleMatrix;

import base.RigidBody;

public class Camera extends RigidBody {
	
	protected DoubleMatrix inverseRot;
	protected DoubleMatrix inversePos;
	
	public Camera(double x, double y, double z, double scale, double pitch, double yaw, double roll) {
		super(x, y, z, scale, pitch, yaw, roll);
		updateReverseRot();
		updateInversePos();
	}
	
	@Override 
	protected void updatePos(){
		super.updatePos();
		updateInversePos();
	}
	
	protected void updateInversePos() {
		//Set inversePos to posMatrix values
		inversePos = posMatrix.dup();
		//Make it so x, y, and z are inverted and w is 0
		new DoubleMatrix(new double[][] {
			new double[] {-1.0            ,0.0             ,0.0             ,0.0             },
			new double[] {0.0             ,-1.0            ,0.0             ,0.0             },
			new double[] {0.0             ,0.0             ,-1.0            ,0.0             },
			new double[] {0.0             ,0.0             ,0.0             ,0.0             }
		}).mmuli(inversePos, inversePos);
	}
	
	@Override
	protected void updateRot() {
		super.updateRot();
		updateReverseRot();
	}
	
	protected void updateReverseRot() {
		//Reset inverseRot
		inverseRot = new DoubleMatrix(new double[][] {
				new double[] {1.0             ,0.0             ,0.0             ,0.0             },
				new double[] {0.0             ,1.0             ,0.0             ,0.0             },
				new double[] {0.0             ,0.0             ,1.0             ,0.0             },
				new double[] {0.0             ,0.0             ,0.0             ,1.0             }
			});
				
		//Pitch rotation
		new DoubleMatrix(new double[][] {
			new double[] {1.0              ,0.0              ,0.0              ,0.0              },
			new double[] {0.0              ,Math.cos(-pitch) ,-Math.sin(-pitch),0.0              },
			new double[] {0.0              ,Math.sin(-pitch) ,Math.cos(-pitch) ,0.0              },
			new double[] {0.0              ,0.0              ,0.0              ,1.0              }
		}).mmuli(inverseRot, inverseRot);
		
		//Yaw rotation
		new DoubleMatrix(new double[][] {
			new double[] {Math.cos(-yaw)   ,0.0              ,-Math.sin(-yaw)  ,0.0              },
			new double[] {0.0              ,1.0              ,0.0              ,0.0              },
			new double[] {Math.sin(-yaw)   ,0.0              ,Math.cos(-yaw)   ,0.0              },
			new double[] {0.0              ,0.0              ,0.0              ,1.0              }
		}).mmuli(inverseRot, inverseRot);
		
		//Roll rotation
		new DoubleMatrix(new double[][] {
			new double[] {Math.cos(-roll)  ,-Math.sin(-roll) ,0.0              ,0.0              },
			new double[] {Math.sin(-roll)  ,Math.cos(-roll)  ,0.0              ,0.0              },
			new double[] {0.0              ,0.0              ,1.0              ,0.0              },
			new double[] {0.0              ,0.0              ,0.0              ,1.0              }
		}).mmuli(inverseRot, inverseRot);
		
	}
	
	protected DoubleMatrix inCameraCoord(DoubleMatrix nontransformed) {
		//Duplicate matrix so that source object is not messed up
		DoubleMatrix transformed = nontransformed.dup();
		//Move so camera is origin
		transformed.addiColumnVector(inversePos);
		//Rotate so into camera coordinate system
		inverseRot.mmuli(transformed,transformed);
		return transformed;
	}
	
	

}
