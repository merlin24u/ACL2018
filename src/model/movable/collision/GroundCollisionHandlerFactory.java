package model.movable.collision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import model.Map;


public class GroundCollisionHandlerFactory {
		private ArrayList<GroundCollisionHandler> groundCollisionHandlers;
		private static GroundCollisionHandlerFactory instance= null;

		public GroundCollisionHandlerFactory() {
			groundCollisionHandlers = new ArrayList<GroundCollisionHandler>();
		}
		
		public GroundCollisionHandler getGroundCollisionHandler(Map map, ECollisionType[] collisions) {
			HashSet<ECollisionType> collisionsSet = new HashSet<ECollisionType>(Arrays.asList(collisions));
			for(GroundCollisionHandler gchf: groundCollisionHandlers) {
				if(gchf.getMap() == map) {
				    HashSet<ECollisionType> collisionsSet2 = new HashSet<ECollisionType>(Arrays.asList(gchf.getCollisions()));
				    if(collisionsSet.equals(collisionsSet2)) {
				    	return gchf;
				    }
				}
			}
			GroundCollisionHandler gchf = new GroundCollisionHandler(map, collisions);
			groundCollisionHandlers.add(gchf);
			return gchf;
		}
		
		public static GroundCollisionHandlerFactory getInstance() {
			if(instance == null)
				instance = new GroundCollisionHandlerFactory();
			return instance;
		}
		
}
