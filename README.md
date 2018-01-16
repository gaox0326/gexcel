# gexcel
gexcel is a library to convert excel into Java object.

## Using gexcel
The primary classes to use, [`Excel`](https://github.com/gaox0326/gexcel/blob/master/src/main/java/com/github/gaoxue/gexcel/Excel.java) and [`ExcelConfig`](https://github.com/gaox0326/gexcel/blob/master/src/main/java/com/github/gaoxue/gexcel/config/ExcelConfig.java).

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
2. Use `ExcelConfig` to indicate an excel for convert. Default values(0 based): sheet index is 0, title row index is 0, data row index is 1. Use user-defined config as follows:
```
ExcelConfig config = new ExcelConfig(inputStream, sheetIndex, titleRowIndex, dataRowIndex);
```
config property | description
 :----:         | :----
 sheetIndex     | excel sheet to handle
 titleRowIndex  | excel title row index
 dataRowIndex   | excel data row begin index
3. Use [`EnvironmentConfig`](https://github.com/gaox0326/gexcel/blob/master/src/main/java/com/github/gaoxue/gexcel/config/EnvironmentConfig.java) to configure environment variables.
* setDateFormatPattern: when Java object field type is Date(String), excel string(Date) value will be parsed(formated) to date(String) with this pattern, default value yyyy-MM-dd. Use `SimpleDateFormat`.
* setTrueFormat/setFalseFormat: when Java object field type is Boolean/boolean(String), excel String(boolean) value will be parsed(formatted) to Boolean(String) with this config, defalut "true" for `true`, "false" for `false`.
* setDoubleFormatPattern: when Java Object field type is Double(String), excel string(Double) value will be parsed(formatted) to double(string) with this pattern, default value #.##. Use `DecimalFormat`.