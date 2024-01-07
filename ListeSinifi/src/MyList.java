public class MyList<T> {

    private int capacity;
    private T[] array;

    //    public MyList(int capacity) {
//        this.capacity = capacity;
//        this.array = (T[]) new Object[this.capacity];
//    }
    public MyList() {
        this.capacity = 10;
        this.array = (T[]) new Object[this.capacity];
    }

    public MyList(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[this.capacity];
    }


    public int size() {
        int size = 0;
        for (T value : this.array) {
            if (value != null) {
                size++;
            }
        }

        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void add(T data) {
        int index = size();
        if (index == this.getCapacity()) {
            capacity *= 2;
            T[] tempArr = (T[]) new Object[capacity];
            for (int i = 0; i < this.array.length; i++) {
                tempArr[i] = this.array[i];
            }
            this.array = tempArr;
        }
        array[index] = data;
    }

    public T get(int index) {
        return this.array[index];
    }

    public T remove(int index) {
        T[] tempArr = (T[]) new Object[capacity];
        if (index < 0 || index >= size()) {
            return null;
        } else {
            for (int i = 0; i < tempArr.length; i++) {
                if (index != i)
                    tempArr[i] = this.array[i];
                else
                    tempArr[i] = this.array[i + 1];
            }
        }
        return this.array[index];
    }

    public int set(int index, T data) {
        array[index] = data;
        return (int)array[index];
    }

    public String toString() {
        String result = "";
        for (T data : this.array) {
            if (data != null) {
                result += data + " ";
            }
        }
        return result;
    }

    public int indexOf(T data){
        int index = -1;
        for(int i = 0; i<this.array.length;i++){
            if(data == this.array[i]){
                index = i;
            }
        }
        return index;
    }

    public boolean isEmpty(){
        int counter = 0;
        for(int i = 0; i<this.array.length;i++){
            if(this.array[i] == null){
                counter++;
            }
        }
        if(counter == getCapacity()){
            System.out.println("Liste Boş");
            return true;
        }else{
            System.out.println("Listede Eleman Var");
            return false;
        }
    }

    public T[] toArray(){
        T[] tempArr = (T[]) new Object[capacity];
        for(int i = 0; i<this.array.length;i++){
            tempArr[i] = this.array[i];
        }
        return tempArr;
    }

    public void clear(){
        for(int i = 0; i< this.array.length;i++){
            this.array[i] = null;
        }
    }

    public MyList<T> subList(int start, int finish) {

        MyList<T> altListe = new MyList<>(finish - start + 1);
        for (int i = start; i <= finish; i++) {
            altListe.add(array[i]);
        }
        return altListe;
    }

    public boolean contains(T data) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == data) {
                return true;
            }
        }
        return false;
    }

    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }
}
