public class Bin{
    int id;
    int capacity;
    DLinkedList<ObjOfBin> ObjectList;
    public void setObjectList(){
        this.ObjectList = new DLinkedList<ObjOfBin>();
    }
    public DLinkedList<ObjOfBin> getObjectList(){
        return this.ObjectList;
    }
    public void setId(int i){
        this.id = i;
    }
    public void setCapacity(int i){
        this.capacity = i;
    }
    public int getId(){
        return this.id;
    }
    public int getCapacity(){
        return this.capacity;
    }

    public Bin(int i, int cap){
        this.id = i;
        this.capacity = cap;
    }

}