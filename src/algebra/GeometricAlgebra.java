package algebra;

import org.jblas.DoubleMatrix;

public class GeometricAlgebra {
	public static int dimension;
	
	public GeometricAlgebra (int dimension) throws Exception {
		if(dimension < 2)
			throw new Exception("Unsuported dimension");
		this.dimension = dimension;
	}
	
	public static Multivector newMultivector(DoubleMatrix[] components) throws Exception {
		Multivector out = new Multivector(dimension);
		out.components = components;
		return out;
	}
	
	public static Multivector newMultivector() throws Exception {
		Multivector out = new Multivector(dimension);
		return out;
	}
	
	public static Multivector add(Multivector a, Multivector b) throws Exception {
		DoubleMatrix vector = a.vectorized();
		b.vectorized().addi(vector, vector);
		Multivector out = new Multivector(b.dim,vector);
		return out;
	}
	
	public static Multivector geometricProduct(Multivector a,Multivector b) throws Exception {
		DoubleMatrix vector = b.vectorized();
		a.geometricProduct().mmuli(b.vectorized(), vector);
		Multivector out = new Multivector(b.dim,vector);
		return out;
	}
	
	//Returns the factor by which the blades of the grade are multiplied to get their reversions
	public static double getReversionFactor(int grade) throws Exception {
		//This will throw an exception if this is fed negative grades as they don't exist
		if(grade < 0)
			throw new Exception("Don't feed garbage into this");
		//Truncation is intentional and needed
		//This checks if floor(grade/2.0) is even
		if(grade/2 == (grade/4)*2.0) 
			return 1.0;
		else
			return -1.0;
	}

}