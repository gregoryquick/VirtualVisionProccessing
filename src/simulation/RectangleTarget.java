package simulation;

import org.jblas.DoubleMatrix;

import control.Return;

public class RectangleTarget extends RigidBody {
	
	protected double length;
	protected double heigth;
	protected DoubleMatrix corners;
	
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
		this.pitch = pitch;
		this.roll = roll;
		this.yaw = yaw;
		this.x = x;
		this.y = y;
		this.z = z;
		this.heigth = heigth;
		this.length = length;
		update();
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
		//Initialize the points about 0 vector
		DoubleMatrix points = new DoubleMatrix(new double[][]{
			new double[]{0.0 - (length/2.0),0.0 - (length/2.0),0.0 + (length/2.0),0.0 + (length/2.0)},
			new double[]{0.0 + (heigth/2.0),0.0 - (heigth/2.0),0.0 - (heigth/2.0),0.0 - (heigth/2.0)},
			new double[]{0.0               ,0.0               ,0.0               ,0.0               },
			new double[]{1.0               ,1.0               ,1.0               ,1.0               } 
			});
		
		//Created rotation matrix, starting with roll in it
		DoubleMatrix rot = new DoubleMatrix(new double[][]{
			new double[]{Math.cos(roll)    ,-Math.sin(roll)   ,0.0               ,0.0               },
			new double[]{Math.sin(roll)    ,Math.cos(roll)    ,0.0               ,0.0               },
			new double[]{0.0               ,0.0               ,1.0               ,0.0               },
			new double[]{0.0               ,0.0               ,0.0               ,1.0               } 
			});
				
		//Rotate by yaw matrix
		rot.mmuli(new DoubleMatrix(new double[][]{
			new double[]{Math.cos(yaw)     ,0.0               ,Math.sin(yaw)     ,0.0               },
			new double[]{0.0               ,1.0               ,0.0               ,0.0               },
			new double[]{-Math.sin(yaw)    ,0.0               ,Math.cos(yaw)     ,0.0               },
			new double[]{0.0               ,0.0               ,0.0               ,1.0               } 
			}));

		//Rotate by pitch matrix
		rot.mmuli(new DoubleMatrix(new double[][]{
			new double[]{1.0               ,0.0               ,0.0               ,0.0               },
			new double[]{0.0               ,Math.cos(pitch)   ,-Math.sin(pitch)  ,0.0               },
			new double[]{0.0               ,Math.sin(pitch)   ,Math.cos(pitch)   ,0.0               },
			new double[]{0.0               ,0.0               ,0.0               ,1.0               } 
			}));
		
		//Apply rotation to points
		rot.mmuli(points, points);

				
		//Move the center to correct position
		points.addi(new DoubleMatrix(new double[][]{
			new double[]{x                 ,x                 ,x                 ,x                 },
			new double[]{y                 ,y                 ,y                 ,y                 },
			new double[]{z                 ,z                 ,z                 ,z                 },
			new double[]{0.0               ,0.0               ,0.0               ,0.0               } 
			}));
		
		corners = points;
		
		return Return.fine;
	}
	

}
