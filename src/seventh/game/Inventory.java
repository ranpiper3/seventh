/*
 * see license.txt 
 */
package seventh.game;

import java.util.ArrayList;
import java.util.List;

import seventh.game.entities.Entity.Type;
import seventh.game.entities.Flag;
import seventh.game.weapons.GrenadeBelt;
import seventh.game.weapons.Weapon;

/**
 * A {@link Player}s current inventory of {@link Weapon}s.
 * 
 * @author Tony
 *
 */
public class Inventory {
   
    private List<Weapon> weapons;
    private final int maxPrimaryWeapons;
    private int currentItem;
    private GrenadeBelt grenades;
    
    private Flag carriedFlag;
    
    public Inventory(int maxPrimaryWeapons) {        
        this.maxPrimaryWeapons = maxPrimaryWeapons;
        this.weapons = new ArrayList<Weapon>();
        
        this.currentItem = 0;        
    }
    
    /**
     * Pickup a flag
     * 
     * @param flag
     */
    public void pickupFlag(Flag flag) {
        this.carriedFlag = flag;
    }
    
    
    /**
     * Drops the flag if carrying one
     */
    public void dropFlag() {
        if(this.carriedFlag!=null) {
            Flag flag = this.carriedFlag;
            this.carriedFlag = null;
            flag.drop();
        }
    }
    
    public boolean isCarryingFlag() {
        return this.carriedFlag != null;
    }

    public boolean hasGrenades() {
        return grenades != null && grenades.isLoaded();
    }
    
    /**
     * @return the grenades
     */
    public GrenadeBelt getGrenades() {
        return grenades;
    }
    
    /**
     * Adds a Weapon to the inventory
     * 
     * @param item
     * @return true if the weapon was picked up
     */
    public boolean addItem(Weapon item) {
        if(item instanceof GrenadeBelt) {
            this.grenades = (GrenadeBelt)item;
            return true;
        }
        else {
            if(numberOfPrimaryItems() < this.maxPrimaryWeapons || !item.isPrimary()) {
                this.weapons.add(item);
                return true;
            }
        }                
        return false;
    }
    
    /**
     * @param item
     * @return true if the weapon type exists in this inventory
     */
    public boolean hasItem(Type item) {
        return getItem(item) != null;
    }
    
    /**
     * @param item
     * @return the Weapon of type 'item' or null if not in the inventory
     */
    public Weapon getItem(Type item) {
        for(Weapon w : weapons) {
            if(w.getType().equals(item)) {
                return w;
            }
        }
        return null;
    }
    
    public void clear() {
        this.grenades = null;
        this.weapons.clear();
    }
    
    public void removeItem(Weapon item) {
        this.weapons.remove(item);
    }
    
    public Weapon currentItem() {
        return (this.currentItem < this.weapons.size()) ? 
            this.weapons.get(this.currentItem) : null;
    }
    
    public Weapon nextItem() {
        currentItem += 1;
        if(currentItem >= weapons.size()) {
            currentItem = 0;
        }
        return currentItem();
    }
    
    public Weapon prevItem() {
        currentItem -= 1;
        if( currentItem < 0) {
            currentItem = Math.max(weapons.size() - 1, 0);
        }
        return currentItem();
    }
    
    /**
     * Equips the desired item 
     * @param item
     * @return the Weapon
     */
    public Weapon equipItem(Type item) {
        for(int i = 0; i < weapons.size(); i++) {
            if(weapons.get(i).getType().equals(item)) {
                currentItem = i;
                break;
            }
        }
        
        return currentItem();
    }
    
    /**
     * Number of weapons currently held
     * @return Number of weapons currently held
     */
    public int numberOfItems() {
        return this.weapons.size();
    }
    
    public int numberOfPrimaryItems() {
        int numberOfPrimary = 0;
        for(int i = 0; i < weapons.size(); i++) {
            if(weapons.get(i).isPrimary()) {
                numberOfPrimary++;
            }
        }
        return numberOfPrimary;
    }
    
    /**
     * @return true if the primary weapon storage is full
     */
    public boolean isPrimaryFull() {
        return numberOfPrimaryItems() >= this.maxPrimaryWeapons;
    }
    
    /**
     * @return the weapons
     */
    public List<Weapon> getItems() {
        return weapons;
    }
}
