
package models;

/**
 *
 * @author Matteo Ciman
 */
public class Reading {
    
    private long timestamp;
    private float x;
    private float y;
    private float z;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float proximity;
    
    public Reading(long timestamp, float x, float y, float z,
            float rotationX, float rotationY, float rotationZ, float proximity) {
        
        this.timestamp = timestamp; this.x = x; this.y = y; this.z = z;
        this.rotationX = rotationX; this.rotationY = rotationY; 
        this.rotationZ = rotationZ; this.proximity = proximity;
    }
}
