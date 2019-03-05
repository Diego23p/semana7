package edu.eci.cvds.calculator;

import java.util.Optional;

import edu.eci.cvds.model.SeatCategory;
import edu.eci.cvds.model.BookingOutput;
import edu.eci.cvds.model.BookingResult;

/**
 * Utility class to validate an airline's booking
 */
public class AirlineCalculator implements BookingCalculator {

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public BookingOutput calculate(Integer seatsNumber, SeatCategory category) {
		// TODO: Add required validations and calculate total price if applies
		
		if (seatsNumber<1 || seatsNumber>100) {
			return  new BookingOutput(BookingResult.INVALID, Optional.empty());
		}
        float precio=seatsNumber * category.getPrice();
        if (seatsNumber>=6 && seatsNumber<=9) {
        	precio-=precio*(0.02);
        }
        else if (seatsNumber>=10 && seatsNumber<=14) {
        	precio-=precio*(0.1);
        }
        else if (seatsNumber>=15) {
        	precio-=precio*(0.2);
        }
        
		if (SeatCategory.ECONOMY_CLASS.equals(category)&& seatsNumber<=50){
			return new BookingOutput(BookingResult.SUCCESS, Optional.of(precio));

		}
		if (SeatCategory.FIRST_CLASS.equals(category)&& seatsNumber<=15){
			return new BookingOutput(BookingResult.SUCCESS, Optional.of(precio));

		}
		if (SeatCategory.EMERGENCY_DOOR.equals(category) && seatsNumber<=8){
			return new BookingOutput(BookingResult.SUCCESS, Optional.of(precio));

		}
		else {

		return new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty());
		}
	}
}
