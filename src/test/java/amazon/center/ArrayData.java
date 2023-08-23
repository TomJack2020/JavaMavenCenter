package amazon.center;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayData {


    @Test
    public void Demo01(){

        List<String> columns = new ArrayList<String>(){{
            add("spu");
            add("sku");
        }};
        System.out.println(columns.get(0));

    }
}
