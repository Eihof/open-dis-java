package edu.nps.moves.dis7;

import java.io.*;

/**
 * The firing of a weapon or expendable shall be communicated by issuing a Fire
 * PDU. Sectioin 7.3.2. COMPLETE
 *
 * Copyright (c) 2008-2016, MOVES Institute, Naval Postgraduate School. All
 * rights reserved. This work is licensed under the BSD open source license,
 * available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class FirePdu extends WarfareFamilyPdu implements Serializable {

    /**
     * This field shall specify the entity identification of the fired munition
     * or expendable. This field shall be represented by an Entity Identifier
     * record (see 6.2.28).
     */
    protected EntityID munitionExpendibleID = new EntityID();

    /**
     * This field shall contain an identification generated by the firing entity
     * to associate related firing and detonation events. This field shall be
     * represented by an Event Identifier record (see 6.2.34).
     */
    protected EventIdentifier eventID = new EventIdentifier();

    /**
     * This field shall identify the fire mission (see 5.4.3.3). This field
     * shall be representedby a 32-bit unsigned integer.
     */
    protected long fireMissionIndex;

    /**
     * This field shall specify the location, in world coordinates, from which
     * the munition was launched, and shall be represented by a World
     * Coordinates record (see 6.2.97).
     */
    protected Vector3Double locationInWorldCoordinates = new Vector3Double();

    /**
     * This field shall describe the firing or launch of a munition or
     * expendable represented by one of the following types of Descriptor
     * records: Munition Descriptor (6.2.20.2) or Expendable Descriptor
     * (6.2.20.4).
     */
    protected MunitionDescriptor descriptor = new MunitionDescriptor();

    /**
     * This field shall specify the velocity of the fired munition at the point
     * when the issuing simulation application intends the externally visible
     * effects of the launch (e.g. exhaust plume or muzzle blast) to first
     * become apparent. The velocity shall be represented in world coordinates.
     * This field shall be represented by a Linear Velocity Vector record [see
     * 6.2.95 item c)].
     */
    protected Vector3Float velocity = new Vector3Float();

    /**
     * This field shall specify the range that an entity’s fire control system
     * has assumed in computing the fire control solution. This field shall be
     * represented by a 32-bit floating point number in meters. For systems
     * where range is unknown or unavailable, this field shall contain a value
     * of zero.
     */
    protected float range;

    /**
     * Constructor
     */
    public FirePdu() {
        setPduType((short) 2);
    }

    public int getMarshalledSize() {
        int marshalSize = 0;

        marshalSize = super.getMarshalledSize();
        marshalSize = marshalSize + munitionExpendibleID.getMarshalledSize();  // munitionExpendibleID
        marshalSize = marshalSize + eventID.getMarshalledSize();  // eventID
        marshalSize = marshalSize + 4;  // fireMissionIndex
        marshalSize = marshalSize + locationInWorldCoordinates.getMarshalledSize();  // locationInWorldCoordinates
        marshalSize = marshalSize + descriptor.getMarshalledSize();  // descriptor
        marshalSize = marshalSize + velocity.getMarshalledSize();  // velocity
        marshalSize = marshalSize + 4;  // range

        return marshalSize;
    }

    public void setMunitionExpendibleID(EntityID pMunitionExpendibleID) {
        munitionExpendibleID = pMunitionExpendibleID;
    }

    public EntityID getMunitionExpendibleID() {
        return munitionExpendibleID;
    }

    public void setEventID(EventIdentifier pEventID) {
        eventID = pEventID;
    }

    public EventIdentifier getEventID() {
        return eventID;
    }

    public void setFireMissionIndex(long pFireMissionIndex) {
        fireMissionIndex = pFireMissionIndex;
    }

    public long getFireMissionIndex() {
        return fireMissionIndex;
    }

    public void setLocationInWorldCoordinates(Vector3Double pLocationInWorldCoordinates) {
        locationInWorldCoordinates = pLocationInWorldCoordinates;
    }

    public Vector3Double getLocationInWorldCoordinates() {
        return locationInWorldCoordinates;
    }

    public void setDescriptor(MunitionDescriptor pDescriptor) {
        descriptor = pDescriptor;
    }

    public MunitionDescriptor getDescriptor() {
        return descriptor;
    }

    public void setVelocity(Vector3Float pVelocity) {
        velocity = pVelocity;
    }

    public Vector3Float getVelocity() {
        return velocity;
    }

    public void setRange(float pRange) {
        range = pRange;
    }

    public float getRange() {
        return range;
    }

    public void marshal(DataOutputStream dos) {
        super.marshal(dos);
        try {
            munitionExpendibleID.marshal(dos);
            eventID.marshal(dos);
            dos.writeInt((int) fireMissionIndex);
            locationInWorldCoordinates.marshal(dos);
            descriptor.marshal(dos);
            velocity.marshal(dos);
            dos.writeFloat((float) range);
        } // end try 
        catch (Exception e) {
            System.out.println(e);
        }
    } // end of marshal method

    public void unmarshal(DataInputStream dis) {
        super.unmarshal(dis);

        try {
            munitionExpendibleID.unmarshal(dis);
            eventID.unmarshal(dis);
            fireMissionIndex = dis.readInt();
            locationInWorldCoordinates.unmarshal(dis);
            descriptor.unmarshal(dis);
            velocity.unmarshal(dis);
            range = dis.readFloat();
        } // end try 
        catch (Exception e) {
            System.out.println(e);
        }
    } // end of unmarshal method 

    /**
     * Packs a Pdu into the ByteBuffer.
     *
     * @throws java.nio.BufferOverflowException if buff is too small
     * @throws java.nio.ReadOnlyBufferException if buff is read only
     * @see java.nio.ByteBuffer
     * @param buff The ByteBuffer at the position to begin writing
     * @since ??
     */
    public void marshal(java.nio.ByteBuffer buff) {
        super.marshal(buff);
        munitionExpendibleID.marshal(buff);
        eventID.marshal(buff);
        buff.putInt((int) fireMissionIndex);
        locationInWorldCoordinates.marshal(buff);
        descriptor.marshal(buff);
        velocity.marshal(buff);
        buff.putFloat((float) range);
    } // end of marshal method

    /**
     * Unpacks a Pdu from the underlying data.
     *
     * @throws java.nio.BufferUnderflowException if buff is too small
     * @see java.nio.ByteBuffer
     * @param buff The ByteBuffer at the position to begin reading
     * @since ??
     */
    public void unmarshal(java.nio.ByteBuffer buff) {
        super.unmarshal(buff);

        munitionExpendibleID.unmarshal(buff);
        eventID.unmarshal(buff);
        fireMissionIndex = buff.getInt();
        locationInWorldCoordinates.unmarshal(buff);
        descriptor.unmarshal(buff);
        velocity.unmarshal(buff);
        range = buff.getFloat();
    } // end of unmarshal method 


    /*
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        return equalsImpl(obj);
    }

    @Override
    public boolean equalsImpl(Object obj) {
        boolean ivarsEqual = true;

        if (!(obj instanceof FirePdu)) {
            return false;
        }

        final FirePdu rhs = (FirePdu) obj;

        if (!(munitionExpendibleID.equals(rhs.munitionExpendibleID))) {
            ivarsEqual = false;
        }
        if (!(eventID.equals(rhs.eventID))) {
            ivarsEqual = false;
        }
        if (!(fireMissionIndex == rhs.fireMissionIndex)) {
            ivarsEqual = false;
        }
        if (!(locationInWorldCoordinates.equals(rhs.locationInWorldCoordinates))) {
            ivarsEqual = false;
        }
        if (!(descriptor.equals(rhs.descriptor))) {
            ivarsEqual = false;
        }
        if (!(velocity.equals(rhs.velocity))) {
            ivarsEqual = false;
        }
        if (!(range == rhs.range)) {
            ivarsEqual = false;
        }

        return ivarsEqual && super.equalsImpl(rhs);
    }
} // end of class
