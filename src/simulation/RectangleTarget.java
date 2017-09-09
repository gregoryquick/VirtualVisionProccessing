package simulation;

import org.jblas.DoubleMatrix;

import control.Return;

public class RectangleTarget extends RigidBody {
	
	double length;
	double heigth;
	
	public RectangleTarget(RigidBody initializer, Camera orgin, double length, double heigth) {
		super(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, orgin);
		double pitch = 0.0;
		double roll = 0.0;
		double yaw = 0.0;
		double x = 0.0;
		double y = 0.0;
		double z = 0.0;
		initializer.getPitch(pitch);
		initializer.getRoll(roll);
		initializer.getYaw(yaw);
		initializer.getX(x);
		initializer.getY(y);
		initializer.getZ(z);
		setPitch(pitch);
		setRoll(roll);
		setYaw(yaw);
		setX(x);
		setY(y);
		setZ(z);
		this.heigth = heigth;
		this.length = length;
	}
	
	@Override
	public Return update() {
		/**
		 * Point matrix for target
		 * [ a0x , a1x , a2x , a3x ]
		 * [ a0y , a1y , a2y , a3y ]
		 * [ a0z , a1z , a2z , a3z ]
		 * [ a0w , a1w , a2w , a3w ]
		 * a0 thru a3 are the points at the corners of the
		 * target starting from the top left going counter-
		 * clockwise. The x,y,z,w coordinates are the projective
		 * coordinates of said points;
		 */
//		DoubleMatrix unrotated = new DoubleMatrix(
//				{
//				{x - (length/2.0),x - (length/2.0),x + (length/2.0),x + (length/2.0)},
//				{y + (heigth/2.0),y - (heigth/2.0),y - (heigth/2.0),y - (heigth/2.0)},
//				{z               ,z               ,z               ,z               },
//				{1               ,1               ,1               ,1               } 
//				}
//				);
//		
		DoubleMatrix test;
		return Return.fine;
	}
	

}
