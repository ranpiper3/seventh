/*
 * The Seventh
 * see license.txt 
 */
package seventh.network.messages;

import harenet.IOBuffer;
import seventh.game.PlayerClass;

/**
 * @author Tony
 *
 */
public class PlayerSwitchPlayerClassMessage extends AbstractNetMessage {
    public int playerId;
    public PlayerClass playerClass;
    
    /**
     * 
     */
    public PlayerSwitchPlayerClassMessage() {
        super(BufferIO.PLAYER_CLASS_CHANGE);
    }
    
    @Override
    public void read(IOBuffer buffer) {    
        super.read(buffer);
        playerId = BufferIO.readPlayerId(buffer);
        playerClass = BufferIO.readPlayerClassType(buffer);
    }
    
    @Override
    public void write(IOBuffer buffer) {    
        super.write(buffer);
        BufferIO.writePlayerId(buffer, playerId);
        BufferIO.writePlayerClassType(buffer, playerClass);
    }
}
