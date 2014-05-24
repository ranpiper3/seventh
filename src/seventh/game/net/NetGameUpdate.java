/*
 * see license.txt 
 */
package seventh.game.net;

import harenet.IOBuffer;
import harenet.messages.NetMessage;
import seventh.game.Game;
import seventh.network.messages.BufferIO;
import seventh.shared.BitArray;

/**
 * @author Tony
 *
 */
public class NetGameUpdate implements NetMessage {	
	public static final int ENTITIES_MASK = (1<<0);
	public static final int SOUND_MASK = (1<<1);
	public static final int DEAD_ENTS_MASK = (1<<2);
	public static final int SPEC_MASK = (1<<3);
	
	public NetEntity[] entities;
	public NetSound[] sounds;
	
	public int time;
	public int spectatingPlayerId = -1;
	

	private BitArray entityBitArray;
	public BitArray deadPersistantEntities;
	private int numberOfInts;
	
	private boolean hasDeadEntities;
	
	protected byte bits;
	
	/**
	 * 
	 */
	public NetGameUpdate() {
		entityBitArray = new BitArray(Game.MAX_ENTITIES - 1);		
		entities = new NetEntity[Game.MAX_ENTITIES];
		
		deadPersistantEntities = new BitArray(Game.MAX_PERSISTANT_ENTITIES - 1);
		hasDeadEntities = true;
		
		numberOfInts = entityBitArray.numberOfInts();
	}
	
	/* (non-Javadoc)
	 * @see seventh.network.messages.NetMessage#read(java.nio.ByteBuffer)
	 */
	@Override
	public void read(IOBuffer buffer) {
		bits = buffer.get();
		if( (bits & ENTITIES_MASK) != 0) {
			for(int i = 0; i < numberOfInts; i++) {
				entityBitArray.setDataElement(i, buffer.getInt());
			}
			
			for(int i = 0; i < entities.length; i++) {
				if(entityBitArray.getBit(i)) {
					entities[i] = BufferIO.readEntity(buffer);	
					entities[i].id = i;
				}
			}
		}
		
		if( (bits & SOUND_MASK) != 0) {
			short len = (short)buffer.getUnsignedByte();
			sounds = new NetSound[len];
			for(short i = 0; i < len; i++) {
				sounds[i] = new NetSound();
				sounds[i].read(buffer);
			}
		}
		
		if( (bits & DEAD_ENTS_MASK) != 0) {			
			hasDeadEntities = true;
			for(int i = 0; i < deadPersistantEntities.numberOfInts(); i++) {
				deadPersistantEntities.setDataElement(i, buffer.getInt());
			}
		}
		
		if( (bits & SPEC_MASK) != 0) {
			spectatingPlayerId = buffer.getUnsignedByte();
		}
		
		time = buffer.getInt();
		
		
	}
	
	/* (non-Javadoc)
	 * @see seventh.network.messages.NetMessage#write(java.nio.ByteBuffer)
	 */
	@Override
	public void write(IOBuffer buffer) {
		bits = 0;
		if(entities != null && entities.length > 0) {
			bits |= ENTITIES_MASK;
		}
		
		if(sounds != null && sounds.length > 0) {
			bits |= SOUND_MASK;
		}
		 
		if(hasDeadEntities) {
			bits |= DEAD_ENTS_MASK;
		}
		
		if(spectatingPlayerId > -1) {
			bits |= SPEC_MASK;
		}
	
		buffer.put(bits);
		
		if(entities != null && entities.length > 0) {
			entityBitArray.clear();
			
			for(int i = 0; i < entities.length; i++) {
				if(entities[i]!=null) {
					entityBitArray.setBit(i);
				}
			}
			
			int[] data = entityBitArray.getData();
			for(int i = 0; i < data.length; i++) {
				buffer.putInt(data[i]);
			}
			
			for(short i = 0; i < entities.length; i++) {
				if(entities[i]!=null) {
					entities[i].write(buffer);
				}
			}
		}
		
		if(sounds != null && sounds.length > 0) {
			int len = sounds.length;
			buffer.putUnsignedByte(sounds.length);
			for(byte i = 0; i < len; i++) {
				sounds[i].write(buffer);
			}
		}
		
		if(hasDeadEntities) {
			int[] data = deadPersistantEntities.getData();
			for(int i = 0; i < data.length; i++) {
				buffer.putInt(data[i]);
			}
		}
		
		if(spectatingPlayerId > -1) {
			buffer.putUnsignedByte(spectatingPlayerId);
		}
		
		buffer.putInt(this.time);
		

	}
}
