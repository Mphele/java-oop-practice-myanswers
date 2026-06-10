package za.co.wethinkcode.model;

public abstract class LibraryItem {

    private String itemId;
    private String title;
    protected ItemStatus status;

    public LibraryItem(String itemId, String title){
        if(itemId==null || title == null || itemId.isEmpty() || title.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.itemId = itemId;
        this.title = title;
        this.status = ItemStatus.AVAILABLE;
    }

    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public ItemStatus getStatus() {
        return status;
    }
}
