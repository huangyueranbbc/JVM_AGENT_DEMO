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

import static java.lang.System.out;

/**
 * @author Aleksey Shipilev
 */
public class JOLSample_11_ClassWord {

    /*
     * This is the example to have insight into object headers.
     *
     * In HotSpot, object header consists of two parts: mark word,
     * and class word. We can clearly see the class word by analysing
     * two syntactically equivalent instances of two distinct classes.
     * See the difference in headers, that difference is the reference
     * to class.
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseInstance(new A()).toPrintable());
        out.println(ClassLayout.parseInstance(new B()).toPrintable());
        out.println(ClassLayout.parseInstance(new int[11]).toPrintable()); // arrays
    }

    public static class A {
        // no fields
    }

    public static class B {
        // no fields
    }

    /**
     * TODO object result out:
     *          OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     mark       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     klass      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     reference  8     4        (object header)                           b8 cd 8e ef (10111000 11001101 10001110 11101111) (-275853896)
                12     4       (loss due to the next object alignment)
     */

    /**
     * TODO arrays result out:
     *          OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     mark       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     klass      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     reference  8     4        (object header)                           1a 02 84 ef (00011010 00000010 10000100 11101111) (-276561382)
     len        12     4       (object header)                           0b 00 00 00 (00001011 00000000 00000000 00000000) (11)
                12     4       (loss due to the next object alignment)
     */

}