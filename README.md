Computational Geometry college project, fall semester, second year
Authors: Emanuel Dumitru, Ovidiu Miu
Date: 2014

How to run it
-----------------------------------------------------------------------------------------------


mkdir src
mkdir build\classes
javac -sourcepath src -d build\classes src\mainclass\MainClass.java
java -cp build\classes mainclass.MainClass


echo Main-Class: mainclass.MainClass>mf
mkdir build\jar
jar cfm build\jar\MainClass.jar mf -C build\classes .
java -jar build\jar\MainClass.jar


What does it do?
------------------------------------------------------------------------------------------------

Generates random points on a simple GUI, does Convex Hull with all these points, generates another random 
point and then checks if that point is in, on or out the Convex Hull. 