package za.co.wethinkcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.RoomBooking;
import za.co.wethinkcode.model.SpaService;
import za.co.wethinkcode.service.Chargeable;
import za.co.wethinkcode.service.GuestAccount;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestBillingSystem {

    private RoomBooking room;
    private SpaService standardSpa;
    private SpaService premiumSpa;
    private GuestAccount account;

    @BeforeEach
    public void setUp() {
        room = new RoomBooking("101A", 3, 500.0);
        standardSpa = new SpaService("Swedish Massage", false);
        premiumSpa = new SpaService("Deep Tissue Massage", true);
        account = new GuestAccount("GUEST-999");
    }

    @Test
    public void roomBooking_calculatesCorrectChargeAndDescription() {
        assertEquals(1500.0, room.calculateCharge(), 0.01);
        assertEquals("Room 101A for 3 nights", room.getChargeDescription());
    }

    @Test
    public void roomBooking_constructorThrowsOnInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new RoomBooking(null, 2, 100.0));
        assertThrows(IllegalArgumentException.class, () -> new RoomBooking("", 2, 100.0));
        assertThrows(IllegalArgumentException.class, () -> new RoomBooking("101A", 0, 100.0));
        assertThrows(IllegalArgumentException.class, () -> new RoomBooking("101A", 2, -50.0));
    }

    @Test
    public void spaService_calculatesCorrectChargeAndDescription() {
        assertEquals(75.0, standardSpa.calculateCharge(), 0.01);
        assertEquals("Standard Spa Service: Swedish Massage", standardSpa.getChargeDescription());

        assertEquals(150.0, premiumSpa.calculateCharge(), 0.01);
        assertEquals("Premium Spa Service: Deep Tissue Massage", premiumSpa.getChargeDescription());
    }

    @Test
    public void spaService_constructorThrowsOnInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new SpaService(null, true));
        assertThrows(IllegalArgumentException.class, () -> new SpaService("", false));
    }

    @Test
    public void guestAccount_constructorThrowsOnInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new GuestAccount(null));
        assertThrows(IllegalArgumentException.class, () -> new GuestAccount(""));
    }

    @Test
    public void guestAccount_addChargeThrowsOnNull() {
        assertThrows(IllegalArgumentException.class, () -> account.addCharge(null));
    }

    @Test
    public void guestAccount_calculatesTotalBalancePolymorphically() {
        account.addCharge(room);         // 1500.0
        account.addCharge(standardSpa);  // 75.0
        account.addCharge(premiumSpa);   // 150.0

        assertEquals(1725.0, account.getTotalBalance(), 0.01);
        assertEquals(3, account.getCharges().size());
    }

    @Test
    public void guestAccount_getChargesReturnsDefensiveCopy() {
        account.addCharge(room);
        List<Chargeable> returnedList = account.getCharges();
        returnedList.add(standardSpa);

        assertEquals(1, account.getCharges().size(), "Original charges list must not be modified by altering returned copy");
        assertFalse(account.getCharges().contains(standardSpa));
    }
}