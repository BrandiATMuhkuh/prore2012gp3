/*
  Receives Test Events from your phone.
 After it gets a test message the led 13 will blink
 for one second.
 */

#include <MeetAndroid.h>

MeetAndroid meetAndroid;
int onboardLed = 13;
int startButton = 7;
int startButtonState = 0;

void setup()  
{
  // use the baud rate your bluetooth module is configured to 
  // not all baud rates are working well, i.e. ATMEGA168 works best with 57600
  //115000 firefly
  //57600 old
  Serial.begin(57600); 

  // register callback functions, which will be called when an associated event occurs.
  // - the first parameter is the name of your function (see below)
  // - match the second parameter ('A', 'B', 'a', etc...) with the flag on your Android application
  meetAndroid.registerFunction(testEvent, 'A');  
  meetAndroid.registerFunction(setGroupProgress, 'B');  
  meetAndroid.registerFunction(setMyProgress, 'C');  
  meetAndroid.registerFunction(setGroupNotificatinoLight, 'D');  
  meetAndroid.registerFunction(setMyNotificatinoLight, 'E');  

  pinMode(onboardLed, OUTPUT);
  pinMode(startButton, INPUT);
  digitalWrite(onboardLed, HIGH);

}

void loop()
{
  meetAndroid.receive(); // you need to keep this in your loop() to receive events

  startButtonState = digitalRead(startButton);
  
  
  
  //Runs as long as button is pressed
  while (startButtonState == HIGH){
    int button_delay=0;
    
    //count button press time
    
    while (startButtonState == HIGH){
      button_delay++;
      delay(100);
      startButtonState = digitalRead(startButton);
      
      //Long press button
      if(button_delay == 10){ //no need to wait for user to release
        sendStart(); 
      }        
      
    }
    
    //short press button
    if(button_delay < 10) { //short press
      sendShowProgress();
    }
    
  }
  
  
  
}

void setGroupProgress(byte flag, byte numOfValues){
  meetAndroid.send("setGroupProgress");
  meetAndroid.send(meetAndroid.getInt());
}

void setMyProgress(byte flag, byte numOfValues){
  meetAndroid.send("setMyProgress");
  meetAndroid.send(meetAndroid.getInt());
}
void setGroupNotificatinoLight(byte flag, byte numOfValues){
  meetAndroid.send("setGroupNotificatinoLight");
  meetAndroid.send(meetAndroid.getInt());
}
void setMyNotificatinoLight(byte flag, byte numOfValues){
  meetAndroid.send("setMyNotificatinoLight");
  meetAndroid.send(meetAndroid.getInt());
}

void sendStart(){
  meetAndroid.send("sendstart");
}

void sendShowProgress(){
  meetAndroid.send("sendshowprogress");
}




/*
 * This method is called constantly.
 * note: flag is in this case 'A' and numOfValues is 0 (since test event doesn't send any data)
 */
void testEvent(byte flag, byte numOfValues)
{
  sendStart();
  flushLed(300);
  flushLed(300);
  //meetAndroid.send(meetAndroid.getInt());
}

void flushLed(int time)
{
  digitalWrite(onboardLed, LOW);
  delay(time);
  digitalWrite(onboardLed, HIGH);
  delay(time);
}




