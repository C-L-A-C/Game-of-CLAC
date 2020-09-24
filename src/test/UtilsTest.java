package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import utils.Utils;

class UtilsTest {

	@Test
	void test_map() {
		int[][] values = {
				{0, 5, 0, 10},
				{0, 8, 0, 5},
				{-5, 15, 0, 8},
				{-10, -1, -8, -3},
				{8, 0, 5, 10},
				{12, 20, 10, 0},
				{20, 3, 10, 2},
				{-5, -10, 5, 15}
		};
		for (int[] vals : values)
		{
			int sens = vals[1] >= vals[0] ? 1 : -1;
			for (int x = vals[0]; x != vals[1] + sens * 3; x += sens)
				assertEquals(PApplet.map(x, vals[0], vals[1], vals[2], vals[3]), 
						Utils.map(x, vals[0], vals[1], vals[2], vals[3]), 
						1E-6,
						"Pour " + x + " allant de " + vals[0] + " à " + vals[1] + " vers " + vals[2] + " à " + vals[3]);
		}
	}

}
