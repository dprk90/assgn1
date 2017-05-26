package com.example.android.freshleafy1;

public class AnItem {

    String nameHindi ;
    String nameEng ;
    String image ;
    int price ;
    int cat_id ;

    public AnItem( String nameHindi ,String nameEng , String image ,int price ,int cat_id ){
        this.nameHindi = nameHindi ;
        this.nameEng = nameEng ;
        this.price = price ;
        this.cat_id = cat_id ;
        this.image = image ;
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
    public String getImage()
    {
        return image ;
    }
}
