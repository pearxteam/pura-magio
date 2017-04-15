package net.pearx.purmag.sip;

/**
 * Created by mrAppleXZ on 12.04.17 8:25.
 */
public interface ISipStore
{
    /**
     * Gets the current stored SIP.
     */
    int getSipStored(String type);


    int getMaxSipStored(String type);

    /**
     * Sets the current stored SIP.
     * @param value New stored value.
     */
    void setSipStored(String type, int value);

    /**
     * Can this ISS accept Sip?
     * @param bs Beam Speed
     */
    boolean canSipIn(String type, short bs);

    /**
     * Can this ISS return Sip?
     */
    boolean canSipOut(String type);

    /**
     * Modifies the current stored SIP.
     * stored = stored + value
     * @param value Value to modify.
     */
    void modifySip(String type, int value);

    /**
     * Gets the output Beam Speed.
     */
    short getOutBs();
}
