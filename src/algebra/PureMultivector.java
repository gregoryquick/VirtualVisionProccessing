package algebra;

import org.jblas.DoubleMatrix;

public class PureMultivector extends Multivector {

	//Assumed to use standard orthonormal basis and for 3D bivector basis
	//is {e1e2,e1e3,e2e3} not {e1e2,e2e3,e3e1} as is sometimes used
	public int grade;
	public int subDim;
	
	public PureMultivector(int dimension, int grade) throws Exception {
		super(dimension);
		if(grade < 0 || grade > dimension)
			throw new Exception("Invalid grade");
		this.grade = grade;
		this.subDim = MathUtil.choose(dim, grade);
	}
	
	@Override
	public PureMultivector nBlade(int grade) throws Exception {
		if(this.grade != grade)
			throw new Exception("Invalid grade");
		return (PureMultivector) this.clone();
	}

}
