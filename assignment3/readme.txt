Must have maven installed
Run the commands below in the directory where the pom.xml resides in

1. Build the project
   > mvn install

2. Run the crawler
   > mvn exec:java -D exec.mainClass="crawler.KijijiCrawler" -Dexec.args="<seed> <limit>"
   Where seed is the url to house listings in kijiji and limit is the number of listing you want to extract
   (ie seed="https://www.kijiji.ca/b-apartments-condos/city-of-toronto/page-2/c37l1700273" and limit=2)
3. Run Server (App)
   > mvn spring-boot:run (windows)
   > ./mvn spring-boot:run (unix)

    NOTE: If server is run before crawler then server will display based on what was in database previously. To see new listings, run crawler then the application

4. To see app go to localhost:8080/cscc01a3

