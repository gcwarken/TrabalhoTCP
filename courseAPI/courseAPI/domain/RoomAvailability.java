package courseAPI.domain;

import java.util.Arrays;

public class RoomAvailability {
	private boolean[][] schedule;	
	private int days = 6;
	private int hours = 16;
	
	public RoomAvailability() {
		this.schedule = new boolean[this.days][this.hours];
		
		// fill scheduel with true = all slots available 
		for (boolean[] row: this.schedule) 
			Arrays.fill(row, true);
		
//		schedule:
//					|0		|1		|2		|3		|4		|5
//					|seg____|ter____|qua____|qui____|sex____|sab____
//		00	07h30	|_______|_______|_______|_______|_______|_______|
//		01	08h30	|_______|_______|_______|_______|_______|_______|
//		02	09h30	|_______|_______|_______|_______|_______|_______|
//		03	10h30	|_______|_______|_______|_______|_______|_______|
//		04	11h30	|_______|_______|_______|_______|_______|_______|
//		05	12h30	|_______|_______|_______|_______|_______|_______|
//		06	13h30	|_______|_______|_______|_______|_______|_______|
//		07	14h30	|_______|_______|_______|_______|_______|_______|
//		08	15h30	|_______|_______|_______|_______|_______|_______|
//		09	16h30	|_______|_______|_______|_______|_______|_______|
//		10	17h30	|_______|_______|_______|_______|_______|_______|
//		11	17h30	|_______|_______|_______|_______|_______|_______|
//		12	19h30	|_______|_______|_______|_______|_______|_______|
//		13	20h30	|_______|_______|_______|_______|_______|_______|
//		14	21h30	|_______|_______|_______|_______|_______|_______|
//		15	22h30	|_______|_______|_______|_______|_______|_______|
	}
	
	protected boolean getAvailability(int d, int h) {
		return this.schedule[this.convertDay(d)][this.convertHour(h)]; 
	}
	
	protected void setAvailability(int d, int h, boolean b) {
		this.schedule[this.convertDay(d)][this.convertHour(h)] = b;
	}
		
	protected void notAvailable() {
		for (boolean[] row: this.schedule) 
			Arrays.fill(row, false);
	}
	
	private int convertDay(int d) {
		return d - 1;
	}
	
	private int convertHour(int h) {
		return (h - 730) / 100;
	}
}
