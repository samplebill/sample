package org.mifosplatform.portfolio.billingorder.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.portfolio.billingorder.commands.BillingOrderCommand;
import org.mifosplatform.portfolio.billingorder.commands.InvoiceCommand;
import org.mifosplatform.portfolio.billingorder.commands.InvoiceTaxCommand;
import org.mifosplatform.portfolio.billingorder.data.BillingOrderData;
import org.mifosplatform.portfolio.billingorder.domain.InvoiceTax;
import org.mifosplatform.portfolio.billingorder.exceptions.BillingOrderNoRecordsFoundException;
import org.mifosplatform.portfolio.order.domain.OrderRepository;
import org.mifosplatform.portfolio.taxmaster.data.TaxMappingRateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateBillingOrderServiceImplementation implements
		GenerateBillingOrderService {

	private final GenerateBill generateBill;
	private final OrderRepository orderRepository;

	@Autowired
	public GenerateBillingOrderServiceImplementation(GenerateBill generateBill,
			final OrderRepository orderRepository) {
		this.generateBill = generateBill;
		this.orderRepository = orderRepository;
	}

	@Override
	public List<BillingOrderCommand> generatebillingOrder(
			List<BillingOrderData> products) {

		BillingOrderCommand billingOrderCommand = null;
		List<BillingOrderCommand> billingOrderCommands = new ArrayList<BillingOrderCommand>();

		// First loop -- insert a record in invoice_charge
		// For each Record we have to first identify the charge type based
		// on
		// charge type if NRC it is Only One time Bill
		// Charge type = RC we have to do the calculation based on the
		// duration
		// and duration type * price
		// Phase II implementation of Discounts
		// Phase III implementation of UC will come into picture
		// Second loop -- insert a record in invoice_tax
		// For each Record seek the mapping of Tax to the charge code if
		// exists
		// then we need to do the calculation and post a record in
		// invoice_tax
		// if not available the exit
		// Phase II implementation will be for if the charge code has
		// multiple
		// tax mappings
		// Phase III implementation will be for tax on tax
		// Phase IV implementation will be on Round off Values
		// Final Round up -- insert a record in invoice base on the client
		// id --
		// Each client may have one or many billing records

		// 1st loop on the records

		if (products.size() != 0) {

			for (BillingOrderData billingOrderData : products) {

				if(billingOrderData.getOrderStatus() ==3){
					billingOrderCommand=generateBill.getCancelledOrderBill(billingOrderData);	
					billingOrderCommands.add(billingOrderCommand);
				}
				
				else if (generateBill.isChargeTypeNRC(billingOrderData)) {
						// code to be developed latter
						System.out.println("---- NRC ---");
							billingOrderCommand = generateBill.getOneTimeBill(billingOrderData);
							billingOrderCommands.add(billingOrderCommand);

					} else if (generateBill.isChargeTypeRC(billingOrderData)) {

						System.out.println("---- RC ----");

						BigDecimal pricePerMonth = null;
						LocalDate startDate = null;
						LocalDate endDate = null;
						BigDecimal price = null;
						LocalDate invoiceTillDate = null;
						LocalDate nextbillDate = null;
						// monthly
						if (billingOrderData.getDurationType()
								.equalsIgnoreCase("month(s)") ) {
							if (billingOrderData.getBillingAlign()
									.equalsIgnoreCase("N")) {

								billingOrderCommand = generateBill
										.getMonthyBill(billingOrderData);
								billingOrderCommands.add(billingOrderCommand);

							} else if (billingOrderData.getBillingAlign().equalsIgnoreCase("Y")) {

								if (billingOrderData.getInvoiceTillDate() == null) {

									billingOrderCommand = generateBill
											.getProrataMonthlyFirstBill(billingOrderData);
									billingOrderCommands
											.add(billingOrderCommand);

								} else if (billingOrderData
										.getInvoiceTillDate() != null) {

									billingOrderCommand = generateBill
											.getNextMonthBill(billingOrderData);
									billingOrderCommands
											.add(billingOrderCommand);

								}
							}

							// weekly
						} else if (billingOrderData.getDurationType()
								.equalsIgnoreCase("week(s)")) {

							if (billingOrderData.getBillingAlign()
									.equalsIgnoreCase("N")) {

								billingOrderCommand = generateBill
										.getWeeklyBill(billingOrderData);
								billingOrderCommands.add(billingOrderCommand);

							} else if (billingOrderData.getBillingAlign()
									.equalsIgnoreCase("Y")) {

								if (billingOrderData.getInvoiceTillDate() == null) {

									billingOrderCommand = generateBill
											.getProrataWeeklyFirstBill(billingOrderData);
									billingOrderCommands
											.add(billingOrderCommand);

								} else if (billingOrderData
										.getInvoiceTillDate() != null) {

									billingOrderCommand = generateBill
											.getNextWeeklyBill(billingOrderData);
									billingOrderCommands
											.add(billingOrderCommand);
								}
							}

							// daily
						} else if (billingOrderData.getDurationType()
								.equalsIgnoreCase("daily")) {
							// To be developed latter
						}
					}
				
			}
		} else if (products.size() == 0) {
			throw new BillingOrderNoRecordsFoundException();
		}
		// return billingOrderCommand;
		return billingOrderCommands;
	}

	@Override
	public List<InvoiceTaxCommand> generateInvoiceTax(
			List<TaxMappingRateData> taxMappingRateDatas, BigDecimal price,
			Long clientId) {

		BigDecimal taxPercentage = null;
		String taxCode = null;
		BigDecimal taxAmount = null;
		List<InvoiceTaxCommand> invoiceTaxCommands = new ArrayList<InvoiceTaxCommand>();
		InvoiceTaxCommand invoiceTaxCommand = null;
		if (taxMappingRateDatas != null) {

			for (TaxMappingRateData taxMappingRateData : taxMappingRateDatas) {

				taxPercentage = taxMappingRateData.getRate();
				taxCode = taxMappingRateData.getTaxCode();
				taxAmount = price.multiply(taxPercentage.divide(new BigDecimal(
						100)));

				invoiceTaxCommand = new InvoiceTaxCommand(clientId, null, null,
						taxCode, null, taxPercentage, taxAmount);
				invoiceTaxCommands.add(invoiceTaxCommand);
			}

		}
		return invoiceTaxCommands;

	}

	@Override
	public InvoiceCommand generateInvoice(
			List<BillingOrderCommand> billingOrderCommands) {
		BigDecimal totalChargeAmountForServices = BigDecimal.ZERO;
		BigDecimal totalTaxAmountForServices = BigDecimal.ZERO;
		BigDecimal invoiceAmount = BigDecimal.ZERO;
		LocalDate invoiceDate = new LocalDate();
		for (BillingOrderCommand billingOrderCommand : billingOrderCommands) {
			totalChargeAmountForServices = billingOrderCommand.getPrice().add(
					totalChargeAmountForServices);
			List<InvoiceTax> listOfTaxes = billingOrderCommand.getListOfTax();
			BigDecimal netTaxForService = BigDecimal.ZERO;
			for (InvoiceTax invoiceTax : listOfTaxes) {
				netTaxForService = invoiceTax.getTaxAmount().add(
						netTaxForService);
			}
			totalTaxAmountForServices = totalTaxAmountForServices
					.add(netTaxForService);
		}
		invoiceAmount = totalChargeAmountForServices
				.add(totalTaxAmountForServices);

		return new InvoiceCommand(billingOrderCommands.get(0).getClientId(),
				invoiceDate.toDate(), invoiceAmount,
				totalChargeAmountForServices, totalTaxAmountForServices,
				"active", null, null, null, null);

		// invoice amount as zero
		// List<InvoiceCommand> invoiceCommands = new
		// ArrayList<InvoiceCommand>();
		// List<Long> orderIds = new ArrayList<Long>();
		// for(BillingOrderCommand billingOrderCommand : billingOrderCommands){
		// Long orderId = billingOrderCommand.getClientOrderId();
		// orderIds.add(orderId);
		// }
		//
		// Set<Long> clientOrderIds = toSet(orderIds);

		// for(Long orderId : clientOrderIds){
		// BigDecimal totalChargeAmountForServices = BigDecimal.ZERO;
		// BigDecimal totalTaxAmountForServices = BigDecimal.ZERO;
		// BigDecimal invoiceAmount = BigDecimal.ZERO;
		// LocalDate invoiceDate = new LocalDate();
		// for(BillingOrderCommand billingOrderCommand : billingOrderCommands){
		// if(billingOrderCommand.getClientOrderId()== orderId){
		// // our main logic goes here
		// totalChargeAmountForServices =
		// billingOrderCommand.getPrice().add(totalChargeAmountForServices);
		// BigDecimal netTaxForService = BigDecimal.ZERO;
		// List<InvoiceTax> listOfTaxes = billingOrderCommand.getListOfTax();
		// for(InvoiceTax invoiceTax : listOfTaxes){
		// netTaxForService = invoiceTax.getTaxAmount().add(netTaxForService);
		// }
		// totalTaxAmountForServices =
		// totalTaxAmountForServices.add(netTaxForService);
		// }
		// }
		// invoiceAmount =
		// totalChargeAmountForServices.add(totalTaxAmountForServices);
		// InvoiceCommand invoiceCommand = new
		// InvoiceCommand(billingOrderCommands.get(0).getClientId(),
		// invoiceDate.toDate(),
		// invoiceAmount, totalChargeAmountForServices,
		// totalTaxAmountForServices, "active", null,
		// null, null, null);
		//
		// invoiceCommands.add(invoiceCommand);
		// }
		// return invoiceCommands;

		// for(BillingOrderCommand billingOrderCommand : billingOrderCommands){
		// totalChargeAmountForServices =
		// billingOrderCommand.getPrice().add(totalChargeAmountForServices);
		// BigDecimal netTaxForService = BigDecimal.ZERO;
		// List<InvoiceTax> listOfTaxes = billingOrderCommand.getListOfTax();
		// for(InvoiceTax invoiceTax : listOfTaxes){
		// netTaxForService = invoiceTax.getTaxAmount().add(netTaxForService);
		// }
		// totalTaxAmountForServices =
		// totalTaxAmountForServices.add(netTaxForService);
		//
		// }
		//
		// invoiceAmount =
		// totalChargeAmountForServices.add(totalTaxAmountForServices);

		// for(BillingOrder billingOrder : listOfBillingOrders){
		// BigDecimal netChargeAmount = billingOrder.getChargeAmount();
		// BigDecimal netTaxAmount = BigDecimal.ZERO;
		// for(List<InvoiceTax> tax : listOfListOfTaxes){
		// BigDecimal taxAmount = BigDecimal.ZERO;
		// if (tax.size() != 0) {
		// for (InvoiceTax invoiceTax : tax) {
		// taxAmount = invoiceTax.getTaxAmount();
		// netTaxAmount = taxAmount.add(netTaxAmount);
		// }
		// }
		// }
		// totalTaxAmount = netTaxAmount.add(totalTaxAmount);
		// totalChargeAmount = netChargeAmount.add(totalChargeAmount);
		//
		// }
		// LocalDate invoiceDate = new LocalDate();
		// BigDecimal netChargeAmount = command.getPrice();
		// BigDecimal taxAmount = null;
		// BigDecimal totalTaxAmount = BigDecimal.ZERO;

		// if (tax.size() != 0) {
		// for (InvoiceTax invoiceTax : tax) {
		// taxAmount = invoiceTax.getTaxAmount();
		// totalTaxAmount = taxAmount.add(totalTaxAmount);
		// }
		// }

		// BigDecimal invoiceAmount = totalChargeAmount.add(totalTaxAmount);
		// return new InvoiceCommand(billingOrderCommands.get(0).getClientId(),
		// invoiceDate.toDate(),
		// invoiceAmount, totalChargeAmountForServices,
		// totalTaxAmountForServices, "active", null,
		// null, null, null);
	}
}
