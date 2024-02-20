build:
	mvn clean compile assembly:single

run:
	java -cp ./target/BasicChessGame-0.2-jar-with-dependencies.jar Tennyson_T_Bardwell.BasicChessGame.Launcher
