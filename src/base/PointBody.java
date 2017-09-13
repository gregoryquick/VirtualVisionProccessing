package base;

import org.jblas.DoubleMatrix;

public class PointBody extends RigidBody {
	//transMatrix stores the points of the object at the proper location in projective 3-space;
	protected DoubleMatrix transMatrix;
	//pointMatrix stores the points of the object with the center of rotation at the origin
	/**Note:
	 * The points in the point matrix
	 * SHOULD NOT
	 * have any value EXCEPT 0.0
	 * for the w term or scale will be messed up.
	 */
	protected DoubleMatrix pointMatrix;
	protected int pointLength;

	public PointBody(double x, double y, double z, double scale, double pitch, double yaw, double roll,int pointNum) {
		super(x, y, z, scale, pitch, yaw, roll);
		pointMatrix = new DoubleMatrix(4,pointNum);
		pointLength = pointNum;
	}
	
	protected void update() {
		transMatrix = pointMatrix;
		rotMatrix.mmuli(transMatrix, transMatrix);
		transMatrix.addiColumnVector(posMatrix);
	}
	
	public DoubleMatrix getDef() {
		return pointMatrix;
	}
	
	protected void setDef(DoubleMatrix newPoints) {
		pointMatrix = newPoints;
		update();
	}
	
	public DoubleMatrix getPoints() {
		return transMatrix;
	}

}
