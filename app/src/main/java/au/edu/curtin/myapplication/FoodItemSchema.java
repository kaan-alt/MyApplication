package au.edu.curtin.myapplication;

public class FoodItemSchema {
    public static class FoodItemTable
    {
        public static final String TABLE_NAME = "foodItems";
        public static class Cols
        {
            public static final String ITEMID = "itemId";
            public static final String ITEMDESCRIPTION = "itemDescription";
            public static final String ITEMVALUE = "itemValue";
            public static final String USABLE = "usableBool";
            public static final String ITEMROW = "itemRow";
            public static final String ITEMCOL = "itemCol";
            public static final String PLAYEROWNED = "playerOwnedBool";
            public static final String FOODHEALTH = "foodHealth";
        }
    }
}
