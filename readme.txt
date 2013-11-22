Running the application with hibernate jpa and logback logging on tomee:

    mvn clean package tomee:run


The Application is now available here:

    http://localhost:8080/DukesShoppingList/

    
Alternative:

    mvn clean package tomee:build
    cd target\apache-tomee\bin
    startup.bat
