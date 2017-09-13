package objects;

import org.jblas.DoubleMatrix;

import base.ConnectedLineBody;

public class WireframeCube extends ConnectedLineBody {

	public WireframeCube(double x, double y, double z, double scale, double pitch, double yaw, double roll) {
		super(x, y, z, scale, pitch, yaw, roll, 8, new int[][]{
			new int[] {0,1},
			new int[] {0,2},
			new int[] {0,3},
			new int[] {1,4},
			new int[] {1,5},
			new int[] {2,4},
			new int[] {2,6},
			new int[] {3,5},
			new int[] {3,6},
			new int[] {7,6},
			new int[] {7,5},
			new int[] {7,4}
		});
		setDef(new DoubleMatrix(new double[][] {
			new double[] {1.0             ,-1.0            ,1.0             ,1.0             ,-1.0            ,-1.0            ,1.0             ,-1.0            },
			new double[] {1.0             ,1.0             ,-1.0            ,1.0             ,-1.0            ,1.0             ,-1.0            ,-1.0            },
			new double[] {1.0             ,1.0             ,1.0             ,-1.0            ,1.0             ,-1.0            ,-1.0            ,-1.0            },
			new double[] {0.0             ,0.0             ,0.0             ,0.0             ,0.0             ,0.0             ,0.0             ,0.0             }
		}));
	}

}
