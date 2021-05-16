并行GC日志解析:

命令:java -XX:+PrintGCDetails -Xloggc:gc.demo.log -XX:+UseParallelGC -Xmx1g -Xms1g com.lurenjun.jvm.test.GCLogAnalysis

```java
小GC,YangGC
[0.199s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)  //Allocation Failure 年轻代分配内存失败发生GC
[0.210s][info][gc,heap      ] GC(0) PSYoungGen: 262144K->43519K(305664K)  //在0.21-0.199 = 0.011s内STW,年轻代GC262M压缩到43M,Yang区最大容量为305M
[0.210s][info][gc,heap      ] GC(0) ParOldGen: 0K->38974K(699392K) //未发生FullGC,Old区新增39M,Old区最大700M
[0.210s][info][gc,metaspace ] GC(0) Metaspace: 4026K->4026K(1056768K) //元空间最大1G,没有变化
[0.210s][info][gc           ] GC(0) Pause Young (Allocation Failure) 256M->80M(981M) 11.395ms //堆内存一共981M,256M压缩到80M,用时0.011s
[0.210s][info][gc,cpu       ] GC(0) User=0.02s Sys=0.08s Real=0.01s
```



```java
大GC,FullGC
[0.522s][info][gc,start     ] GC(11) Pause Full (Ergonomics)
[0.522s][info][gc,phases,start] GC(11) Marking Phase
[0.526s][info][gc,phases      ] GC(11) Marking Phase 3.264ms
[0.526s][info][gc,phases,start] GC(11) Summary Phase
[0.526s][info][gc,phases      ] GC(11) Summary Phase 0.021ms
[0.526s][info][gc,phases,start] GC(11) Adjust Roots
[0.526s][info][gc,phases      ] GC(11) Adjust Roots 0.491ms
[0.526s][info][gc,phases,start] GC(11) Compaction Phase
[0.550s][info][gc,phases      ] GC(11) Compaction Phase 23.822ms
[0.550s][info][gc,phases,start] GC(11) Post Compact
[0.553s][info][gc,phases      ] GC(11) Post Compact 3.225ms
[0.553s][info][gc,heap        ] GC(11) PSYoungGen: 45617K->0K(232960K) //FullGC时候同时会进行YangGC,Yang区直接45M清空0M
[0.553s][info][gc,heap        ] GC(11) ParOldGen: 625947K->323368K(699392K) //Old区从635M清理到323M
[0.553s][info][gc,metaspace   ] GC(11) Metaspace: 4026K->4026K(1056768K) //元空间不变
[0.553s][info][gc             ] GC(11) Pause Full (Ergonomics) 655M->315M(910M) 31.005ms //堆内存一共910M,655M压缩到315M,整个FullGC用时0.031s
[0.553s][info][gc,cpu         ] GC(11) User=0.19s Sys=0.00s Real=0.03s
```



串行GC日志解析:

命令:java -XX:+PrintGCDetails -Xloggc:gc.demo.log -XX:+UseSerialGC -Xmx1g -Xms1g com.lurenjun.jvm.test.GCLogAnalysis



```java
YangGC
[0.202s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
[0.247s][info][gc,heap      ] GC(0) DefNew: 279616K->34944K(314560K) //DefNew代表Yang区,从279M压缩到34M,整个Yang区314M
[0.247s][info][gc,heap      ] GC(0) Tenured: 0K->56965K(699072K) //Tenured代表Old区,从0M新增到56M,整个Old区699M
[0.247s][info][gc,metaspace ] GC(0) Metaspace: 4025K->4025K(1056768K)//元空间不变
[0.247s][info][gc           ] GC(0) Pause Young (Allocation Failure) 273M->89M(989M) 45.522ms //整个堆内存989M,从273M压缩到89M,用时0.045s
[0.247s][info][gc,cpu       ] GC(0) User=0.03s Sys=0.02s Real=0.05s
```



```java
FullGC
[0.749s][info][gc,start     ] GC(9) Pause Full (Allocation Failure)
[0.749s][info][gc,phases,start] GC(9) Phase 1: Mark live objects
[0.750s][info][gc,phases      ] GC(9) Phase 1: Mark live objects 1.127ms
[0.751s][info][gc,phases,start] GC(9) Phase 2: Compute new object addresses
[0.751s][info][gc,phases      ] GC(9) Phase 2: Compute new object addresses 0.941ms
[0.752s][info][gc,phases,start] GC(9) Phase 3: Adjust pointers
[0.752s][info][gc,phases      ] GC(9) Phase 3: Adjust pointers 0.547ms
[0.752s][info][gc,phases,start] GC(9) Phase 4: Move objects
[0.784s][info][gc,phases      ] GC(9) Phase 4: Move objects 31.791ms
[0.784s][info][gc             ] GC(9) Pause Full (Allocation Failure) 962M->351M(989M) 34.677ms //FullGC从962M压缩到351M
[0.784s][info][gc,heap        ] GC(8) DefNew: 314559K->0K(314560K) //Yang区清空
[0.784s][info][gc,heap        ] GC(8) Tenured: 671263K->359502K(699072K) //Old区从671M压缩到359M
[0.784s][info][gc,metaspace   ] GC(8) Metaspace: 4026K->4026K(1056768K) //元空间不变
```



CMS GC日志解析:

命令:java -XX:+PrintGCDetails -Xloggc:gc.demo.log -XX:+UseConcMarkSweepGC -Xmx1g -Xms1g com.lurenjun.jvm.test.GCLogAnalysis

```java
YangGC:
[0.203s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
[0.203s][info][gc,task      ] GC(0) Using 6 workers of 6 for evacuation
[0.217s][info][gc,heap      ] GC(0) ParNew: 279616K->34943K(314560K) //Yang区,279M压缩到35M,最大314M
[0.217s][info][gc,heap      ] GC(0) CMS: 0K->58182K(699072K) //Old区新增58M
[0.217s][info][gc,metaspace ] GC(0) Metaspace: 4026K->4026K(1056768K) 元空间不变
[0.217s][info][gc           ] GC(0) Pause Young (Allocation Failure) 273M->90M(989M) 14.314ms //整个堆内存989M,273M压缩到90M,用时0.014s
[0.217s][info][gc,cpu       ] GC(0) User=0.09s Sys=0.00s Real=0.02s
```



```java
FullGC:
[0.372s][info][gc,start     ] GC(9) Pause Full (Allocation Failure)
[0.372s][info][gc,phases,start] GC(9) Phase 1: Mark live objects
[0.373s][info][gc,phases      ] GC(9) Phase 1: Mark live objects 0.924ms
[0.373s][info][gc,phases,start] GC(9) Phase 2: Compute new object addresses
[0.374s][info][gc,phases      ] GC(9) Phase 2: Compute new object addresses 0.586ms
[0.374s][info][gc,phases,start] GC(9) Phase 3: Adjust pointers
[0.374s][info][gc,phases      ] GC(9) Phase 3: Adjust pointers 0.478ms
[0.374s][info][gc,phases,start] GC(9) Phase 4: Move objects
[0.399s][info][gc,phases      ] GC(9) Phase 4: Move objects 24.844ms
[0.399s][info][gc             ] GC(9) Pause Full (Allocation Failure) 449M->250M(494M) 27.244ms
[0.399s][info][gc,heap        ] GC(8) ParNew: 157247K->0K(157248K)
[0.399s][info][gc,heap        ] GC(8) CMS: 302741K->256520K(349568K)
[0.399s][info][gc,metaspace   ] GC(8) Metaspace: 4025K->4025K(1056768K)
[0.399s][info][gc             ] GC(8) Pause Young (Allocation Failure) 449M->250M(494M) 27.363ms
[0.399s][info][gc,cpu         ] GC(8) User=0.03s Sys=0.00s Real=0.03s
//初始暂停标记阶段,第一次STW
[0.399s][info][gc,start       ] GC(10) Pause Initial Mark 
[0.399s][info][gc             ] GC(10) Pause Initial Mark 253M->253M(494M) 0.071ms
[0.400s][info][gc,cpu         ] GC(10) User=0.00s Sys=0.00s Real=0.00s
//并发标记阶段
[0.400s][info][gc             ] GC(10) Concurrent Mark 
[0.400s][info][gc,task        ] GC(10) Using 2 workers of 2 for marking
    
[0.401s][info][gc             ] GC(10) Concurrent Mark 1.103ms
[0.401s][info][gc,cpu         ] GC(10) User=0.00s Sys=0.00s Real=0.00s
//并发预清理阶段
[0.401s][info][gc             ] GC(10) Concurrent Preclean 
[0.401s][info][gc             ] GC(10) Concurrent Preclean 0.300ms
    
[0.401s][info][gc,cpu         ] GC(10) User=0.00s Sys=0.00s Real=0.00s
[0.401s][info][gc             ] GC(10) Concurrent Abortable Preclean
[0.413s][info][gc,start       ] GC(11) Pause Young (Allocation Failure)
[0.413s][info][gc,task        ] GC(11) Using 6 workers of 6 for evacuation
[0.419s][info][gc,heap        ] GC(11) ParNew: 139776K->17470K(157248K)
[0.420s][info][gc,heap        ] GC(11) CMS: 256520K->297397K(349568K)
[0.420s][info][gc,metaspace   ] GC(11) Metaspace: 4026K->4026K(1056768K)
[0.420s][info][gc             ] GC(11) Pause Young (Allocation Failure) 387M->307M(494M) 6.173ms
[0.420s][info][gc,cpu         ] GC(11) User=0.00s Sys=0.00s Real=0.01s
[0.432s][info][gc,start       ] GC(12) Pause Young (Allocation Failure)
[0.432s][info][gc,task        ] GC(12) Using 6 workers of 6 for evacuation
[0.443s][info][gc,heap        ] GC(12) ParNew: 157246K->17471K(157248K)
[0.443s][info][gc,heap        ] GC(12) CMS: 297397K->348056K(349568K)
[0.443s][info][gc,metaspace   ] GC(12) Metaspace: 4026K->4026K(1056768K)
[0.443s][info][gc             ] GC(12) Pause Young (Allocation Failure) 443M->356M(494M) 10.513ms
[0.443s][info][gc,cpu         ] GC(12) User=0.09s Sys=0.00s Real=0.01s
[0.443s][info][gc             ] GC(10) Concurrent Abortable Preclean 41.699ms
[0.443s][info][gc,cpu         ] GC(10) User=0.16s Sys=0.00s Real=0.04s
//最终标记阶段,第二次SWT
[0.443s][info][gc,start       ] GC(10) Pause Remark
[0.443s][info][gc             ] GC(10) Pause Remark 360M->360M(494M) 0.530ms
[0.443s][info][gc,cpu         ] GC(10) User=0.00s Sys=0.00s Real=0.00s
//并发清除阶段
[0.443s][info][gc             ] GC(10) Concurrent Sweep
[0.444s][info][gc             ] GC(10) Concurrent Sweep 0.395ms
[0.444s][info][gc,cpu         ] GC(10) User=0.00s Sys=0.00s Real=0.00s
//并发重置阶段
[0.444s][info][gc             ] GC(10) Concurrent Reset
[0.444s][info][gc             ] GC(10) Concurrent Reset 0.179ms
[0.444s][info][gc,cpu         ] GC(10) User=0.00s Sys=0.00s Real=0.00s
[0.444s][info][gc,heap        ] GC(10) Old: 256520K->347956K(349568K)
```



https://gceasy.io/  分析GC日志网站