/*
 * Based on example code by Stefan Gustavson (stegu@itn.liu.se).
 * Optimisations by Peter Eastman (peastman@drizzle.stanford.edu).
 * Octavization by "longshorts" on the following forum:
 * http://www.java-gaming.org/index.php?topic=31637.0
 * Randomization and 2D-ification by Mike Nickels (mnickels@uw.edu).
 */

package model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Simplex Noise generator.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class SimplexNoise {

	private static Grad grad3[] = {new Grad(1,1,0),new Grad(-1,1,0),new Grad(1,-1,0),new Grad(-1,-1,0),
			new Grad(1,0,1),new Grad(-1,0,1),new Grad(1,0,-1),new Grad(-1,0,-1),
			new Grad(0,1,1),new Grad(0,-1,1),new Grad(0,1,-1),new Grad(0,-1,-1)};

	private short p[] = new short[256];
	private short perm[] = new short[512];
	private short permMod12[] = new short[512];

	// Skewing and unskewing factors
	private static final double F2 = 0.5*(Math.sqrt(3.0)-1.0);
	private static final double G2 = (3.0-Math.sqrt(3.0))/6.0;
	
	final long seed;
	
	/**
	 * Creates a random SimplexNoise object.
	 * 
	 * @author Mike Nickels | mnickels@uw.edu
	 */
	public SimplexNoise() {
		this(System.nanoTime());
	}
	
	/**
	 * Pseudo-random Simplex Noise controlled by a seed.
	 * @param seed the seed for the Random object used.
	 * 
	 * @author Mike Nickels | mnickels@uw.edu
	 */
	public SimplexNoise(long seed) {
		this.seed = seed;
		
		List<Short> plist = new ArrayList<>();
		for (short i = 0; i < p.length; i++) {
			plist.add(i);
		}
		Collections.shuffle(plist, new Random(seed));
		for (int i = 0; i < p.length; i++) {
			p[i] = plist.get(i);
		}
		
		for(int i=0; i<512; i++) {
			perm[i]=p[i & 255];
			permMod12[i] = (short)(perm[i] % 12);
		}
	}

	/**
	 * Gets the currently-in-use seed value.
	 * @return The seed used by this SimplexNoise object to generate random noise.
	 * 
	 * @author Mike Nickels | mnickels@uw.edu
	 */
	public long getCurrentSeed() {
		return seed;
	}
	
	/**
	 * Takes Simplex Noise and returns the same noise scaled to values between -1 and 1.
	 * @param noise the noise to normalize.
	 * @return The same noise scaled to have a minimum value of -1 and a maximum value of 1.
	 * 
	 * @author Mike Nickels | mnickels@uw.edu
	 */
	private float[][] normalize(float[][] noise) {
		float min = 0;
		float max = 0;
		float[][] normalizedNoise = new float[noise.length][noise[0].length];
		
		for (int y = 0; y < noise.length; y++) {
			for (int x = 0; x < noise[y].length; x++) {
				if (noise[y][x] < min) {
					min = noise[y][x];
				} else if (noise[y][x] > max) {
					max = noise[y][x];
				}
			}
		}
		
		for (int y = 0; y < noise.length; y++) {
			for (int x = 0; x < noise[y].length; x++) {
				normalizedNoise[y][x] = ((noise[y][x] - min) / (max - min)) * 2 - 1;
			}
		}
		
		return normalizedNoise;
	}

	// TODO: Look into why every simplex noise has a value of 0 at the (0,0) index, and figure out what to do to compensate?
	/**
	 * Generates octaved simplex noise. All values will be between -1 and 1.
	 * @param width the width of the noise.
	 * @param height the height of the noise.
	 * @param octaves the number of iterations to run. Higher number of octaves means more branching in noise.
	 * 		  Noticeable difference up to about 7 octaves, after that the effect is minimal.
	 * @param roughness the roughness of the noise. Higher values lead to noisier noise.
	 * @param frequency the steepness of the noise. Higher values lead to steeper slopes in the noise.
	 * 		  Higher values produce more numerous but thinner peaks and valleys.
	 * @return A 2D array of float values between -1 and 1 containing Simplex Noise.
	 * 
	 * @author Mike Nickels | mnickels@uw.edu
	 */
	public float[][] generateOctavedSimplexNoise(int width, int height, int octaves, float roughness, float frequency){
		float[][] totalNoise = new float[height][width];
		float layerFrequency = frequency;
		float layerWeight = 1;
		float weightSum = 0;

		for (int octave = 0; octave < octaves; octave++) {
			//Calculate single layer/octave of simplex noise, then add it to total noise
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					totalNoise[y][x] += (float) noise(x * layerFrequency,y * layerFrequency) * layerWeight;
				}
			}

			//Increase variables with each incrementing octave
			layerFrequency *= 2;
			weightSum += layerWeight;
			layerWeight *= roughness;

		}
		return normalize(totalNoise);
	}

	// This method is a *lot* faster than using (int)Math.floor(x)
	private static int fastfloor(double x) {
		int xi = (int)x;
		return x<xi ? xi-1 : xi;
	}

	private static double dot(Grad g, double x, double y) {
		return g.x*x + g.y*y; }


	// 2D simplex noise
	public double noise(double xin, double yin) {
		double n0, n1, n2; // Noise contributions from the three corners
		// Skew the input space to determine which simplex cell we're in
		double s = (xin+yin)*F2; // Hairy factor for 2D
		int i = fastfloor(xin+s);
		int j = fastfloor(yin+s);
		double t = (i+j)*G2;
		double X0 = i-t; // Unskew the cell origin back to (x,y) space
		double Y0 = j-t;
		double x0 = xin-X0; // The x,y distances from the cell origin
		double y0 = yin-Y0;
		// For the 2D case, the simplex shape is an equilateral triangle.
		// Determine which simplex we are in.
		int i1, j1; // Offsets for second (middle) corner of simplex in (i,j) coords
		if(x0>y0) {i1=1; j1=0;} // lower triangle, XY order: (0,0)->(1,0)->(1,1)
		else {i1=0; j1=1;}      // upper triangle, YX order: (0,0)->(0,1)->(1,1)
		// A step of (1,0) in (i,j) means a step of (1-c,-c) in (x,y), and
		// a step of (0,1) in (i,j) means a step of (-c,1-c) in (x,y), where
		// c = (3-sqrt(3))/6
		double x1 = x0 - i1 + G2; // Offsets for middle corner in (x,y) unskewed coords
		double y1 = y0 - j1 + G2;
		double x2 = x0 - 1.0 + 2.0 * G2; // Offsets for last corner in (x,y) unskewed coords
		double y2 = y0 - 1.0 + 2.0 * G2;
		// Work out the hashed gradient indices of the three simplex corners
		int ii = i & 255;
		int jj = j & 255;
		int gi0 = permMod12[ii+perm[jj]];
		int gi1 = permMod12[ii+i1+perm[jj+j1]];
		int gi2 = permMod12[ii+1+perm[jj+1]];
		// Calculate the contribution from the three corners
		double t0 = 0.5 - x0*x0-y0*y0;
		if(t0<0) n0 = 0.0;
		else {
			t0 *= t0;
			n0 = t0 * t0 * dot(grad3[gi0], x0, y0);  // (x,y) of grad3 used for 2D gradient
		}
		double t1 = 0.5 - x1*x1-y1*y1;
		if(t1<0) n1 = 0.0;
		else {
			t1 *= t1;
			n1 = t1 * t1 * dot(grad3[gi1], x1, y1);
		}
		double t2 = 0.5 - x2*x2-y2*y2;
		if(t2<0) n2 = 0.0;
		else {
			t2 *= t2;
			n2 = t2 * t2 * dot(grad3[gi2], x2, y2);
		}
		// Add contributions from each corner to get the final noise value.
		// The result is scaled to return values in the interval [-1,1].
		return 70.0 * (n0 + n1 + n2);
	}

	// Inner class to speed upp gradient computations
	// (In Java, array access is a lot slower than member access)
	private static class Grad
	{
		double x, y, z, w;

		Grad(double x, double y, double z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}

		Grad(double x, double y, double z, double w)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.w = w;
		}
	}
}
