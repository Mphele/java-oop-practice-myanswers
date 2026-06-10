package za.co.wethinkcode.model;

import za.co.wethinkcode.service.Borrowable;

public class Book extends LibraryItem implements Borrowable {

    public Book(String itemId, String title){
        super(itemId, title);
    }

    @Override
    public void checkout() {
        if(getStatus()!=ItemStatus.AVAILABLE){
            throw new IllegalStateException();
        }
        status= ItemStatus.CHECKED_OUT;
    }

    @Override
    public void returnItem() {

        if(getStatus()!=ItemStatus.CHECKED_OUT){
            throw new IllegalStateException();
        }
        status = ItemStatus.AVAILABLE;

    }
}
