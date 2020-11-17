package com.marstalk.apps.triggerGenerator;

import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * 扫描目录下的sql文件，每个文件是一个ddl语句。 根据ddl，将相关信息替换到trigger模板中，生成trigger实例，保存到文件中，命名tri_${tableName}_afdel.sql
 *
 * @author shanzhonglaosou
 */
public class TriggerGennerator {

    public static void main(String[] args) {
        String rootPath = "C:\\Users\\louisl\\Desktop\\tables-need-trigger";
        dojob(rootPath);
        // table: qgen_api_invoke. Has crud_id? false
        // table: qgen_service_body_template. Has crud_id? false
        // 上述两个表，就不是用resourceId了。
    }

    private static void dojob(String rootPath) {
        File file = new File(rootPath);
        assert file.isDirectory();

        File[] files = file.listFiles();
        assert files != null;

        Map<String, Ddl> ddlList = new HashMap<>(10);
        Arrays.asList(files).forEach(f -> handle(f, ddlList));
        //应用到模板中。
        applyToTemplate(ddlList);
    }

    private static void handle(File f, Map<String, Ddl> ddlList) {
        //第一步，过滤有效的columnName
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            //提前保证ddl是格式化好的。
            List<String> lines = br.lines().collect(Collectors.toList());
            List<String> columNames = lines.stream().filter(s -> !s.contains("CREATE TABLE"))
                    .filter(s -> !s.contains("ENGINE="))
                    .filter(s -> !s.contains("entry_id"))
                    .filter(s -> !s.contains("entry_datetime"))
                    .filter(s -> !s.contains("update_id"))
                    .filter(s -> !s.contains("update_datetime"))
                    .filter(s -> !s.contains("h_version"))
                    .filter(s -> !s.contains("o_id"))
                    .filter(s -> !s.contains("oid"))
                    .filter(s -> !s.contains("BTREE"))
                    .filter(s -> !s.contains("UNIQUE KEY"))
                    .map(s -> s.substring(s.indexOf("`") + 1, s.lastIndexOf("`")))
                    //.peek(System.out::println)
                    .collect(Collectors.toList());

            String tableName = lines.stream().filter(s -> s.contains("CREATE TABLE"))
                    .map(s -> s.substring(s.indexOf("`") + 1, s.lastIndexOf("`")))
                    .findFirst()
                    .orElse("Can't extract tableName");

            String primaryKey = lines.stream().filter(s -> s.contains("PRIMARY KEY"))
                    .map(s -> s.substring(s.indexOf("`") + 1, s.lastIndexOf("`")))
                    .findFirst()
                    .orElse("Can't extract primaryKey");

            ddlList.put(tableName, new Ddl(tableName, primaryKey, columNames, columNames.contains("crud_id")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void applyToTemplate(Map<String, Ddl> ddlList) {
        String templatePath = "D:\\github\\algorithmdemo\\src\\main\\java\\com\\marstalk\\apps\\triggerGenerator\\trigger_template.sql";
        File templateFile = new File(templatePath);
        if (!templateFile.exists()) {
            System.err.println("template file does not exist");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(templateFile))) {
            String template = br.lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println(ddlList.size());
            ddlList.forEach((s, ddl) -> handleDdl(ddl, template));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用的模板中，并且写到文件
     *
     * @param ddl
     * @param template
     */
    private static void handleDdl(Ddl ddl, String template) {
        Map<String, String> map = new HashMap<>();
        map.put("\\$\\{tableName\\}", ddl.tableName);
        map.put("\\$\\{primaryKey\\}", ddl.primaryKey);
        map.put("\\$\\{columnNameAndValues\\}", ddl.columnNameAndValuesConcatStr());
        for (Entry<String, String> e : map.entrySet()) {
            template = template.replaceAll(e.getKey(), e.getValue());
        }

        String parentPath = Thread.currentThread().getContextClassLoader().getResource("com/marstalk/apps/triggerGenerator").getPath();
        File file = new File(parentPath + File.separator + "tri_" + ddl.tableName + ".sql");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(file.getPath());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(template, 0, template.length());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Ddl {

        String tableName;
        String primaryKey;
        List<String> columnNames;
        boolean hasCrudId;

        public Ddl(String tableName, String primaryKey, List<String> columnNames, boolean hasCrudId) {
            this.tableName = tableName;
            this.primaryKey = primaryKey;
            this.columnNames = columnNames;
            this.hasCrudId = hasCrudId;
        }

        /**
         * 'crud_id=', IFNULL(old.crud_id, 'null'), ' ref_id=', IFNULL(old.ref_id, 'null'), ' profile_type=',
         * IFNULL(old.profile_type, 'null'), ' profile_cat=', IFNULL(old.profile_cat, 'null'), ' profile_value=',
         * IFNULL(old.profile_value, 'null')
         *
         * @return
         */
        public String columnNameAndValuesConcatStr() {
            if (CollectionUtils.isEmpty(columnNames)) {
                System.err.println("columnNames is empty");
            }
            return columnNames.stream()
                    .map(s -> "'" + s + "=', IFNULL(old." + s + ", 'null') ")
                    .collect(Collectors.joining("," + System.lineSeparator()));
        }

        @Override
        public String toString() {
            return "DDL{" +
                    "tableName='" + tableName + '\'' +
                    ", primaryKey='" + primaryKey + '\'' +
                    ", columnNames=" + columnNames +
                    ", hasCrudId=" + hasCrudId +
                    '}';
        }

    }

}
