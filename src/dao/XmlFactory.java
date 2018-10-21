package dao;

public class XmlFactory extends DAOFactory {

	private static XmlFactory instance = null;

	private XmlFactory() {

	}

	@Override
	public MapDAO getClassDAO() {
		return MapXmlDAO.getInstance();
	}

	public static XmlFactory getInstance() {
		if (instance == null)
			instance = new XmlFactory();
		return instance;
	}

}
