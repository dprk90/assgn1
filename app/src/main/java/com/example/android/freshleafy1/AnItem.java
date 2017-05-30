package com.example.android.freshleafy1;

public class AnItem {

    private String nameHindi ;
    private String nameEng ;
    private String image ;
    private int price ;
    private int cat_id ;
    private int item_id ;

    public AnItem( String nameHindi ,String nameEng , String image ,int price ,int cat_id , int item_id ){
        this.nameHindi = nameHindi ;
        this.nameEng = nameEng ;
        this.price = price ;
        this.cat_id = cat_id ;
        this.image = image ;
        this.item_id = item_id ;
    }

    public String getName()
    {
        return nameEng ;
    }

    public int getPrice(){
        return price ;
    }
    public int getCat_id(){
        return cat_id ;
    }
    public String getImage(){ return image ; }
    public String getNameHindi(){
        return nameHindi ;
    }
    public int getItem_id() { return item_id ;}
}
