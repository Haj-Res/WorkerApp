Add db.properties file to the resource folder.
db.properties should contain the following fields:
user = db account name
password = password for said account
url = connection string for jdbc

Example:
user = root
password = Pa$$w0rd
url = jdbc:mysql://localhost:3306/WorkersDB?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Sarajevo