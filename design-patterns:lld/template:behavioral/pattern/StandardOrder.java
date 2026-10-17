






public class StandardOrder extends Order{





    @Override protected  void updateInventory(){
        System.out.println("Updating inventory by deducting items from local warehouse");
    }



}
