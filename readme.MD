#JAVA AGENT DEMO  
##Instrumentation  
run:    
1.创建MySizeOf工具类  
2.META-INF/MANIFEST.MF添加Premain-Class: MySizeOf属性  
3.将MySizeOf类打成JAR包  
4.使用MySizeOf编写监控类T7、TestSize  
5.将MySizeOf的JAR包导入到监控类的classpath中进行编译  
    javac -classpath JVMObjectSize.jar TestSize.java  
6.通过-javaagent命令运行java监控类  
    java -javaagent:JVMObjectSize.jar TestSize  


run result:  
一个Interger对象大小为：16  
一个String对象大小为：24  
一个String对象大小（关闭指针压缩）为：48  
一个char对象大小为：24  
一个ArrayList对象大小为：24  
一个Object对象大小为：16  
一个Long对象大小为：24  

##JOL  
官方的例子。做了点翻译。  
基本操作就那么几个。      
com.hyr.jol.demo.*  

