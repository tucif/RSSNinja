javac -cp $CLASSPATH com/rssninja/*.java
echo "Java classes generated"
jar cvf Aprendiz.jar -C ./ com
echo "Jar created!"
