package au.edu.curtin.myapplication;

public class EquipmentItemSchema {
    public static class EquipmentItemTable
    {
        public static final String TABLE_NAME = "equipmentItems";
        public static class Cols
        {
            public static final String ITEMID = "itemId";
            public static final String ITEMDESCRIPTION = "itemDescription";
            public static final String ITEMVALUE = "itemValue";
            public static final String USABLE = "usableBool";
            public static final String ITEMROW = "itemRow";
            public static final String ITEMCOL = "itemCol";
            public static final String PLAYEROWNED = "playerOwnedBool";
            public static final String EQUIPMENTITEMMASS = "equipmentItemMass";
        }
    }
}
