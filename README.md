JVMINT_JsonConverter
====================

JSON Converter 
1. Application uses reflections.
2. It converts java class into JSON string. Class can contain:
- primitive types
- Integer, Double, String
- Composite objects, user-defined objects
- Arrays


To test application run it as JUnit test.

Sample output:
 { "SomeFieldInt" : 1, "SomeFieldDoub" : 1.25, "SomeFieldInteger" : 12, "SomeFieldDouble" : 12.22, "SomeFieldString" : "SomeString", "SomeFieldChar" : "a", "SomeFieldBoolean" : "true", "values" :  [ "A","B","C" ] , "testClass1" :  { "SomeFieldInt1" : 1, "SomeFieldDoub1" : 1.25, "testClass2" :  { "SomeFieldInt2" : 1, "SomeFieldDoub2" : 1.25 } } }
