package za.co.wethinkcode.model;

public class ReferenceJournal extends LibraryItem{

    private int floorNumber;

    public ReferenceJournal(String itemId, String title, int floorNumber){
        super(itemId, title);
        if(floorNumber<1){
            throw new IllegalArgumentException();
        }
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber(){
        return floorNumber;
    }



}
