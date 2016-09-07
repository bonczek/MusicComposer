#Project compilation, test and installation in local maven repository
#(produced jar won't work - lack of runtime dependencies)
mvn clean install

#Run through maven (after project build)
mvn exec:java

#Build standalone version
mvn jfx:jar

#Run standalone version
java -jar target/jfx/app/music-composer-1.0-SNAPSHOT-jfx.jar