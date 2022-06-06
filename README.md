DSID-RMI

Como compilar o programa

javac -d ./bin/ ./src/client/*.java ./src/interfaces/*.java ./src/parts/*.java ./src/server/*.java ./src/utils/*.java

Como rodar

Server:
java -cp ./bin server.Server

Cliente (App): 
java -cp ./bin client.Client
