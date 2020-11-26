#测试步骤
1. 先单独编译processor
```
mvn compiler:compile@processor-only
```
2. 再编译整个项目
```$xslt
mvn compiler:compile@compile-project
```
参考：
https://stackoverflow.com/questions/36248959/bad-service-configuration-file-or-exception-thrown-while-constructing-processor