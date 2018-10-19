package dao;

public class XMLFactory extends DAOFactory {

	@Override
	public ClassDAO getClass1DAO() {
		return ClassXMLDAO.getInstance();
	}

}
