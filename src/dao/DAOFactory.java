package dao;

public abstract class DAOFactory {

	public final static int XML = 1;

	public abstract MapDAO getMapDAO();

	public static DAOFactory getAbstractDAOFactory(int type) throws Exception {
		switch (type) {
		case XML:
			return XmlFactory.getInstance();
		default:
			throw new Exception();
		}

	}
}
