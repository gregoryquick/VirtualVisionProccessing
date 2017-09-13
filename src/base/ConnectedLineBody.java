package base;

public class ConnectedLineBody extends PointBody {
	
	//Point connections is a list of the unordered pairs of the
	//column indices of points connected by lines
	int[][] pointConnections;

	public ConnectedLineBody(double x, double y, double z, double scale, double pitch, double yaw, double roll,
			int pointNum, int[][] pointConnections) {
		super(x, y, z, scale, pitch, yaw, roll, pointNum);
		this.pointConnections = pointConnections;
	}

}
