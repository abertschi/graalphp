package org.graalphp.runtime.array;

/**
 * @author abertschi
 */
public class LibraryExampleTests {

    //
    //        @Test
    //        public void runExample01Test() {
    //            ArrayReadNode read = ArrayReadNodeGen.create(new ArgumentNode(0), new
    //            ArgumentNode(1));
    //            CallTarget target = Truffle.getRuntime().createCallTarget(new ExampleRootNode
    //            (read));
    //
    //            Assert.assertEquals(3, target.call(new SequenceArray(1, 2, 3), 1));
    //            Assert.assertEquals(0, target.call(new BufferArray(2), 1));
    //        }
    //

//    @Test
//    public void runExample3() {
//        ArrayReadNode read = ArrayReadNodeGen.create(new ArgumentNode(0), new ArgumentNode(1));
//        CallTarget target = Truffle.getRuntime().createCallTarget(new ExampleRootNode(read));
//
//        Assert.assertEquals(3, target.call(new SequenceArray(1, 2, 3), 1));
//        Assert.assertEquals(0, target.call(new BufferArray(2), 1));
//    }
//
//    @Test
//    public void uncachedExample3() {
//        Object noArray = new Object();
//
//        ArrayLibrary arrays = ArrayLibrary.getUncached();
//
//        Assert.assertFalse(arrays.isArray(noArray));
//
//        Object sequence = new SequenceArray(1, 2, 3);
//        Assert.assertTrue(arrays.isArray(sequence));
//        Assert.assertEquals(3, arrays.read(sequence, 1));
//
//        Object buffer = new BufferArray(2);
//        Assert.assertTrue(arrays.isArray(buffer));
//        Assert.assertEquals(0, arrays.read(buffer, 1));
//    }

}
