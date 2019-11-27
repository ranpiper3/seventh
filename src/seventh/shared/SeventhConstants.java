/*
 * see license.txt 
 */
package seventh.shared;

/**
 * Shared constants between the server and client
 * 
 * @author Tony
 *
 */
public interface SeventhConstants {

    /**
     * Default port
     */
    public static final int DEFAULT_PORT = 9844;
    
    public static final int MAX_PRIMARY_WEAPONS = 2;
    public static final int MAX_TIMERS = 32;    
    public static final int MAX_ENTITIES = 256;
    public static final int MAX_PLAYERS = 24;
    public static final int MAX_PERSISTANT_ENTITIES = 64;
    
    public static final int MAX_SOUNDS = 32;

    
    public static final int INVALID_PLAYER_ID = MAX_PLAYERS;
    
    public static final int SPAWN_INVINCEABLILITY_TIME = 2_000;
    
    
    public static final int PLAYER_HEARING_RADIUS = 1400;
    public static final int PLAYER_WIDTH = 24;//16;
    public static final int PLAYER_HEIGHT = 24;
    
    public static final int PLAYER_SPEED = 200; // 140
    public static final int PLAYER_MIN_SPEED = 60; // 40
    public static final int RUN_DELAY_TIME = 300;
    public static final int SPRINT_DELAY_TIME = 200;
    
    public static final float WALK_SPEED_FACTOR = 0.584f;
    public static final float SPRINT_SPEED_FACTOR = 1.60f; // 1.95f
    
    public static final int ENTERING_VEHICLE_TIME = 1000;
    public static final int EXITING_VEHICLE_TIME = 700;
    
    public static final int RECOVERY_TIME = 2000;
    
    public static final byte MAX_STAMINA = 100;
    public static final float STAMINA_DECAY_RATE = 2; // 4
    public static final float STAMINA_RECOVER_RATE = 1.5f;
    
    public static final int FLAG_WIDTH = 20;
    public static final int FLAG_HEIGHT = 20;
}
