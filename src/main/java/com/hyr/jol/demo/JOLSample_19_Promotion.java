/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.hyr.jol.demo;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.io.PrintWriter;

import static java.lang.System.out;

/**
 * @author Aleksey Shipilev
 */
public class JOLSample_19_Promotion {

    /*
     * The example of object promotion.
     *
     * Once the object survives the garbage collections, it is getting
     * promoted to another generation. In this example, we can track
     * the addresses of the objects, as it changes over time.
     *
     * VM also needs to record the "age" (that is, the number of GC
     * cycles the object had survived) of the object somewhere, and
     * it is stored in mark word as well. See how particular mark word
     * bits change with each promotion.
     */

    static volatile Object sink;

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());

        PrintWriter pw = new PrintWriter(System.out, true);

        Object o = new Object(); // 被观察的对象

        ClassLayout layout = ClassLayout.parseInstance(o); // 对象的结构

        long lastAddr = VM.current().addressOf(o);
        pw.printf("Fresh object is at %x%n", lastAddr); // 新生对象地址

        int moves = 0; // 地址移动的次数
        for (int i = 0; i < 100000; i++) {
            long cur = VM.current().addressOf(o); // 对象的当前地址
            if (cur != lastAddr) { // 如果触发了GC，地址产生了变化。
                moves++;
                pw.printf("*** Move %2d, object is at %x%n", moves, cur); // 每次GC，对象地址发生的变化
                out.println(layout.toPrintable()); // 可以观察到每次发生GC，记录GC分代年龄等相关信息的mark标记词发生的变化
                /*
                            OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
                mark          0     4        (object header)                           09 00 00 00 (00001001 00000000 00000000 00000000) (9)
                klass         4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
                reference     8     4        (object header)                           76 05 84 ef (01110110 00000101 10000100 11101111) (-276560522)
                              12    4        (loss due to the next object alignment)
                mark:存储对象自身的运行时数据，如哈希码（HashCode）、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID、偏向时间戳等
                klass:class指针，指向对象的类元信息。
                 */
                lastAddr = cur;
            }

            // make garbage 制造垃圾对象 产生GC
            for (int c = 0; c < 10000; c++) {
                sink = new Object();
            }
        }

        pw.close();
    }

}