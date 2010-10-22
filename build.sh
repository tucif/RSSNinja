javac -cp $CLASSPATH com/rssninja/*.java com/rssninja/aprendiz/*.java
echo "Java classes generated"
jar cvf Aprendiz.jar -C ./ com
echo "Jar created!"
