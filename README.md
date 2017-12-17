# gexcel
gexcel is a library to convert excel into Java object.

## Using gexcel
The primary classes to use, [`Excel`](https://github.com/gaox0326/gexcel/blob/master/src/main/java/com/github/gaoxue/gexcel/Excel.java) and [`ExcelConfig`](https://github.com/gaox0326/gexcel/blob/master/src/main/java/com/github/gaoxue/gexcel/ExcelConfig.java).

### Object Examples
```
Class Person {

    @Column
    private String name;

    @Column(name = "age")
    private Integer age;

}

InputStream inputStream = new FileInputStream("person.xls");
ExcelConfig config = new ExcelConfig(inputStream);
Person person = Excel.fromExcel(config, Person.class);
```

### List Examples
```
TypeToken<List<Person>> typeToken = new TypeToken<List<Person>>() { };
Type type = typeToken.getType();
List<Person> list = (List<Person>) Excel.fromExcel(config, type);
```

### Excel template requirements
1. Require a title row which defines the mapping infos between Excel and Java obejct. Then followed serveral data rows for convert.
2. The value of title row cell means the name of Java object field(Annotated with `Column`), the values of data rows mean the field values.
#### Excel template example
row index | name   | age
 :----:   | :----: | :----:
 1        | gaoxue | 18
 2        | java   | 22

### Java object requirements
1. Use annotation `Column` to indicate a field is to be inclued for convert.
2. Use ExcelConfig to indicate an excel for convert. Default values(0 based): sheet index is 0, title row index is 0, data row index is 1. Use user-defined config as follows:
```
ExcelConfig config = new ExcelConfig(inputStream, sheetIndex, titleRowIndex, dataRowIndex);
```