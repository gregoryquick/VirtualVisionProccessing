package control;

import org.jblas.DoubleMatrix;

public class Main {
	
	static double pitch = Math.E*Math.PI;
	static double yaw = Math.PI*Math.PI/2.0;
	static double roll = Math.PI/4.0;

	public static void main(String[] args) {
		
		//Rotate by roll matrix
		DoubleMatrix rot = new DoubleMatrix(new double[][]{
			new double[]{Math.cos(roll)    ,-Math.sin(roll)   ,0.0               ,0.0               },
			new double[]{Math.sin(roll)    ,Math.cos(roll)    ,0.0               ,0.0               },
			new double[]{0.0               ,0.0               ,1.0               ,0.0               },
			new double[]{0.0               ,0.0               ,0.0               ,1.0               } 
			});
		System.out.println("\nRoll Applied:");
//		rot.print();
		matrixPrint(rot);
		
		//Rotate by yaw matrix
		rot.mmuli(new DoubleMatrix(new double[][]{
			new double[]{Math.cos(yaw)     ,0.0               ,Math.sin(yaw)     ,0.0               },
			new double[]{0.0               ,1.0               ,0.0               ,0.0               },
			new double[]{-Math.sin(yaw)    ,0.0               ,Math.cos(yaw)     ,0.0               },
			new double[]{0.0               ,0.0               ,0.0               ,1.0               } 
			}));
		System.out.println("\nYaw Applied:");
//		rot.print();
		matrixPrint(rot);
		
		//Rotate by pitch matrix
		rot.mmuli(new DoubleMatrix(new double[][]{
			new double[]{1.0               ,0.0               ,0.0               ,0.0               },
			new double[]{0.0               ,Math.cos(pitch)   ,-Math.sin(pitch)  ,0.0               },
			new double[]{0.0               ,Math.sin(pitch)   ,Math.cos(pitch)   ,0.0               },
			new double[]{0.0               ,0.0               ,0.0               ,1.0               } 
			}));
		System.out.println("\nPitch Applied:");
//		rot.print();
		matrixPrint(rot);
		
		//Initilizing points
//		DoubleMatrix points = new DoubleMatrix(new double[][]{
//			new double[]{-1.0              ,-1.0              ,1.0               ,1.0               },
//			new double[]{-1.0              ,1.0               ,-1.0              ,1.0               },
//			new double[]{5.0               ,5.0               ,5.0               ,5.0               },
//			new double[]{1.0               ,1.0               ,1.0               ,1.0               } 
//			});
		DoubleMatrix points = new DoubleMatrix(new double[][]{
			new double[]{1.0 ,1.0 ,1.0 ,1.0 ,-1.0,-1.0,-1.0,-1.0},
			new double[]{1.0 ,1.0 ,-1.0,-1.0,1.0 ,1.0 ,-1.0,-1.0},
			new double[]{1.0 ,-1.0,1.0 ,-1.0,1.0 ,-1.0,1.0 ,-1.0},
			new double[]{1.0 ,1.0 ,1.0 ,1.0 ,1.0 ,1.0 ,1.0 ,1.0 } 
			});
		System.out.println("\nInitilized:");
//		points.print();
		matrixPrint(points);
		
		int rowLength = points.getRows();
		int columnLength = points.getColumns();
		double[] checksum = new double[columnLength];
		for(double a : checksum)
			a = 0.0;
		for(int i = 0; i < columnLength;i++) {
			for(int j = 0; j < rowLength;j++) {
				double temp = points.get(j, i);
				checksum[i] += temp*temp;
			}
		}
		
		//Rotating points
		rot.mmuli(points, points);
//		points.print();
		System.out.println("\nRotated:");
		matrixPrint(points);
		
		//Checksum completion as magnitude should not change
		double[] checksum2 = new double[columnLength];
		
		for(int i = 0; i < columnLength;i++) {
			for(int j = 0; j < rowLength;j++) {
				double temp = points.get(j, i);
				temp *= temp;
				checksum2[i] += temp;
			}
			System.out.print('\n');
			double diff = checksum2[i] - checksum[i];
			System.out.print(Math.abs(diff) < 0.000001);
			System.out.print("  :  " + checksum[i] + "  :  ");	
			System.out.print(checksum2[i] + "  :  ");
			System.out.print(diff + "  :  ");
			System.out.println(i);
		}
		
	}
	
	private static void matrixPrint(DoubleMatrix toPrint) {
		int rowLength = toPrint.getRows();
		int columnLength = toPrint.getColumns();
		for(int i = 0; i < rowLength;i++) {
			System.out.print("{ ");
			for(int j = 0; j < columnLength;j++) {
				if(j != 0) {
					System.out.print(" , ");
				}
				System.out.print(toPrint.get(i, j));
			}
			System.out.println(" }");
		}
	}

}
