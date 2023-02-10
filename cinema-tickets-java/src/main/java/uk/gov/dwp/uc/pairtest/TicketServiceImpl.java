package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.HashMap;
import java.util.Map;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        //Checking if accountId is valid
        if (accountId > 0) {
            Map<TicketTypeRequest.Type, Integer> ticketDetails = new HashMap<>();
            for (int i = 0; i < ticketTypeRequests.length; i++) {
                TicketTypeRequest ticketTypeRequest = ticketTypeRequests[i];
                //getting ticket type
                TicketTypeRequest.Type ticketType = ticketTypeRequest.getTicketType();
                //getting no of tickets
                int noOfTickets = ticketTypeRequest.getNoOfTickets();
                ticketDetails.put(ticketType, noOfTickets);
            }

            //Checking if Adult is present - Child and Infant tickets cannot be purchased without purchasing an Adult ticket.
            if (ticketDetails.keySet().contains(TicketTypeRequest.Type.ADULT)) {
                //calculating total number of tickets purchased by a purchaser
                calculateTotalNumberOfTickets(accountId, ticketDetails);
            } else {
                System.out.println("Child and Infant tickets cannot be purchased without purchasing an Adult ticket");
            }
        } else {
            System.out.println("Invalid AccountId");
        }
    }

    private void calculateTotalNumberOfTickets(long accountId, Map<TicketTypeRequest.Type, Integer> ticketDetails) {
        int totalNoOfTickets = 0;
        int totalAmount = 0;

        for(TicketTypeRequest.Type ticketType : ticketDetails.keySet())  {
            if (ticketType.equals(TicketTypeRequest.Type.ADULT)) {
                int adultTickets = ticketDetails.get(ticketType);
                int adultAmt = adultTickets * 20;
                totalNoOfTickets += adultTickets;
                totalAmount += adultAmt;
            } else if (ticketType.equals(TicketTypeRequest.Type.CHILD)) {
                int childTickets = ticketDetails.get(ticketType);
                int childAmt = childTickets * 10;
                totalNoOfTickets += childTickets;
                totalAmount += childAmt;
            } else if (ticketType.equals(TicketTypeRequest.Type.INFANT)) {
                //Infants do not pay for a ticket and are not allocated a seat hence assigning 0
                totalNoOfTickets += 0;
                totalAmount += 0;
            }
        }

        //Method to reserve seat
        callReserveSeat(accountId, totalNoOfTickets);
        //Method to make payment
        callMakePayment(accountId, totalAmount);
    }

    private void callReserveSeat(long accountId, int totalSeats) {
        System.out.println("Seat has been reserved for AccountId: " + accountId + " and Total Seats: " + totalSeats);
        SeatReservationServiceImpl seatReservationService = new SeatReservationServiceImpl();
        seatReservationService.reserveSeat(accountId, totalSeats);
    }

    private void callMakePayment(long accountId, int totalAmount){
        System.out.println("Payment has been completed for AccountId: " + accountId + " and Total Payment: " + totalAmount);
        TicketPaymentServiceImpl ticketPaymentService=new TicketPaymentServiceImpl();
        ticketPaymentService.makePayment(accountId, totalAmount);
    }


}
