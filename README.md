# CSC207-Project
CSC207 Software Deisgn at University of Toronto

#Important notice
1. Please follow the event format when adding more events to myorders.txt.
2. Each line is one event. 
3. Please add whitespace to each word in each event.
4. Each type of event must follow the length of follow examples.
5. When adding wrong events which are events that event package does not include, please start first word other than “Order, Ready, Picker, Sequencer, Loader, Marshalling and Replenisher”. For example, “Driver Jerry drives”.

#Possible events
"Order S White"
"Ready Picker/Sequencer/Loader/Replenisher Alice ready"
"Picker Alice scan 1"
"Sequencer Sue scan 1“
"Loader Bill scan 1”
"Marshalling Picker Alice to Marshalling"
"Replenisher Ruby replenish A 0 0 0"

#Command line
mkdir target
javac -d target src/warehouse/*.java src/event/*.java
java -cp target warehouse.Simulator myorders.txt
