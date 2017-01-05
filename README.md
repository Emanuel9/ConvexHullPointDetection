Computational Geometry College project, Fall semester, Second year<br />
Authors: Emanuel Dumitru, Ovidiu Miu<br />
Date: 2014

How to run it
-----------------------------------------------------------------------------------------------


mkdir src <br />
mkdir build\classes <br />
javac -sourcepath src -d build\classes src\mainclass\MainClass.java <br />
java -cp build\classes mainclass.MainClass<br />


echo Main-Class: mainclass.MainClass>mf <br />
mkdir build\jar <br />
jar cfm build\jar\MainClass.jar mf -C build\classes . <br />
java -jar build\jar\MainClass.jar<br />


What does it do?
------------------------------------------------------------------------------------------------

Generates random points on a simple GUI, does Convex Hull with all these points, generates another random 
point and then checks if that point is in, on or out the Convex Hull. 
