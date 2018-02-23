
package sha.work.mapper;

import static org.junit.Assert.*;
import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import sha.framework.util.DateUtility;

/**
 * Mapper junit test base class.
 */
public class BaseMapperTest {
    
    public static final String TEST_SRC_PATH = 
            "src/test/resources/com/fastretailing/dcp/price/importsummarymaster/repository/";

    public static final String TEST_SELECT_NO_RECORD = "TestSelectNoRecord.xml";
    
    public IDataSet dataSet;
    
    public void initIdataSet(String xmlFilePath) throws MalformedURLException, DataSetException {
        this.dataSet = new FlatXmlDataSetBuilder().build(new File(xmlFilePath));
    }
    
    /**
     * DbUnit XML data to dataSet.
     *
     * @param tableName table name
     * @return map list
     */
    public Map<String, Map<String, Object>> importXmlDataToMapList(
            String tableName, String keyName) throws DataSetException {
        ITable table = dataSet.getTable(tableName);
        ITableMetaData meta = table.getTableMetaData();
        Column[] columns = meta.getColumns();

        Map<String, Map<String, Object>> records = new HashMap<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            Map<String, Object> record = new HashMap<>();
            String key = "";
            for (Column column : columns) {
                String columnName = column.getColumnName();
                if (columnName.equals(keyName)) {
                    key = table.getValue(i, columnName).toString();
                }
                record.put(columnName, table.getValue(i, columnName));
            }
            records.put(key, record);
        }
        return records;        
    }
    
    /**
     * Verify DB record value equals expect value.
     * @param beanInfo DB record bean
     * @param key key to find witch expect data should be referred.
     * @param expectRs expect result value map
     * @throws Exception exception
     */
    public void verifyRecordValue(
            Object beanInfo, 
            String key, 
            Map<String, Map<String, Object>> expectRs) throws Exception {
        
        Map<String, String> properties = BeanUtils.describe(beanInfo);
        Map<String, Object> expectEntity = expectRs.get(key);
        for (Map.Entry<String, String> property : properties.entrySet()) {
            
            // Convert camel case name to snake case name for search expect value
            String propertyNameSnakeCase = StringUtils.join(
                    StringUtils.splitByCharacterTypeCamelCase(
                            property.getKey()), "_").toLowerCase();

            if (expectEntity.containsKey(propertyNameSnakeCase)) {
                if (PropertyUtils.getPropertyType(beanInfo, property.getKey())
                        .equals(LocalDateTime.class)) {
                   
                    DateTimeFormatter dateTimeFormatter = 
                                DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")
                                .withResolverStyle(ResolverStyle.STRICT);
                    LocalDateTime orgDt = LocalDateTime.parse(property.getValue(), 
                            dateTimeFormatter);
                    LocalDateTime trgDt = DateUtility.parseDateTime(
                            expectEntity.get(propertyNameSnakeCase).toString(), 
                            DateUtility.DateTimeFormat.UUUUHMMHDDHHQMIQSS.getFormat());
                    assertEquals(orgDt, trgDt);
                    
                } else {
                    //Confirm bean property value equals expect value
                    assertEquals(expectEntity.get(propertyNameSnakeCase).toString(), 
                                 property.getValue());
                }
            }
        }
    }
    
    /**
     * copy expect value to bean value.
     * @param beanInfo DB record bean
     * @param expectEntity expect result value map
     * @throws Exception exception
     */
    public void copyExpectValueToBean(
            Object beanInfo, 
            Map<String, Object> expectEntity) throws Exception {
        
        Map<String, String> properties = BeanUtils.describe(beanInfo);
        for (Map.Entry<String, String> property : properties.entrySet()) {
            
            // Convert camel case name to snake case name for search expect value
            String propertyNameSnakeCase = StringUtils.join(
                    StringUtils.splitByCharacterTypeCamelCase(
                            property.getKey()), "_").toLowerCase();

            if (expectEntity.containsKey(propertyNameSnakeCase)) {
                //copy map property value to bean value
                if (PropertyUtils.getPropertyType(beanInfo, property.getKey())
                        .equals(LocalDateTime.class)) {
                    BeanUtils.copyProperty(beanInfo, property.getKey(), 
                            DateUtility.parseDateTime(
                                    expectEntity.get(propertyNameSnakeCase).toString(), 
                                    DateUtility.DateTimeFormat.UUUUHMMHDDHHQMIQSS.getFormat()));
                } else if (PropertyUtils.getPropertyType(beanInfo, property.getKey())
                        .equals(Integer.class)) {
                    BeanUtils.copyProperty(beanInfo, property.getKey(), 
                            Integer.valueOf(expectEntity.get(propertyNameSnakeCase).toString()));
                }  else if (PropertyUtils.getPropertyType(beanInfo, property.getKey())
                        .equals(Long.class)) {
                    BeanUtils.copyProperty(beanInfo, property.getKey(), 
                            Long.valueOf(expectEntity.get(propertyNameSnakeCase).toString()));
                } else {                    
                    BeanUtils.copyProperty(beanInfo, property.getKey(), 
                            expectEntity.get(propertyNameSnakeCase).toString());
                }
            }
        }
    }
    
    
}
