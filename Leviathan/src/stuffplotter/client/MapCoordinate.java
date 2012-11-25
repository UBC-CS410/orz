package stuffplotter.client;

import com.google.gwt.maps.client.geom.LatLng;

/**
 * Class to represent a MapCoordinate and help with conversion between a LatLng to a
 * Double[].
 */
public class MapCoordinate
{
	/**
	 * LatLng as a Double[2] where the first entry is the latitude, and the second entry is the
	 * longitude.
	 */
	private Double[] coordinateAsDoubles;
	private LatLng coordinateAsLatLng;
	
	/**
	 * Constructor for MapCoordinate that takes in a LatLng.
	 * @pre coordinate != null;
	 * @post true;
	 * @param coordinate - the map coordinate to store.
	 */
	public MapCoordinate(LatLng coordinate)
	{
		this.coordinateAsLatLng = coordinate;
		this.coordinateAsDoubles = new Double[2];
		this.coordinateAsDoubles[0] = coordinate.getLatitude();
		this.coordinateAsDoubles[1] = coordinate.getLongitude();
	}
	
	/**
	 * Constructor for MapCoordinate that takes in a Double[2] of size 2 where the first entry
	 * is the latitude and the second entry is the longitude.
	 * @pre coordinate != null && coordinate.length == 2;
	 * @post true;
	 * @param coordinate - the map coordinate to store.
	 */
	public MapCoordinate(Double[] coordinate)
	{
		this.coordinateAsDoubles = coordinate;
		Double latitude = coordinate[0];
		Double longitude = coordinate[1];
		LatLng mapCoordinate = LatLng.newInstance(latitude, longitude);
		this.coordinateAsLatLng = mapCoordinate;
	}
	
	/**
	 * Retrieve the coordinate as a LatLng.
	 * @pre true;
	 * @post true;
	 * @return the coordinate as a LatLng.
	 */
	public LatLng getCoordinateAsLatLng()
	{
		return this.coordinateAsLatLng;
	}
	
	/**
	 * Retrieve the coordinate as a Double[2].
	 * @pre true;
	 * @post true;
	 * @return the coordinate as a Double[2].
	 */
	public Double[] getCoordinateAsArray()
	{
		return this.coordinateAsDoubles;
	}
}
