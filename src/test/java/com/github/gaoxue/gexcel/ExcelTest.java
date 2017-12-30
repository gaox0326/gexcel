package com.github.gaoxue.gexcel;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import org.junit.Test;

import com.github.gaoxue.gexcel.config.ExcelConfig;
import com.github.gaoxue.gexcel.reflect.TypeToken;

/**
 * @author gaoxue
 */
public class ExcelTest {

    @Test
    public void test() {
        // EnvironmentConfig.getInstance().setDateFormatPattern("yyyy-MM-dd HH:mm:ss");
        
        String filename = "/excel/person.xls";
        InputStream inputStream = getClass().getResourceAsStream(filename);
        ExcelConfig config = new ExcelConfig(inputStream);

        Person person = Excel.fromExcel(config, Person.class);
        System.out.println(person.toString());

        TypeToken<List<Person>> typeToken = new TypeToken<List<Person>>() { };
        Type type = typeToken.getType();
        @SuppressWarnings("unchecked")
        List<Person> list = (List<Person>) Excel.fromExcel(config, type);
        for (Person item : list) {
            System.out.println(item.toString());
        }
    }

}
