import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public class TicketServiceImplTest {
    TicketServiceImpl ticketService;

    @BeforeEach
    void setUp() {
        ticketService = new TicketServiceImpl();
    }

    @Test
    private void testPurchaseTicketsWithAdultChildInfant_1() {
        long accountId = 111;
        TicketTypeRequest objTicketTypeRequest1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
        TicketTypeRequest objTicketTypeRequest2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);
        TicketTypeRequest objTicketTypeRequest3 = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 4);
        TicketTypeRequest[] ticketTypeRequest = {objTicketTypeRequest1, objTicketTypeRequest2, objTicketTypeRequest3};
        ticketService.purchaseTickets(accountId, ticketTypeRequest);
    }

    @Test
    private void testPurchaseTicketsWithAdultChild() {
        long accountId = 112;
        TicketTypeRequest objTicketTypeRequest1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
        TicketTypeRequest objTicketTypeRequest2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);
        TicketTypeRequest[] ticketTypeRequest = {objTicketTypeRequest1, objTicketTypeRequest2};
        ticketService.purchaseTickets(accountId, ticketTypeRequest);
    }

    @Test
    private void testPurchaseTicketsWithOnlyAdult() {
        long accountId = 113;
        TicketTypeRequest objTicketTypeRequest1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
        TicketTypeRequest[] ticketTypeRequest = {objTicketTypeRequest1};
        ticketService.purchaseTickets(accountId, ticketTypeRequest);
    }

    @Test
    private void testPurchaseTicketsInvalidAccountId() {
        long accountId = 0;
        TicketTypeRequest objTicketTypeRequest1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
        TicketTypeRequest objTicketTypeRequest2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);
        TicketTypeRequest objTicketTypeRequest3 = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 4);
        TicketTypeRequest[] ticketTypeRequest = {objTicketTypeRequest1, objTicketTypeRequest2, objTicketTypeRequest3};
        ticketService.purchaseTickets(accountId, ticketTypeRequest);
    }

    @Test
    private void testPurchaseTicketsWithoutAdult() {
        long accountId = 114;
        TicketTypeRequest objTicketTypeRequest1 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);
        TicketTypeRequest objTicketTypeRequest2 = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 4);
        TicketTypeRequest[] ticketTypeRequest = {objTicketTypeRequest1, objTicketTypeRequest2};
        ticketService.purchaseTickets(accountId, ticketTypeRequest);
    }

    @Test
    private void testPurchaseTicketsWithAdultChildInfant_2() {
        long accountId = 115;
        TicketTypeRequest objTicketTypeRequest1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 12);
        TicketTypeRequest objTicketTypeRequest2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 30);
        TicketTypeRequest objTicketTypeRequest3 = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 10);
        TicketTypeRequest[] ticketTypeRequest = {objTicketTypeRequest1, objTicketTypeRequest2, objTicketTypeRequest3};
        ticketService.purchaseTickets(accountId, ticketTypeRequest);
    }
}
