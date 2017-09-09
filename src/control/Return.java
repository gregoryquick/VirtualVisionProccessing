package control;

public enum Return {
	fine, fatalerror, error;
	
	public static boolean isFine(Return status) {
		if(status.equals(Return.fine)) {
			return true;
		}
		System.out.println(status);
		return false;
	}
	
}
