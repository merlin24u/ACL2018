package dao;

import model.Map;

public interface MapDAO {

	public Map load(String file) throws Exception;

	public void save(Map map);
}
