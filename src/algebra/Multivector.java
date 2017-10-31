package algebra;

import org.jblas.DoubleMatrix;

public class Multivector {
	
	//Assumed to use standard orthonormal basis and for 3D bivector basis
	//is {e1e2,e1e3,e2e3} not {e1e2,e2e3,e3e1} as is sometimes used
	public int dim;
	public DoubleMatrix[] components;
	boolean isPure = false;
	public int embeding;
	
	public Multivector(int dimension) throws Exception {
		//Set dimension
		this.dim = dimension;
		//Set make double array for components
		this.components = new DoubleMatrix[dim+1];
		//For each blade set array for components to appropriate length
		for(int i = 0; i <= dim;i++) {
			components[i] = DoubleMatrix.zeros(MathUtil.choose(dim,i));
		}
		//Get the dimension of the standard vector space the algebra can be embeded in
		embeding = (int) Math.pow(2.0, dim);
	}
	
	public Multivector(int dimension, DoubleMatrix vectorized) throws Exception {
		//Set dimension
		this.dim = dimension;
		//Set make double array for components
		this.components = new DoubleMatrix[dim+1];
		//For each blade set array for components to appropriate length
		for(int i = 0; i <= dim;i++) {
			components[i] = DoubleMatrix.zeros(MathUtil.choose(dim,i));
		}
		int index = 0;
		int currentGrade = 0;
		int gradeSize = MathUtil.choose(dim, currentGrade);
		do {
			for(int i = 0; i < gradeSize;i++) {
				components[currentGrade].put(i,vectorized.get(index));
				index++;
			}
			currentGrade++;
			gradeSize = MathUtil.choose(dim, currentGrade);
		}while(currentGrade < dim);
	}
	
	public PureMultivector nBlade(int grade) throws Exception {
		PureMultivector out = new PureMultivector(dim,grade);
		out.components[grade] = components[grade];
		return out;
	}
	
	public DoubleMatrix vectorized() throws Exception {
		DoubleMatrix out = DoubleMatrix.zeros(embeding);
		int index = 0;
		int currentGrade = 0;
		int gradeSize = MathUtil.choose(dim, currentGrade);
		do {
			for(int i = 0; i < gradeSize;i++) {
				out.put(index, components[currentGrade].get(i));
				index++;
			}
			currentGrade++;
			gradeSize = MathUtil.choose(dim, currentGrade);
		}while(currentGrade < dim);
		return out;
	}
	
	//This return the matrix multiplied on the left to execute the geometric product
	//Not yet completed
	public DoubleMatrix geometricProduct() {
		DoubleMatrix out = new DoubleMatrix(embeding, embeding);
		return null;
	}
}
