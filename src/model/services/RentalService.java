package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {


	public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
		super();
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	private Double pricePerHour;
	private Double pricePerDay;
	
	


	private TaxService taxService;
	public TaxService getTaxService() {
		return taxService;
	}

	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	}

	public void processInvoice(CarRental carRental)
	{
		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hours = minutes / 60;
		
		double basicPayment;
		if (hours <=12)
		{
			basicPayment = Math.ceil(hours) * pricePerHour;
		}
		else {
			basicPayment = pricePerDay *  Math.ceil(hours / 24);
		}
		double tax =taxService.tax(basicPayment);
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
}
