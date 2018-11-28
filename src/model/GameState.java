package model;

public class GameState {
    /**
     * EnumÃ©ration des Ã©tats possibles d'une partie
     */
	public enum State {
    	STARTING,
        RUNNING,
        WON,
        GAMEOVER;
    }
	/* Etat actuel de la partie */
    private State state;

    /**
     * Constructeur
     */
    public GameState(){
        this.state = State.STARTING;
    }
    
    /**
     * EqualsTo
     * MÃ©thode permettant de savoir si l'Ã©tat fourni en paramÃ¨tre est Ã©gal Ã  l'Ã©tat actuel
     * @param state l'Ã©tat comparÃ©
     * @return boolean true si l'Ã©tat actuel est Ã©gal Ã  l'Ã©tat en paramÃ¨tre, false sinon
     */
    public boolean equalsTo(State state) {
    	return this.state == state;
    }

    /**
     * SetState
     * Setter de state
     * @param state
     */
    public void setState(State state){
        this.state = state;
    }
    
    /**
     * GetState
     * Getter de state
     * @return state
     */
    public State getState() {
        return state;
    }
}