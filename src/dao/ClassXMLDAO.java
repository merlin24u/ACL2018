package dao;

public class ClassXMLDAO implements ClassDAO {

	private static ClassXMLDAO instance = null;

	private ClassXMLDAO() {
	}

	@Override
	public Object load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Object obj) {
		// TODO Auto-generated method stub

	}

	public static ClassXMLDAO getInstance() {
		if (instance == null) {
			instance = new ClassXMLDAO();
		}
		return instance;
	}

}
