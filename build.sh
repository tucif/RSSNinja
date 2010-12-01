javac -cp $CLASSPATH com/rssninja/ninja/*.java com/rssninja/aprendiz/*.java com/rssninja/sensei/*.java
echo "Java classes generated"
jar cvf RSSNinja.jar -C ./ com
echo "Jar created!"
