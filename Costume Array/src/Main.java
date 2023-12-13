import java.util.Arrays;

class Main{

    int Max=3;
    int def=0;
    int[] data;

    public Main() {
        this.data = new int[Max];
    }
    private void add(int num){
        if (fullSize()){
            resize();
        }
        data[def++]=num;    //membuat dan memasukan data array dari luar ke dalam variable data[]
    }
    private void resize() {
       int newSize= data.length*2;

       int[] newArray=new int[newSize];
        for (int i = 0; i < data.length; i++) {
            newArray[i]=data[i];
        }
        data=newArray;
    }
    private int getNum(int index){
        System.out.println("number yang diambil = "+data[index]);
        return data[index];
    }
    private int setNum(int index,int value){
        System.out.println("index yang ditarget = "+'['+index+']'+" ,value sebelum diganti = "+data[index]+" ,hasil value yang diganti = "+value);
        return data[index]=value;
    }
    private boolean fullSize(){
        return data.length == Max;
    }
    private void remove( int index) {
        for (int i = 0; i < data.length; i++) {
            if (i==index){
                System.out.println("remove function index yang dihapus= "+'['+index+']'+" data array yang dihapus = "+ data[i]);
                data[i]=0;
                for (int j = i; j < data.length-1 ; j++) {
                    data[i]=data[++i];
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Data : "+Arrays.toString(data);
    }

    public static void main(String[] args) {
       Main main= new Main();
       main.add(11);
       main.add(12);
       main.add(23);
       main.add(35);
        System.out.println(main);
        main.getNum(1);
        main.setNum(0,21);
        System.out.println(main);
        main.remove(0);
        System.out.println(main);
    }
}