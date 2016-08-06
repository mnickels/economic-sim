package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Properties;

/**
 * This class extends the java.util.Properties class to allow cleaner String conversion.
 * 
 * @author Joshua Neighbarger | jneigh@uw.edu
 */
public class XMLProperties extends Properties {
	
	/** Automatically generated serial version. */
	private static final long serialVersionUID = -5993969900932246169L;

	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(final String key) {
		return Boolean.valueOf(getProperty(key));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public byte getByte(final String key) {
		return Byte.valueOf(getProperty(key));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public char getChar(final String key) {
		return Character.valueOf(getProperty(key).charAt(0));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public short getShort(final String key) {
		return Short.valueOf(getProperty(key));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(final String key) {
		return Integer.valueOf(getProperty(key));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public float getFloat(final String key) {
		return Float.valueOf(getProperty(key));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public long getLong(final String key) {
		return Long.valueOf(getProperty(key));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public double getDouble(final String key) {
		return Double.valueOf(getProperty(key));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getString(final String key) {
		return getProperty(key);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public BigDecimal getBigDecimal(final String key) {
		return new BigDecimal(getProperty(key));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public BigInteger getBigInteger(final String key) {
		return new BigInteger(getProperty(key));
	}
	
}