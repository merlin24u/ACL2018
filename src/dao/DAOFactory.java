package dao;

public abstract class DAOFactory {

	public final static int XML = 1;

	public abstract ClassDAO getClass1DAO();

	public static DAOFactory getAbstractDAOFactory(int type) throws Exception {
		switch (type) {
		case XML:
			return new XMLFactory();
		default:
			throw new Exception();
		}

	}
}
