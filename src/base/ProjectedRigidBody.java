package base;

import org.jblas.DoubleMatrix;

public class ProjectedRigidBody {
	private double x;
	private double y;
	private double w;
	//Counterclockwise rotation in radians
	private double rot;
	
	protected DoubleMatrix rotMatrix;
	protected DoubleMatrix posMatrix;
	
	public ProjectedRigidBody(double x, double y, double scale, double rot) {
		this.x = x;
		this.y = y;
		this.w = scale;
		this.setRot(rot);
		rotMatrix = new DoubleMatrix(new double[][] {
			new double[] {1.0             ,0.0             ,0.0             },
			new double[] {0.0             ,1.0             ,0.0             },
			new double[] {0.0             ,0.0             ,1.0             }
		});
		posMatrix = new DoubleMatrix(new double[][] {
			new double[] {0.0             },
			new double[] {0.0             },
			new double[] {0.0             }
		});
		updateRot();
		updatePos();
		normalizeProjectivePos();
	}
	
	protected void updateRot() {
		rotMatrix = new DoubleMatrix(new double[][] {
			new double[] {1.0             ,0.0             ,0.0             },
			new double[] {0.0             ,1.0             ,0.0             },
			new double[] {0.0             ,0.0             ,1.0             }
		});
		new DoubleMatrix(new double[][] {
			new double[] {Math.cos(rot)   ,-Math.sin(rot)  ,0.0             },
			new double[] {Math.sin(rot)   ,Math.cos(rot)   ,0.0             },
			new double[] {0.0             ,0.0             ,1.0             }
		}).mmuli(rotMatrix, rotMatrix);
	}
	
	protected void updatePos() {
		posMatrix = new DoubleMatrix(new double[][] {
			new double[] {x},
			new double[] {y},
			new double[] {w}
		});
	}
	
	protected void normalizeProjectivePos() {
		posMatrix.muli(1.0/w);
	}

	public double getRot() {
		return rot;
	}

	public void setRot(double rot) {
		this.rot = rot;
		updateRot();
	}
	
	

}
